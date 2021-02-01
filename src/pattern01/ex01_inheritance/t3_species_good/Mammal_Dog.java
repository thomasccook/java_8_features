package pattern01.ex01_inheritance.t3_species_good;

import pattern01.ex01_inheritance.t2_class.Insect;

public class Mammal_Dog extends Insect {
	
	// Land speed
	@Override
	public int getSpeed() {
		return 12;
	}
	
	@Override
	public String getYoung() {
		return "Puppies are cute!";
	}
	
	@Override
	public String careForBaby() {
		return "Get 'milk' from mother.";
	}	
	
	
}




