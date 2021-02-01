package pattern01.ex01_inheritance.t3_species_good;

import pattern01.ex01_inheritance.t2_class.Insect;

public class Insect_Cockroach extends Insect {
	
	// Land speed
	@Override
	public int getSpeed() {
		return 5;
	}
	
	@Override
	public String getYoung() {
		return "Babies resemble adults";
	}
	
}




