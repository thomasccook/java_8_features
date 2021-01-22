package feature01_for_each;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class C_Solution {

	
	public C_Solution() {
		
		List<Person> list = this.createList();
		
		print("Java 1 - for");
		for(int i=0; i<list.size(); i++) {
			Person p = list.get(i);
			print(p.getFirstName().substring(0,1) + " " + p.getLastName());
		}

		print("Java 2 - Iterator");
		Iterator<Person> it = list.iterator();
		while(it.hasNext()){
			Person p = it.next();
			print(p.getFirstName().substring(0,1) + " " + p.getLastName());
		}
		
		print("Java 8 - forEach - Concrete Class");
		MyConsumer action = new MyConsumer();
		list.forEach(action);
		
		print("Java 8 - forEach - Anonymous Inner Class");
		list.forEach(new Consumer<Person>() {
			public void accept(Person p) {
				print(p.getFirstName().substring(0,1) + " " + p.getLastName());
			}
		});		
		
	}
			
	// Consumer implementation that can be reused
	class MyConsumer implements Consumer<Person>{
		public void accept(Person p) {
			print(p.getFirstName().substring(0,1) + " " + p.getLastName());
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
	private void print(Object obj) {
		System.out.println(obj);
	}	
	
	// Program begins here
	public static void main(String[] args) {
		new C_Solution();
	}
	
}


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



/*

Java 1 - for
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
Java 8 - forEach - Concrete Class
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
Java 8 - forEach - Anonymous Inner Class
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
