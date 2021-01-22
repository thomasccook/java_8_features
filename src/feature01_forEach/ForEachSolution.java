package feature01_forEach;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/*
 * Iterating through a list of objects
 * 
 * Challenge: create a class that contains fields (firstName, lastName) and sort by "lastName + ' ' + firstName"
 * 
 * 
 */

// https://www.journaldev.com/2389/java-8-features-with-examples#iterable-forEach
public class ForEachSolution {

	
	// A list of numbers
	public List<Integer> createList() {
		List<Integer> myList = new ArrayList<Integer>();
		for(int i=0; i<10; i++) {
			myList.add(i * 10);
		}
		return myList;
	}	
	
	
	public ForEachSolution() {
		
		List<Integer> listNumbers = null;
		
		p("Java 5 - List with Generic");
		listNumbers = this.createList();
		for(int i=0; i<listNumbers.size(); i++) {
			System.out.println(listNumbers.get(i) );
		}

		p("Java 5 - Iterator with Generic");
		listNumbers = this.createList();
		Iterator<Integer> it = listNumbers.iterator();
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println(i);
		}
		
		p("Java 8 - forEach - Anonymous Class");
		listNumbers = this.createList();
		listNumbers.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.println(t);
			}
		});
		
		p("Java 8 - forEach - Named Class");
		listNumbers = this.createList();
		MyConsumer action = new MyConsumer();
		listNumbers.forEach(action);
	}
	
	//Consumer implementation that can be reused
	class MyConsumer implements Consumer<Integer>{
		public void accept(Integer t) {
			System.out.println(t);
		}
	}
	
	

	private void p(String str) {
		System.out.println(str);
	}
	
	
	
	public static void main(String[] args) {
		new ForEachSolution();
	}
	
	
}
