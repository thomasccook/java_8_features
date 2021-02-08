package feature02_default_and_static_methods_in_interfaces;

public class Example01 {

	public Example01() {

		CelebrityChild child1 = new CelebrityChild("North");
		CelebrityChild child2 = new CelebrityChild("Saint");
		CelebrityChild child3 = new CelebrityChild("Chicago");
		
		child1.printName();
		child1.printFather();
		child1.printMother();
		
		System.out.println("---");

		child2.printName();
		child2.printFather();
		child2.printMother();
		
		System.out.println("---");
		
		child3.printName();
		child3.printFather();
		child3.printMother();
		child3.sendMothersDayCard();
		
	}

	//////////////////////////////////////////
	// Supporting functions
			
	// Program begins here
	public static void main(String[] args) {
		new Example01();
	}		
}

/* Output
 
Child : North West
Father : Kanye West
Mother : Kim Kardashian
---
Child : Saint West
Father : Kanye West
Mother : Kim Kardashian
---
Child : Chicago West
Father : Kanye West
Mother : Kim Kardashian
Card : I Love You, Kim
  
 */