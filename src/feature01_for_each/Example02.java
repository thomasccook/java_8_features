package feature01_for_each;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

/*
 * This example demonstrates that older loop method can conveniently leave a loop and skip iterations,
 * but forEach can only leave a loop by throwing an exception.
 * 
 */
public class Example02 {

	public Example02() {
		List<Integer> list = this.createList();
		
		//////////////////////////////////////////////////////
		// Old method - loop modifications 
		
		prn("Java 5 - leave a loop");	
		for (Integer i: list) {
			if (i == 5) {
				break;
			}
			prn(i);			
		}
		
		prn("Java 5 - skip a value");
		for (Integer i: list) {
			if (i == 5) {
				continue;
			}
			prn(i);			
		}
		
		//////////////////////////////////////////////////////
		// New method - returning from a loop
		
		prn("Java 8 - forEach - can't use break or continue ... and return just leaves the current function and continues to the next");
		Consumer<Integer> consumer1 = new Consumer<Integer>() {
			public void accept(Integer i) {
	        	if (i == 5) {
	        		return;
	        	}
	            prn(i);
		    }
		};
		list.forEach(consumer1);		
		
		prn("Java 8 - forEach - to leave a loop, must throw an Exception");
		Consumer<Integer> consumer2 = new Consumer<Integer>() {
			public void accept(Integer i) {
		        try {
		        	if (i == 5) {
		        		throw new Exception();
		        	}
		            prn(i);
		        } catch (final Exception e) {
		            // Implement your own exception handling logic here..
		            // For example:
		            System.out.println("handling an exception...");
		            // Or ...
		            throw new RuntimeException(e);
		        }
		    }
		};
		list.forEach(consumer2);
		
		
		// Conculsion: 
		// You cannot think of Old loops and the new forEach loop as being identical.
		// Old loops are more powerful, forEach loops are more convenient
		
		
	}

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
		new Example02();
	}		
	
}


/* Output
 
Java 5 - leave a loop
0
1
2
3
4
Java 5 - skip a value
0
1
2
3
4
6
7
8
9
Java 8 - forEach - can't use break or continue ... return just leaves the current function and continues
0
1
2
3
4
6
7
8
9
Java 8 - forEach - to leave a loop, must throw an Exception
0
1
2
3
4
handling an exception...
Exception in thread "main" java.lang.RuntimeException: java.lang.Exception
	at feature01_for_each.Example02$2.accept(Example02.java:66)
	at feature01_for_each.Example02$2.accept(Example02.java:1)
	at java.util.ArrayList.forEach(ArrayList.java:1249)
	at feature01_for_each.Example02.<init>(Example02.java:70)
	at feature01_for_each.Example02.main(Example02.java:99)
Caused by: java.lang.Exception
	at feature01_for_each.Example02$2.accept(Example02.java:58)
	... 4 more
  
  
 */



