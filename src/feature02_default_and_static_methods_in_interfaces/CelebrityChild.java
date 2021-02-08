package feature02_default_and_static_methods_in_interfaces;

public class CelebrityChild implements Father, Mother {

	private String name;
	
	public CelebrityChild(String name) {
		this.name = name;
	}
	
	public void printName() {
		System.out.println("Child : " + this.name + " " + Father.getLastName());
	}

	@Override
	public void sendMothersDayCard() {
		
		System.out.println("Card : I Love You, " + Mother.getFirstName());
		
	}
	
}


