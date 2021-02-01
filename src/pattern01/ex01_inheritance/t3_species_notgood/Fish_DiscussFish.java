package pattern01.ex01_inheritance.t3_species_notgood;

import pattern01.ex01_inheritance.t2_class.Fish;

public class Fish_DiscussFish extends Fish {
	
	// Water speed
	@Override
	public int getSpeed() {
		return 10;
	}
	
	@Override
	public String careForBaby() {
		return "Parents feed their young for 2 weeks";
	}	

	// BAD ... This fish excrete's milk ... very similar to a mammal
	public String careForBaby2() {
		return "Parents excrete a protein-antibody mixture for a few days";
	}
	
}




