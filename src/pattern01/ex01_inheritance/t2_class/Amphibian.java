package pattern01.ex01_inheritance.t2_class;

import pattern01.ex01_inheritance.t1_kingdom.Animal;

public class Amphibian extends Animal {
	
	// Iffy ... need to pick getSpeed as Land speed
	@Override
	public int getSpeed() {
		return 1;
	}
	
	// Iffy ... need to add a new function for water
	public int getWaterSpeed() {
		return 2;
	}
	
	@Override
	public String getYoung() {
		return "Egg in water";
	}
	
	@Override
	public String careForBaby() {
		return "Feed yourself";
	}
	
	
}
