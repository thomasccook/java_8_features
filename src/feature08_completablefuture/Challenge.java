package feature08_completablefuture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Challenge {

	private static final String URL = "https://jsonplaceholder.typicode.com/todos/";
	private static final int CALL_COUNT = 10;
	
	////////////////////////////////////////////////////////
	// Use Completable Future
	
	public void useCompletableFuture() throws Exception {
		System.out.println("****** Get Title List - Use CompletableFuture ******");
	
		prn("Queue'ing");
		List<CompletableFuture<String>> listCf = new ArrayList<CompletableFuture<String>>();
		for (int id=1; id <= CALL_COUNT; id++) {
			CompletableFuture<String> cf = getCompletableFuture(URL, id);
			listCf.add(cf);
		}
		
		// Process calls as they arrive
		prn("Waiting");
		List<String> listTitle = new ArrayList<String>();
		for (CompletableFuture<String> cf: listCf) {
			// *** We wait here ***
			String title = cf.get();
			prn("Getting #" + (listCf.indexOf(cf) + 1));
			listTitle.add(title);
		}
		
		prn("\nTitle List:");
		listTitle.stream().sorted().forEach(System.out::println);
	}

	public CompletableFuture<String> getCompletableFuture(String url, int id) throws Exception {				
		return CompletableFuture
				.supplyAsync(   () -> getRemoteData(url, id,  "id"))
				.thenApplyAsync( s -> s + " : " + getRemoteData(url, id, "userId"))
				.thenApplyAsync( s -> s + " : " + getRemoteData(url, id,  "completed"))
				.thenApplyAsync( s -> s + " : " + getRemoteData(url, id,  "title"));
	}
	
	
	////////////////////////////////////////////////////////
	// java.lang.Thread and ConcurrentHashMap
	
	ConcurrentHashMap<String, String> chm = new ConcurrentHashMap<String, String> ();
	
	public void useConcurrentHashmap() throws Exception {
		System.out.println("****** Get Title List - Use java.lang.Thread and ConcurrentHashMap ******");
		
		prn ("Launching Threads");
		for (int id=1; id <= CALL_COUNT; id++) {
			(new CallThread(URL, id)).start();
		}
		
		prn("Waiting");
		int previousSize = 0;
		while(chm.size() < CALL_COUNT) {
			if (chm.size() > previousSize) {
				prn("Received " + chm.size() + " records.");
				previousSize = chm.size();
			} 
			pause(1, 1);
		}
		
		prn("\nTitle List:");
		chm.keySet().stream().sorted().forEach(System.out::println);
	}
	
	class CallThread extends Thread {
		private String url;
		private int id;
		
		public CallThread(String url, int id) {
			this.url = url;
			this.id = id;
		}
		
		public void run() {
			String str = "";
			str = getRemoteData(url, id,  "id");
			str = str + " : " + getRemoteData(url, id,  "userId");
			str = str + " : " + getRemoteData(url, id,  "completed");
			str = str + " : " + getRemoteData(url, id,  "title");
			chm.put(str,  str);
		}
	}
	
	
	////////////////////////////////////////////////////////
	// Support
	
	private String getRemoteData(String url, int id, String field) {
		String urlAndParameters = url + id;
		try {
			pause(1,3);
			prn("Requesting #" + id + "." + field);
			String json = this.makeCall(urlAndParameters);
			Map<String, String> map = parseJSON(json);
			return map.get(field);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}

	// https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
	private String makeCall(String url) throws Exception {
		StringBuilder sb = new StringBuilder();

		URL yahoo = new URL(url);
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine + "\n");
		}
		in.close();

		return sb.toString();
	}

	// https://stackoverflow.com/questions/443499/convert-json-to-map
	public static Map<String, String> parseJSON(String json) throws ScriptException {
		ScriptEngineManager sem = new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		String script = "Java.asJSONCompatible(" + json + ")";
		Map<String, Object> result = (Map)engine.eval(script);
		
		Map<String, String> map = result.entrySet().stream().collect(Collectors.toMap(
	            e -> e.getKey(),
	            e -> {return "" + e.getValue();}));
		
		return map;
	}

	private synchronized static void prn(Object obj) {
		System.out.print(obj);
		System.out.print(" ... ");
		System.out.print(Thread.currentThread().getName());
		System.out.println();
	}

	private void pause(double min, double max) {
		try {
			long seconds = (long) (min + (max - min) * Math.random());
			Thread.currentThread().sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Challenge challenge = new Challenge();
		try {
			challenge.useCompletableFuture();
			//challenge.useConcurrentHashmap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
