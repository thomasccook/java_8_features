package feature09_executor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;  

public class Challenge {

	private static int TASK_COUNT = 12;
	
	ConcurrentHashMap<Integer, Integer> chm = new ConcurrentHashMap<Integer, Integer>();
	
    public void process() {
        
    	int numberOfProcessors = Runtime.getRuntime().availableProcessors();

    	
    	
    	System.out.println("Done on " + Thread.currentThread().getName());    
    }
		
    ///////////////////////////////////////////////
    // Supporting Functions
    
	private void pause(double min, double max) {
		try {
			long seconds = (long) (min + (max - min) * Math.random());
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
}
