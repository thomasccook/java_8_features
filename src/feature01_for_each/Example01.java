package feature01_for_each;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/*
 * Here are different way of iterating through an List, ranging from Java 1 to Java 8.
 */
public class Example01 {

	public Example01() {
		List<Integer> list = this.createList();
		
		//////////////////////////////////////////////////////
		// Old methods - No inner class
		
		prn("Java 1 - for loop");
		for(int i=0; i<list.size(); i++) {
			prn(list.get(i));
		}

		prn("Java 2 - Iterator");
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			Integer i = it.next();
			prn(i);
		}
		
		prn("Java 5 - for - Generics");	
		for (Integer i: list) {
			prn(i);
		}
		
		
		//////////////////////////////////////////////////////
		// New method - Inner class
		
		prn("Java 8 - forEach - Concrete class");
		list.forEach(new MyConsumer());
		
		prn("Java 8 - forEach - Anonymous Inline class");
		Consumer<Integer> consumer = new Consumer<Integer>() {
		    public void accept(Integer i) {
		        prn(i);
		    }
		};
		list.forEach(consumer);
		
		prn("Java 8 - forEach - Anonymous Class");
		list.forEach(new Consumer<Integer>() {
			public void accept(Integer i) {
				prn(i);
			}
		});		
		
		prn("Java 8 - forEach - Lambda expression");
		list.forEach(i -> {
		    prn(i);
		});

		prn("Java 8 - forEach - Method Reference - Defined by me");
		list.forEach(myFunctionReference);			
		
		prn("Java 8 - forEach - Method Reference - Third Party");
		list.forEach(System.out::println);
		

		// In brief:
//		Instead of using
//		A CONCRETE CLASS
//		you can use
//		AN ANONYMOUS CLASS
//		or
//		A LAMBDA EXPRESSION
//		And if this just calls one method, you can use
//		A METHOD REFERENCE
		
		
	}
	
	// Resusable implementation
	class MyConsumer implements Consumer<Integer>{
		public void accept(Integer i) {
			prn(i);
		}
	}
	
	// Method Reference
	Consumer<Integer> myFunctionReference = i -> prn(i);
		

	//////////////////////////////////////////
	// Supporting functions
		
	// A list of numbers
	public List<Integer> createList() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0; i<10; i++) {
			list.add(i);
		}
		return list;
	}		
	
	// A more pleasant looking print function
	private void prn(Object obj) {
		System.out.println(obj);
	}		
	
	// Program begins here
	public static void main(String[] args) {
		new Example01();
	}		
	
}


/* Output
 
Java 1 - for loop
0
1
2
3
4
5
6
7
8
9
Java 2 - Iterator
0
1
2
3
4
5
6
7
8
9
Java 5 - for - Generics
0
1
2
3
4
5
6
7
8
9
Java 8 - forEach - Concrete class
0
1
2
3
4
5
6
7
8
9
Java 8 - forEach - Anonymous Inline class
0
1
2
3
4
5
6
7
8
9
Java 8 - forEach - Anonymous Class
0
1
2
3
4
5
6
7
8
9
Java 8 - forEach - Lambda expression
0
1
2
3
4
5
6
7
8
9
Java 8 - forEach - Method Reference - Locally defined
0
1
2
3
4
5
6
7
8
9
Java 8 - forEach - Method Reference - Third Party
0
1
2
3
4
5
6
7
8
9
  
  
 */



