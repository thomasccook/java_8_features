package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.Egg;
import pattern01.ex02_composition.properties.LandSpeed;
import pattern01.ex02_composition.properties.Milk;
import pattern01.ex02_composition.properties.Taxonomy;
import pattern01.ex02_composition.properties.WaterSpeed;

class Platypus implements Taxonomy, LandSpeed, WaterSpeed, Egg, Milk {
	
	@Override
	public String getClasss() {return "Mammal";}
	
	@Override
	public String getSpecies() {return "Platypus";}

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
	
	
}




