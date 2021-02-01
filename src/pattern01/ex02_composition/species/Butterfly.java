package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.AbandonBaby;
import pattern01.ex02_composition.properties.AirSpeed;
import pattern01.ex02_composition.properties.Egg;
import pattern01.ex02_composition.properties.Larvae;
import pattern01.ex02_composition.properties.Pupae;
import pattern01.ex02_composition.properties.Taxonomy;

public class Butterfly implements Taxonomy, AirSpeed, Egg, Larvae, Pupae, AbandonBaby  {
	
	@Override
	public String getClasss() {return "Insect";}
	
	@Override
	public String getSpecies() {return "Butterfly";}

	@Override
	public String abandonBaby() {
		return "something";
	}

	@Override
	public String getPupae() {
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
	public int getAirSpeed() {
		return 0;
	}	
	
		
}




