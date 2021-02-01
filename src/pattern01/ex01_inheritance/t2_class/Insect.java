package pattern01.ex01_inheritance.t2_class;

import pattern01.ex01_inheritance.t1_kingdom.Animal;

public class Insect extends Animal {
	
	// Land speed
	@Override
	public int getSpeed() {
		return 1;
	}
	
	@Override
	public String getYoung() {
		return "Babies resemble adults";
	}
	
	@Override
	public String careForBaby() {
		return "Abandoned Babies";
	}
	
}




