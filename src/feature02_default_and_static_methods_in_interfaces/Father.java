package feature02_default_and_static_methods_in_interfaces;

public interface Father {

	// Interface can have variables
	String firstName = "Kanye";
	String lastName = "West";
	
	// Interface can have a function
	default void printFather() {
		System.out.println("Father : " + Father.getFirstName() + " " + Father.getLastName());
	}
	
	// Interface can have a static function
	static String getFirstName(){
		return firstName;
	}	
	
	static String getLastName(){
		return lastName;
	}
}


