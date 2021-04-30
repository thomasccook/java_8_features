package feature07_concurrency_api_improvements;

import java.util.concurrent.ConcurrentHashMap;

public class Challenge {

	private ConcurrentHashMap<Integer, Boolean> chm = new ConcurrentHashMap<Integer, Boolean>();

	private int MAX_NUMBER = 100;
	private long PARALELLISM_THRESHOLD = 1;
	private boolean countingFinished = false;

	public void process() {

		prn("Looking for Primes:\n\n");

		(new Thread_AddToHashMap()).start();
		pause(1);
		(new Thread_Report()).start();
		pause(1);
		(new Thread_MarkNonPrimes()).start();
		pause(1);
		(new Thread_RemoveNonPrimes()).start();
	}

	// Insert a new number every 1 seconds.
	class Thread_AddToHashMap extends Thread {
		public void run() {
			int n = 1;
			while (n < MAX_NUMBER) {
				pause(1);
				chm.put(n, true);
				n++;
			}
			countingFinished = true;
		}
	}

	// Report contents every 2 seconds
	class Thread_Report extends Thread {
		public void run() {
			while (!countingFinished) {
				pause(2);
				chm.forEachEntry(PARALELLISM_THRESHOLD, (entry) -> prn(entry.getKey()));
				prn("\n");
			}

			prn("\n");
			prn("Counting Finished. Waiting for other threads to finish.\n");
			pause(10);

			// Print results
			prn("\n");
			prn("Finished:\n");
			chm.keySet().stream().sorted().forEach(el -> prn(el + ", "));

			prn("\n");
			prn("Expected:\n");
			prn("1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97");

		}
	}

	// Find non-primes every 3 seconds
	class Thread_MarkNonPrimes extends Thread {
		public void run() {
			while (countingFinished == false) {
				pause(3);
				prn("Marking  non-primes\n");
				for (int n = 2; n < chm.size() / 2; n++) {
					final int denominator = n;
					chm.forEachKey(PARALELLISM_THRESHOLD, (key) -> {
						if (key != denominator && key % denominator == 0) {
							chm.put(key, false);
						}
					});
				}
			}
		}
	}

	// Remove non-primes every 4 seconds
	class Thread_RemoveNonPrimes extends Thread {
		public void run() {
			while (countingFinished == false) {
				pause(4);
				prn("Removing non-primes\n");
				chm.forEachKey(PARALELLISM_THRESHOLD, (key) -> {
					if (chm.get(key) == false) {
						chm.remove(key);
					}
				});
			}
		}
	}

	/////////////////////////////////////
	// Supporting

	private void pause(long seconds) {
		try {
			Thread.currentThread().sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized static void prn(Integer number) {
		System.out.print(number + ", ");
	}

	private synchronized static void prn(String str) {
		System.out.print(str);
	}

	public static void main(String[] args) {
		(new Challenge()).process();
	}

}
