package feature07_concurrency_api_improvements;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example {

	private ConcurrentHashMap<Integer, String> chm = new ConcurrentHashMap<Integer, String>();
	
	public void process() throws InterruptedException, ExecutionException {

		boolean display = true;
		if (display) prnTitle("Put data into HashMap");
		List<Integer> list = Arrays.asList(new Integer[] { 5, 3, 6, 4, 7, 8, 2, 1, 9, 10 });
		PutInHashMap putInHashMap = new PutInHashMap(display);
		list
			.parallelStream()
			.map(putInHashMap)
			.collect(Collectors.toList());

		
		if (false) {
			prnTitle("compute:");
			chm.compute(3, (key, val) -> val + "xxx");
			System.out.println(chm);
		}

		if (false) {
			prnTitle("forEachEntry:");
			this.chm.forEach((key, value) ->  prn(key));
		}
		
		
		if (false) {
			prnTitle("forEachEntry:");
			this.chm.forEachEntry(2, (entry) ->  prn(entry));
		}

		
		if (false) {
			prnTitle("forEachKey:");
			this.chm.forEachKey(10, (key) -> prn(key));
		}
		
		if (false) {
			prnTitle("forEachValue:");
			this.chm.forEachValue(2, (value) -> prn(value));
		}
		
		if (false) {
			prnTitle("Merge two hashMaps:");
			ConcurrentHashMap<Integer, String> chm2 = new ConcurrentHashMap<Integer, String>();
			chm2.put(11, "1100");
			chm2.put(12, "1200");
			chm2.put(13, "1300");
			chm2.put(1, "aaa");
			chm2.put(2, "bbb");
			chm2.put(3, "ccc");
			chm2.forEach(  
	                (key, value) -> chm.merge( key, value, (v1, v2) -> v1.equalsIgnoreCase(v2) ? v1 : v1 + "_" + v2));
			System.out.println(chm);
		}

		if (false) {
			prnTitle("Reduce the HashMap into one String:");
			String result = chm.reduce(1, (k, v) -> v = v + "   ", (t, v) -> t + v);
			System.out.println(result);
		}

		if (false) {
			prnTitle("Find the value 800:");
			Integer key = chm.search(2, (k, v) -> v.equals("800") ? k : null);
			System.out.println(key);
			// searchEntries
			// searchKeys
			// searchValues
		}

	}

	// Custom Lambda Function
	class PutInHashMap implements Function<Integer, Integer> {
		
		boolean display;
		public PutInHashMap(boolean display) {
			this.display = display;
		}
		
		
		public Integer apply(Integer key) {

			if (this.display) prn(key);
			
			String value = "" + (key * 100);
			chm.put(key, value);
			return 0;
		}
	}
	

	/////////////////////////////////////
	// Supporting

	private synchronized static void prnTitle(String title) {
		System.out.print("\n\n");
		System.out.println(title);
		System.out.println("---");
	}	
	
	private synchronized static void prn(Object obj) {
		System.out.print(Thread.currentThread().getName());
		System.out.print(" ... ");
		System.out.print(obj);
		System.out.println();
	}

	public static void main(String[] args) {

		try {
			Example homework = new Example();
			homework.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
