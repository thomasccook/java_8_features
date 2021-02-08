package feature02_default_and_static_methods_in_interfaces;

//An interface with exactly one abstract method is known as Functional Interface.
@FunctionalInterface
public interface Mother {
	
	String firstName = "Kim";
	String lastName = "Kardashian";	
	
	// @FunctionalInterface annotation is a facility to avoid accidental addition of abstract 
	// methods in the functional interfaces. It’s optional but good practice to use it.
	// 
	// Functional interfaces are long awaited and much sought out feature of Java 8 because 
	// it enables us to use lambda expressions to instantiate them. 
	void sendMothersDayCard();
	
	default void printMother() {
		System.out.println("Mother : " + Mother.getFirstName() + " " + Mother.getLastName());
	}	
	
	static String getFirstName(){
		return firstName;
	}	
	
	static String getLastName(){
		return lastName;
	}	
}


