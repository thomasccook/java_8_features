package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.AbandonBaby;
import pattern01.ex02_composition.properties.BabyResemblesAdult;
import pattern01.ex02_composition.properties.Taxonomy;
import pattern01.ex02_composition.properties.WaterSpeed;

public class Mudpuppy implements Taxonomy, WaterSpeed, BabyResemblesAdult, AbandonBaby {
	
	@Override
	public String getClasss() {return "Amphibian";}
	
	@Override
	public String getSpecies() {return "Mudpuppy";}

	@Override
	public String abandonBaby() {
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




