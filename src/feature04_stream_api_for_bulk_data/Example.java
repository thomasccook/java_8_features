package feature04_stream_api_for_bulk_data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Example {

	class Person {
		private String firstName;
		private String lastName;
		private int age;
		private String gender;

		public Person(String firstName, String lastName, int age, String gender) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
			this.gender = gender;
		}
		
		public String getFirstName() {return firstName;}
		public void setFirstName(String firstName) {this.firstName = firstName;}
		public String getLastName() {return lastName; }
		public void setLastName(String lastName) {this.lastName = lastName;}
		public int getAge() {return age;}
		public void setAge(int age) {this.age = age;}
		public String getGender() {return gender;}
		public void setGender(String gender) {this.gender = gender;}

		public String toString() {
			return this.firstName + "\t\t" + this.lastName + "\t\t" + age + "\t" + gender;
		}

	}	
	
	public Person[] listPerson = {
			new Person("Laiba","White", 65, "F"),
			new Person("Blair","Reed", 7, "F"),
			new Person("Robin","White", 12, "M"),
			new Person("Omari","Macias", 33, "M"),
			new Person("Danny","Macias", 52, "F"),
			new Person("Sanjay","Perkins", 29, "M"),
			new Person("Ally","White", 9, "M"),
			new Person("Luis","Rodam", 15, "M"),
			new Person("Eesa","Reed", 23, "F"),
			new Person("Matilda","Perkins", 42, "F"),
			new Person("Ronaldo","Reed", 80, "M"),
			new Person("Jaimee","Perkins", 72, "M"),
			new Person("Donovan","White", 74, "M"),
			new Person("Aleisha","Reed", 46, "F"),
			new Person("Mark","Rodam", 8, "M"),
			new Person("Bev","Macias", 21, "F"),
			new Person("Roan","Perkins", 28, "M"),
			new Person("Sammy","Perkins", 26, "M"),
			new Person("Raphael","Macias", 62, "M"),
			new Person("Pablo","Rodam", 16, "M"),
	};
			
	/////////////////////////////////////////////////
	// Stream or Parallel Stream
	
	// Stream - data in order - Single Thread
	public void example01() {
		if(true) return;;
		List<Person> list = Arrays.asList(this.listPerson);
		
		// Order in matches order out.
		list
		.stream()
		.forEach(p -> prn(p));
	}
	
	// Parallel Stream - data not in order - Multiple Threads (I guess)
	public void example02() {
		if(true) return;;
		List<Person> list = Arrays.asList(this.listPerson);
	
		// Order in does not match order out.
		list
		.parallelStream()
		.forEach(p -> prn(p));
	}	
	
	
	
	/////////////////////////////////////////////////
	// Map - n objects in, n objects out
	
	public void example03() {
		if(true) return;;
		List<Person> list = Arrays.asList(this.listPerson);
			
		// Extract all first names and make lower case.
		list
		.stream()
		.map (p -> p.getFirstName().toLowerCase())
		.forEach(p -> prn(p));
	}	
	
	public void example04() {
		if(true) return;;
		List<Person> list = Arrays.asList(this.listPerson);
		
		// Print out all ages.
		list
		.stream()
		.mapToInt(Person::getAge)
		.forEach(p -> prn(p));
	}
		
	public void example05() {
		if(true) return;;
		List<Person> list = Arrays.asList(this.listPerson);
		
		// Organize by last name.
		list
		.stream()
		.sorted(Comparator.comparing(Person::getLastName))
		.forEach(p -> prn(p));
	}		
	
	
	
	/////////////////////////////////////////////////
	// Filter -  n objects in, <=n objects out
	
	public void example06() {
		if(true) return;;
		List<Person> list = Arrays.asList(this.listPerson);
		
		// Get all the Reed's
		list
		.stream()
		.filter(p -> p.getLastName().equals("Reed"))
		.forEach(p -> prn(p));
	}	
	
	/////////////////////////////////////////////////
	// Reduce -  n objects in, 1 object out	- Terminal Operation
	
	public void example07() {
		if(true) return;;
		List<Person> list= Arrays.asList(this.listPerson);
		
		// Get the youngest person
		Person person = 
			list
			.stream()
			.reduce((p1, p2) -> p1.getAge() < p2.getAge()? p1 : p2)
			.get();
		
		prn(person);
	}		
	
	public void example08() {
		if(true) return;;
		//List<Person> list = Arrays.asList(this.listPerson);
		List<Person> list = new ArrayList<Person>();		
		
		// Get the average age
		double d = 
			list
			.stream()
			.mapToInt(Person::getAge)
			.average()  
			.orElse(-1);
			//.getAsDouble()
		
		prn(d);
	}		
	
	/////////////////////////////////////////////////
	// Collect - Stream<T> in, List<T> or Map<R, T> or Single Object out
	
	public void example09() {
		if(true) return;;
		List<Person> list= Arrays.asList(this.listPerson);
		
		// Put all the Females into a new List.
		List<Person> listFemale =
			    list
			    .stream()
			    .filter(p -> p.getGender().equals("F"))
			    .collect(Collectors.toList());

		prn(listFemale.size());
		
	}		
	
	public void example10() {
		if(true) return;;
		List<Person> list= Arrays.asList(this.listPerson);
		
		// Put people with the same last name in their own map.
		Map<String, List<Person>> byLastName =
			    list
			    .parallelStream()
			    .collect(Collectors.groupingBy(Person::getLastName));

		prn(byLastName.get("Perkins"));
		prn(byLastName.get("Reed"));
		prn(byLastName.get("Rodam"));
		prn(byLastName.get("White"));
		
	}	
	
	/////////////////////////////////////////////////
	// Chain together

	public void example11() {
		if(true) return;;
		List<Person> list= Arrays.asList(this.listPerson);
		
		// Get the average age of all male Perkins
		double d = 
			list
			.stream()
			.filter(p -> p.getLastName().equals("Perkins"))
			.filter(p -> p.getGender().equals("M"))
			.mapToInt(p -> p.getAge())
			.average()
			.getAsDouble();

		prn(d);
	}		
	
	////////////////////////////
	// Supporting Functions
	
	
	// A more pleasant looking print function
	private void prn(Object obj) {
		System.out.println(obj);
	}		
	
	// Program begins here
	public static void main(String[] args) {
		Example example = new Example();		
		example.example01();
		example.example02();
		example.example03();
		example.example04();
		example.example05();
		example.example06();
		example.example07();
		example.example08();
		example.example09();
		example.example10();
		example.example11();
	}		
	
}