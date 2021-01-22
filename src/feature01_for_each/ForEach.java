package feature01_for_each;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/*
 * Here are four different ways of iterating through a list of Objects, ranging from Java 1 to Java 8.
 * 
 * Derived from https://www.journaldev.com/2389/java-8-features-with-examples#iterable-forEach
 * 
 */
public class ForEach {

		
	public ForEach() {
		
		List<Integer> list = this.createList();
		
		print("Java 1 - for");
		for(int i=0; i<list.size(); i++) {
			print(list.get(i));
		}

		print("Java 2 - Iterator");
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			Integer i = it.next();
			print(i);
		}
		
		print("Java 8 - forEach - Concrete Class");
		MyConsumer action = new MyConsumer();
		list.forEach(action);
		
		print("Java 8 - forEach - Anonymous Inner Class");
		list.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				print(t);
			}
		});		
		
	}
	
	// A list of numbers
	public List<Integer> createList() {
		List<Integer> myList = new ArrayList<Integer>();
		for(int i=0; i<10; i++) {
			myList.add(i);
		}
		return myList;
	}		
	
	// Consumer implementation that can be reused
	class MyConsumer implements Consumer<Integer>{
		public void accept(Integer t) {
			print(t);
		}
	}
	
	// A more pleasant looking print function
	private void print(Object obj) {
		System.out.println(obj);
	}	
	
	// Program begins here
	public static void main(String[] args) {
		new ForEach();
	}
	
	
}
