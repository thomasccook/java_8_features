package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.AirSpeed;
import pattern01.ex02_composition.properties.Egg;
import pattern01.ex02_composition.properties.LandSpeed;
import pattern01.ex02_composition.properties.RegurgitateForChildren;
import pattern01.ex02_composition.properties.Taxonomy;
import pattern01.ex02_composition.properties.WaterSpeed;

public class Puffin implements Taxonomy, AirSpeed, LandSpeed, WaterSpeed, Egg, RegurgitateForChildren {
	
	@Override
	public String getClasss() {return "Bird";}
	
	@Override
	public String getSpecies() {return "Puffin";}

	@Override
	public String gotMilk() {
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

	@Override
	public int getAirSpeed() {
		return 0;
	}	
	
	
}




