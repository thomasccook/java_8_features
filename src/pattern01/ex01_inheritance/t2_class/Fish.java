package pattern01.ex01_inheritance.t2_class;

import pattern01.ex01_inheritance.t1_kingdom.Animal;

public class Fish extends Animal {
	
	// Water speed
	@Override
	public int getSpeed() {
		return 10;
	}
	
	@Override
	public String getYoung() {
		return "Egg in sand";
	}
	
	@Override
	public String careForBaby() {
		return "Abandoned by parents";
	}
	
}




