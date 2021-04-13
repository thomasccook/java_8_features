package feature06_collection_api_improvements;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Feature06Homework {

	int HORSE_COUNT = 25; // Increase to 100
	int HORSES_PER_RACE = 5;
	
	int MIN_SPEED = 5; // 5 mph
	int MAX_SPEED = 45; // 45 mph
	
	public void process() {
		
		// Initial list of horses
		List<Integer> listWinner = IntStream
				.rangeClosed(1, HORSE_COUNT)
				.map(p -> MIN_SPEED + (int)((MAX_SPEED - MIN_SPEED) * Math.random()))
				.boxed()
				.collect(Collectors.toList());

		int round = 1;
		do {
			prn("*** Round " + round + " ***\n");
			prn("Horse speeds: ");
			listWinner.forEach(el -> prn(el + " "));
			prn("\n");

			Spliterator<Integer> spliterator = listWinner.stream().spliterator();
			listWinner = race(spliterator);

			prn("\n");
			round++;
		} while (listWinner.size() > 1);

		prn("*** Winner! *** \n");
		listWinner.forEach(p -> prn(p));
	}

	public List<Integer> race(Spliterator<Integer> splt1) {

		if (splt1.estimateSize() <= HORSES_PER_RACE) {

			List<Integer> horses = StreamSupport.stream(splt1, false).collect(Collectors.toList());

			horses.forEach(p -> prn(p + " "));
			prn(" ----> ");

			try {
				int racetime = 1000;
				//int racetime = 1000 + (int)(4000 * Math.random()); // 1-5 seconds
				Thread.sleep(racetime);
			} catch (Exception e) {
				
			}
			
			Integer fastest = horses.stream().max(Integer::compare).get();
			
			prn(fastest);
			prn("\n");

			return Arrays.asList(fastest);

		} else {
			// This split
			Spliterator<Integer> splt2 = splt1.trySplit();
			List<Integer> list1 = race(splt1);
			List<Integer> list2 = race(splt2);

			List<Integer> listWinner = Stream.concat(list1.stream(), list2.stream()).collect(Collectors.toList());

			return listWinner;

		}

	}

	/////////////////////////////////////
	// Supporting
	
	private static void prn(Object obj) {
		System.out.print(obj);
	}

	public static void main(String[] args) {

		(new Feature06Homework()).process();

	}

}
