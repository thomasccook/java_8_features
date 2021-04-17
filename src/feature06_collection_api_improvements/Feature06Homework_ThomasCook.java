package feature06_collection_api_improvements;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

enum Mode {
	SINGLE_THREAD,
	DEFAULT_THREAD_POOL,
	CUSTOM_THREAD_POOL
}

class Params {
	public static Mode mode = Mode.CUSTOM_THREAD_POOL;
	
	public static int[] horseCount = { 100 };
	// public static int[] horseCount = {50, 100, 150, 200, 250, 300, 350, 400, 450, 500};
	
	// Results
	// https://docs.google.com/spreadsheets/d/1m7wrULmSs3RVI1QZDL00hC9za5X-yGOSgUJkywONup4/edit?usp=sharing
}

public class Feature06Homework_ThomasCook {

	int HORSES_PER_RACE = 5;
	
	int MIN_RACE_TIME = 1000;
	int MAX_RACE_TIME = 5000;

	public double process(int horseCount) throws InterruptedException, ExecutionException {
		prn("Begin Horse Races\n");
		
		if (Params.horseCount.length == 1) {
			pause(10); // Gives us 10 seconds to turn on the profiler
		}
		
		// Make a bunch of horses with different speeds
		List<Integer> listHorses =
			    IntStream.rangeClosed(1, horseCount)
			        .boxed()
			        .collect(Collectors.toList());
		Collections.shuffle(listHorses);

		int round = 1;
		long t0 = System.currentTimeMillis();
		while (listHorses.size() > 1) {
			prn("*** Round " + round + " ***\n");
			
			prn("Horses:\n");
			listHorses.stream().forEach(el -> prn(el + ", "));
			prn("\n");
			
			pause(1);
			
			// Subdivide them into races with no more than 5 horses each.
			// https://e.printstacktrace.blog/divide-a-list-to-lists-of-n-size-in-Java-8/
			AtomicInteger counter = new AtomicInteger();
			Collection<List<Integer>> listRaces = listHorses
					.stream()
					.collect(Collectors.groupingBy(it -> counter.getAndIncrement() / HORSES_PER_RACE))
					.values();
			prn("Races:");
			listRaces.stream().forEach(el -> prn("\n" + el));
			prn("\n");

			pause(1);
			
			// Do the races
			prn("Results:\n");
			Race race = new Race();
			List<Integer> listWinners = null;
			if (Params.mode == Mode.SINGLE_THREAD) {
				listWinners = listRaces
					.stream()
					.map(race)
					.collect(Collectors.toList());
			} 
			else if (Params.mode == Mode.DEFAULT_THREAD_POOL) {
				listWinners = listRaces
					.parallelStream()
					.map(race)
					.collect(Collectors.toList());
			} 
			else if (Params.mode == Mode.CUSTOM_THREAD_POOL) {
				ForkJoinPool customThreadPool = null;
				try {
					customThreadPool = new ForkJoinPool(listRaces.size());
					listWinners = customThreadPool.submit(() -> listRaces
							.parallelStream()
							.map(race)
							.collect(Collectors.toList())).get();
				} finally {
					if (customThreadPool != null)
						customThreadPool.shutdown();
				}


			}
			
			prn("\n\n");
			listHorses = listWinners;
			round++;
		}
		long t1 = System.currentTimeMillis();
		double lapseTime = ((double)(t1 - t0)) / 1000;
		return lapseTime;
		
	}

	// Custom Lambda Function
	class Race implements Function<List<Integer>, Integer> {
		public Integer apply(List<Integer> listHorses) {
			waitForRaceToFinish();
			int winner = listHorses.stream().max(Integer::compare).get();
			prn(winner + " ");
			return winner;
		}
		
		private void waitForRaceToFinish() {
			try {
				int racetime = MIN_RACE_TIME + (int)((MAX_RACE_TIME - MIN_RACE_TIME) * Math.random()); // 1-5 seconds
				Thread.sleep(racetime);
			} catch (Exception e) {
			}
		}
		
	}
	

	/////////////////////////////////////
	// Supporting
	
	private static void prn(Object obj) {
		if (Params.horseCount.length > 1) return;
		System.out.print(obj);
	}

	private void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {			
		}
	}
	
	public static void main(String[] args) {

		try {
			Feature06Homework_ThomasCook homework = new Feature06Homework_ThomasCook();
			for (int i =0; i < Params.horseCount.length; i++) {
				double runtime = homework.process(Params.horseCount[i]);
				Feature06Homework_ThomasCook.prn("Runtime: ");
				System.out.println(runtime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}

}
