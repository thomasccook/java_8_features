package pattern01.ex01_inheritance.t3_species_good;

import pattern01.ex01_inheritance.t2_class.Amphibian;

public class Amph_Frog extends Amphibian {
	
	// Land speed
	@Override
	public int getSpeed() {
		return 10;
	}
	
	@Override
	public int getWaterSpeed() {
		return 20;
	}
	
	
	
}




