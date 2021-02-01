package pattern01.ex01_inheritance.t3_species_notgood;

import pattern01.ex01_inheritance.t2_class.Bird;

public class Bird_Puffin extends Bird {
	
	// Air speed
	@Override
	public int getSpeed() {
		return 55;
	}
	
	public int getLandSpeed() {
		return 1; // BAD ... function added as 'one-off'
	}
	
	public int getWaterSpeed() {
		return 10; // BAD ... function added as 'one-off'
	}
	
}




