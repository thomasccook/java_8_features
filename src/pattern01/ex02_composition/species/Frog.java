package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.AbandonBaby;
import pattern01.ex02_composition.properties.Egg;
import pattern01.ex02_composition.properties.LandSpeed;
import pattern01.ex02_composition.properties.Larvae;
import pattern01.ex02_composition.properties.Taxonomy;
import pattern01.ex02_composition.properties.WaterSpeed;

public class Frog implements Taxonomy, LandSpeed, WaterSpeed, Egg, Larvae, AbandonBaby {
	
	@Override
	public String getClasss() {return "Amphibian";}
	
	@Override
	public String getSpecies() {return "Frog";}

	@Override
	public String abandonBaby() {
		return "something";
	}

	@Override
	public String getLarvae() {
		return "something";
	}

	@Override
	public String getEgg() {
		return "something";
	}

	@Override
	public int getWaterSpeed() {
		return 0;
	}

	@Override
	public int getLandSpeed() {
		return 0;
	}	
	
	
	
	
}




