package pattern01.ex01_inheritance.t3_species_notgood;

import pattern01.ex01_inheritance.t2_class.Amphibian;

public class Amph_Mudpuppy extends Amphibian {
	
	// Land speed
	@Override
	public int getSpeed() {
		return -1; // BAD ... Mudpuppies never go on land
	}
	
	@Override
	public int getWaterSpeed() {
		return 5;
	}
	
	
	
}




