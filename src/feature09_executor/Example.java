package feature09_executor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;  

public class Example {

	private static int TASK_COUNT = 12;
	
	ConcurrentHashMap<Integer, Integer> chm = new ConcurrentHashMap<Integer, Integer>();
	
    public void process() {
        
    	int numberOfProcessors = Runtime.getRuntime().availableProcessors();

    	if (true) {
        	System.out.println("*** newCachedThreadPool() ***");
    		// Creates a thread pool that creates new threads as needed, but will reuse previously constructed 
    		// threads when they are available.
        	ExecutorService executorService = Executors.newCachedThreadPool();
        	queueManyTasks(executorService);
        	executorService.shutdown();
    	}

    	if (false) {
        	System.out.println("*** newFixedThreadPool(...) ***");
    		// Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue.
        	ExecutorService executorService = Executors.newFixedThreadPool(3);
        	queueManyTasks(executorService);
        	executorService.shutdown();
    	}
    	
    	if (false) {
        	System.out.println("*** newSingleThreadExecutor() ***");
    		// Creates an Executor that uses a single worker thread operating off an unbounded queue.
        	ExecutorService executorService = Executors.newSingleThreadExecutor();
        	queueManyTasks(executorService);
        	executorService.shutdown();
    	}

    	if (false) {
        	System.out.println("*** newScheduledThreadPool(...) ***");
    		// Creates a thread pool that can schedule commands to run after a given delay, or to execute periodically.
        	ExecutorService executorService = Executors.newScheduledThreadPool(3);
        	queueManyTasks(executorService);
        	executorService.shutdown();
    	}
    	
    	// New to Java 8 ... totally different than the other functions.
    	if (false) {
        	System.out.println("*** newWorkStealingPool() ***");
    		// Creates a thread pool that maintains enough threads to support the given parallelism level, and 
    		// may use multiple queues to reduce contention.
    		// Uses ForkJoinPool
        	ExecutorService executorService = Executors.newWorkStealingPool(numberOfProcessors);
        	queueManyTasks(executorService);
        	//executorService.shutdown();
        	while(chm.size() < TASK_COUNT) {}
    	} 
    	
    	System.out.println("Done on " + Thread.currentThread().getName());    
    }
 
    private void queueManyTasks(ExecutorService executorService) {
    	for (int i=1; i <= TASK_COUNT; i++) {
            executorService.execute(new Task(i));   
    	}
    }
    
    class Task implements Runnable {
        int num;
        public Task(int num) {
            this.num = num;
        }
        @Override
        public void run() {
        	double pauseTime = 0.5 * (double)num;
            pause(pauseTime, pauseTime);
            System.out.println("Task #"+num + " on " + Thread.currentThread().getName());
            chm.put(num, num);
        }
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
