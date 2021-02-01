package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.BabyResemblesAdult;
import pattern01.ex02_composition.properties.Milk;
import pattern01.ex02_composition.properties.Taxonomy;
import pattern01.ex02_composition.properties.WaterSpeed;

class DiscussFish implements Taxonomy, WaterSpeed, BabyResemblesAdult, Milk {
	
	@Override
	public String getClasss() {return "Fish";}
	
	@Override
	public String getSpecies() {return "Discuss Fish";}

	@Override
	public String gotMilk() {
		return "something";
	}

	@Override
	public String babyResemblesAdult() {
		return "something";
	}

	@Override
	public int getWaterSpeed() {
		return 0;
	}	
	
	
}




