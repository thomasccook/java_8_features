package pattern01.ex01_inheritance.t3_species_notgood;

import pattern01.ex01_inheritance.t2_class.Insect;

public class Insect_Butterfly extends Insect {
	
	public int phase;
	
	// Land speed
	@Override
	public int getSpeed() {
		return 5;
	}
	
	// BAD ... This insect has a multi-phase youth cycle, but the interface is too simple.
	@Override
	public String getYoung() {
		switch (phase) {
			case 1: return getEgg();
			case 2: return getLarvae();
			case 3: return getPupae();
			default: return null;
		}
	}
	
	public String getEgg() {
		return "Egg";
	}
	
	public String getLarvae() {
		return "Larvae";
	}
	
	public String getPupae() {
		return "Pupae";
	}

	
	
}




