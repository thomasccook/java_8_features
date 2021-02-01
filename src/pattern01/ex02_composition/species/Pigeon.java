package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.AirSpeed;
import pattern01.ex02_composition.properties.Egg;
import pattern01.ex02_composition.properties.Milk;
import pattern01.ex02_composition.properties.RegurgitateForChildren;
import pattern01.ex02_composition.properties.Taxonomy;

public class Pigeon implements Taxonomy, AirSpeed, Egg, Milk, RegurgitateForChildren {
	
	@Override
	public String getClasss() {return "Bird";}
	
	@Override
	public String getSpecies() {return "Pigeon";}

	@Override
	public String gotMilk() {
		return "something";
	}

	@Override
	public String getEgg() {
		return "something";
	}

	@Override
	public int getAirSpeed() {
		return 0;
	}	
	
	
}




