package feature08_completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Challenge {

	public void process() throws ExecutionException, InterruptedException {
	  
		 
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
		Example example = new Example();
		try {
			// blocking call
			example.process();
		} catch (ExecutionException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
