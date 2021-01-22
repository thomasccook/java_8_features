package feat01_forEach;

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
public class Feat01_ForEach {

	
	public Feat01_ForEach() {
		
		System.out.println("Java 5 - List with Generic");
		List<Integer> listNumbers = this.createList();
		for(int i=0; i<listNumbers.size(); i++) {
			System.out.println(listNumbers.get(i) );
		}

		System.out.println("Java 5 - Iterator with Generic");
		Iterator<Integer> it = listNumbers.iterator();
		while(it.hasNext()){
			Integer i = it.next();
			System.out.println(i);
		}
		
		System.out.println("Java 8 - forEach - Anonymous Class");
		listNumbers.forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.println(t);
			}
		});
		
		System.out.println("Java 8 - forEach - Named Class");
		MyConsumer action = new MyConsumer();
		listNumbers.forEach(action);
	}
	
	//Consumer implementation that can be reused
	class MyConsumer implements Consumer<Integer>{
		public void accept(Integer t) {
			System.out.println(t);
		}
	}
	
	
	public List<Integer> createList() {
		List<Integer> myList = new ArrayList<Integer>();
		for(int i=0; i<10; i++) {
			myList.add(i * 10);
		}
		return myList;
	}
	
	
	
	
	public static void main(String[] args) {
		new Feat01_ForEach();
	}
	
	
}
