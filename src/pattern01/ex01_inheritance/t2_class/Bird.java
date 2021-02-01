package pattern01.ex01_inheritance.t2_class;

import pattern01.ex01_inheritance.t1_kingdom.Animal;

public class Bird extends Animal {
	
	// Air speed
	@Override
	public int getSpeed() {
		return 10;
	}
	
	@Override
	public String getYoung() {
		return "Egg in nest";
	}
	
	@Override
	public String careForBaby() {
		return "Regurgitate";
	}
	
	
}




