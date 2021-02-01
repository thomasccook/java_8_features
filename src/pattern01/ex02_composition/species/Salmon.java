package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.AbandonBaby;
import pattern01.ex02_composition.properties.Egg;
import pattern01.ex02_composition.properties.Taxonomy;
import pattern01.ex02_composition.properties.WaterSpeed;

public class Salmon implements Taxonomy, WaterSpeed, Egg, AbandonBaby {
	
	@Override
	public String getClasss() {return "Fish";}
	
	@Override
	public String getSpecies() {return "Salmon";}

	@Override
	public String abandonBaby() {
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
	
	
}




