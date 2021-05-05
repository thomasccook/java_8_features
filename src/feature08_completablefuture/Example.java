package feature08_completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example {

	
	public void process() throws ExecutionException, InterruptedException {

		if (false) {
			
			prn("m1");
			
			String s1 = "AAA";
			s1 += "BBB";
			s1 += "CCC";

			String s2 = "DDD";
			s2 += "EEE";
			s2 += "FFF";
			
			prn("s1 Returned Value- " + s1);		  
			prn("s2 Returned Value- " + s2);			
		}
		
		if (false) {
		
			prn("m1");
			
			CompletableFuture<String> cf1 = CompletableFuture
					.supplyAsync(   () -> {pause(1); prn("m2"); return "AAA";})
					.thenApplyAsync( s -> {pause(1); prn("m3"); return s + "BBB";})
					.thenApplyAsync( s -> {pause(1); prn("m4"); return s + "CCC";});
			  
			CompletableFuture<String> cf2 = CompletableFuture
					.supplyAsync(   () -> {pause(1); prn("m5"); return "DDD";})
					.thenApplyAsync( s -> {pause(1); prn("m6"); return s + "EEE";})
					.thenApplyAsync( s -> {pause(3); prn("m7"); return s + "FFF";});
			
			prn(cf1.get() + " - " + cf2.get());
		}
		
		
		if (true) {
			
			prn("m1");
			
			ExecutorService executor = Executors.newFixedThreadPool(6);
			  
			CompletableFuture<String> cf1 = CompletableFuture
					.supplyAsync(   () -> {pause(1); prn("m2"); return "AAA";}, executor)
					.thenApplyAsync( s -> {pause(1); prn("m3"); return s + "BBB";}, executor)
					.thenApplyAsync( s -> {pause(1); prn("m4"); return s + "CCC";}, executor);
			   
			CompletableFuture<String> cf2 = CompletableFuture
					.supplyAsync(   () -> {pause(1); prn("m5"); return "DDD";}, executor)
					.thenApplyAsync( s -> {pause(1); prn("m6"); return s + "EEE";}, executor)
					.thenApplyAsync( s -> {pause(3); prn("m7"); return s + "FFF";}, executor);
			
			prn(cf1.get() + " - " + cf2.get());	
		}		  
		  

	}

	
	private synchronized static void prn(Object obj) {
		System.out.print(obj);
		System.out.print(" ... ");
		System.out.print(Thread.currentThread().getName());
		System.out.println();
	}	
	
	private void pause(long seconds) {
		try {
			Thread.currentThread().sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		Example cfDemo = new Example();
		try {
			// blocking call
			cfDemo.process();
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
