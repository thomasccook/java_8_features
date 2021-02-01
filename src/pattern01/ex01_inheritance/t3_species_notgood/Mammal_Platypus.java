package pattern01.ex01_inheritance.t3_species_notgood;

import pattern01.ex01_inheritance.t2_class.Insect;

public class Mammal_Platypus extends Insect {
	
	// Land speed
	@Override
	public int getSpeed() {
		return 5;
	}
	
	public int getWaterSpeed() {
		return 10;
	}
	

	// BAD ... Platypus lay eggs, but there is no way to add this interesting feature 
	// common to other animals.
	
	
	
	
}




