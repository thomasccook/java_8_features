package feature03_lambda_expressions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Solution {

	public Solution() {
		
		List<Person> list = this.createList();
		
		prn("Java 1 - for loop");
		for(int i=0; i<list.size(); i++) {
			Person p = list.get(i);
			prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
		}

		prn("Java 2 - Iterator");
		Iterator<Person> it = list.iterator();
		while(it.hasNext()){
			Person p = it.next();
			prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
		}
		
		prn("Java 5 - for - Generics");	
		for (Person p: list) {
			prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
		}
		
		
		//////////////////////////////////////////////////////
		// New method - Inner class
		
		prn("Java 8 - forEach - Concrete class");
		list.forEach(new PersonConsumer());
		
		prn("Java 8 - forEach - Anonymous Inline class");
		Consumer<Person> consumer = new Consumer<Person>() {
		    public void accept(Person p) {
		        prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
		    }
		};
		list.forEach(consumer);
		
		prn("Java 8 - forEach - Anonymous Class");
		list.forEach(new Consumer<Person>() {
			public void accept(Person p) {
				prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
			}
		});		
		
		prn("Java 8 - forEach - Lambda expression");
		list.forEach(p -> {
		    prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
		});

		prn("Java 8 - forEach - Method Reference - Locally defined");
		list.forEach(myFunctionReference);			
		
		
	}
			
	// Consumer implementation that can be reused
	class PersonConsumer implements Consumer<Person>{
		public void accept(Person p) {
			prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
		}
	}
	
	Consumer<Person> myFunctionReference = p -> prn(p.getFirstName().substring(0,1) + " " + p.getLastName());
	
	
	//////////////////////////////////////////
	// Supporting functions
	
	class Person {
		private String firstName;
		private String lastName;
		
		public Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}		
		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
	}	
	
	
	public List<Person> createList() {
		List<Person> list = new ArrayList<Person>();
		list.add(new Person("Brenden","Acosta"));
		list.add(new Person("Rafe","Chung"));
		list.add(new Person("Konnor","Lott"));
		list.add(new Person("Fahima","Curtis"));
		list.add(new Person("Fenton","Baldwin"));
		list.add(new Person("Teejay","Wood"));
		list.add(new Person("Eva-Rose","Major"));
		list.add(new Person("Charlize","Wallace"));
		list.add(new Person("Maximillian","Buck"));
		list.add(new Person("Leia","Zavala"));
		return list;
	}		
	
	// A more pleasant looking print function
	private void prn(Object obj) {
		System.out.println(obj);
	}	
	
	// Program begins here
	public static void main(String[] args) {
		new Solution();
	}
	
}






/* Output

Java 1 - for loop
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 2 - Iterator
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 5 - for - Generics
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 8 - forEach - Concrete class
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 8 - forEach - Anonymous Inline class
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 8 - forEach - Anonymous Class
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 8 - forEach - Lambda expression
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala
Java 8 - forEach - Method Reference - Locally defined
B Acosta
R Chung
K Lott
F Curtis
F Baldwin
T Wood
E Major
C Wallace
M Buck
L Zavala

 */
