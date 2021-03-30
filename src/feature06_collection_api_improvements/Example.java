package feature06_collection_api_improvements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;

import org.junit.Test;

//Collection API Improvements
public class Example {

			
	/////////////////////////////////////////////////
	// Iterator
	
	//@Test
	public void iterator_forEachRemaining() {

		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));

		Iterator<Integer> iter = list.iterator();
		
		iter.next();
		iter.next();
		iter.next();
		
		// java.util.function.Consumer
		iter.forEachRemaining(n -> prn(n));
	}

	/////////////////////////////////////////////////
	// Collection	
	
	// @Test
	public void collection_removeIf() {

		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        
		// java.util.function.Predicate
		list.removeIf(n -> (n % 3 == 0)); 

		prn(list);
	}
	
	/////////////////////////////////////////////////
	// Spliterator
	// The Spliterator interface, introduced in Java 8, can be used for traversing and partitioning sequences
	// If you want to process a stream in parallel, it is the spliterator which dictates how to split the stream, if possible.

	// @Test
	public void spliterator_tryAdvance() {

		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        
		Spliterator<Integer> spliterator = list.spliterator();
		
		// java.util.function.Consumer
		spliterator.tryAdvance(n -> prn(n)); 
		spliterator.tryAdvance(n -> prn(n)); 
		spliterator.tryAdvance(n -> prn(n)); 
		
		while (spliterator.tryAdvance(n -> prn(n))) {
			prn("Found another");
		}
		prn("No more");

	}
	
	// The trySplit method tries to split it into two parts.
	// @Test
	public void spliterator_trySplit() {

		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        
		Spliterator<Integer> splt1 = list.spliterator();
		Spliterator<Integer> splt2 = splt1.trySplit();
		
		splt1.forEachRemaining(n -> prn(n));
		prn();
		splt2.forEachRemaining(n -> prn(n));

	}	
	
	// @Test
	public void spliterator_estimateSize() {

		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        
		Spliterator<Integer> splt1 = list.spliterator();
		Spliterator<Integer> splt2 = splt1.trySplit();

		prn(splt1.estimateSize());
		prn(splt2.estimateSize());
	}	
	
	// https://richardstartin.github.io/posts/spliterator-characteristics-and-performance
	// @Test
	public void spliterator_hasCharacteristics() {

		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        
		Spliterator<Integer> splt = list.spliterator();

		prn("ORDERED = ", splt.hasCharacteristics(Spliterator.ORDERED));
		prn("DISTINCT = ",splt.hasCharacteristics(Spliterator.DISTINCT));
		prn("SORTED = ",splt.hasCharacteristics(Spliterator.SORTED));
		prn("SIZED = ",splt.hasCharacteristics(Spliterator.SIZED));
		prn("NONNULL = ",splt.hasCharacteristics(Spliterator.NONNULL));
		prn("IMMUTABLE = ",splt.hasCharacteristics(Spliterator.IMMUTABLE));
		prn("CONCURRENT = ",splt.hasCharacteristics(Spliterator.CONCURRENT));
		prn("SUBSIZED = ",splt.hasCharacteristics(Spliterator.SUBSIZED));
	}	
	
	//	ORDERED promises that there is an order. For instance, trySplit is guaranteed to give a prefix of elements.
	//	DISTINCT a promise that each element in the stream is unique.
	//	SORTED a promise that the stream is already sorted.
	//	SIZED promises the size of the stream is known. This is not true when a call to iterate generates the stream.
	//	NONNULL promises that no elements in the stream are null.
	//	IMMUTABLE promises the underlying data will not change.
	//	CONCURRENT promises that the underlying data can be modified concurrently. Must not also be IMMUTABLE.
	//	SUBSIZED promises that the sizes of splits are known, must also be SIZED.
	
	
	/////////////////////////////////////////////////
	// Map
	
	// https://www.geeksforgeeks.org/hashmap-replaceallbifunction-method-in-java-with-examples/
	// @Test
	public void map_replaceAll() {

		HashMap<String, Integer> map1 = new HashMap<>(); 
		map1.put("key1", 1); 
		map1.put("key2", 2); 
		map1.put("key3", 3); 
		map1.put("key4", 4); 
		
		prn(map1);
		
		prn();
		
		// java.util.function.BiFunction
		map1.replaceAll((key, oldValue) -> oldValue * oldValue); 
		  
		prn(map1);
	}	
	
	// https://www.geeksforgeeks.org/hashmap-compute-method-in-java-with-examples/
	// @Test
	public void map_compute() {

		Map<String, String> map = new HashMap<>(); 
		map.put("Name", "Aman"); 
		map.put("Address", "Kolkata"); 
		
		prn(map);
		  
		        // java.util.function.BiFunction
		map.compute("Name", (key, val) -> val.concat(" Singh")); 
		map.compute("Address", (key, val)  -> val.concat(" West-Bengal")); 
		  
		prn(map);
	}	
	
	// https://www.geeksforgeeks.org/hashmap-mergekey-value-bifunction-method-in-java-with-examples/
	// @Test
	public void map_merge() {

		HashMap<Integer, String> map = new HashMap<>(); 
		map.put(1, "L"); 
		map.put(2, "M"); 
		map.put(3, "N"); 
		  
		prn(map);
		
		map.merge( 1, "A", (v1, v2)  -> {return v1 + "_" + v2;});
		map.merge( 2, "B", (v1, v2)  -> {return v1 + "_" + v2;});
		map.merge( 3, "C", (v1, v2)  -> {return v1 + "_" + v2;});
		  
		// print new mapping 
		prn(map);		
	}	
	
	/////////////////////////////////////////////////////
	// Helper Functions
	
	private static void prn() {
		System.out.println("---");
	}

	// A more pleasant looking print function
	private static void prn(Object obj) {
		System.out.println(obj);
	}	
	
	// A more pleasant looking print function
	private static void prn(String title, Object obj) {
		System.out.print(title + " = ");
		System.out.println(obj);
	}	

		
}