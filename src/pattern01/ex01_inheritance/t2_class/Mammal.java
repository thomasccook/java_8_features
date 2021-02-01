package pattern01.ex01_inheritance.t2_class;

import pattern01.ex01_inheritance.t1_kingdom.Animal;

public class Mammal extends Animal {
	
	// Land speed
	@Override
	public int getSpeed() {
		return 1;
	}
	
	@Override
	public String getYoung() {
		return "Live birth";
	}
	
	@Override
	public String careForBaby() {
		return "Parents care for young";
	}
	
	// All mammals give milk ... by definition
	public String getMilk() {
		return "Gets milk from mother";
	}
	
}




