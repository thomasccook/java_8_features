package pattern01.ex01_inheritance.t3_species_good;

import pattern01.ex01_inheritance.t2_class.Fish;

public class Fish_Salmon extends Fish {
	
	// Water speed
	@Override
	public int getSpeed() {
		return 20;
	}
	
	@Override
	public String careForBaby() {
		return "Parents mate, lay eggs, and die. Young care for themselves.";
	}	
	
	
}




