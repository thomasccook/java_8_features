package pattern01.ex02_composition.species;

import pattern01.ex02_composition.properties.BabyResemblesAdult;
import pattern01.ex02_composition.properties.LandSpeed;
import pattern01.ex02_composition.properties.Milk;
import pattern01.ex02_composition.properties.Taxonomy;

public class Cockroach implements Taxonomy, LandSpeed, BabyResemblesAdult, Milk {
	
	@Override
	public String getClasss() {return "Insect";}
	
	@Override
	public String getSpecies() {return "Cockroach";}

	@Override
	public String gotMilk() {
		return "something";
	}

	@Override
	public String babyResemblesAdult() {
		return "something";
	}

	@Override
	public int getLandSpeed() {
		return 0;
	}	
	
	
	
}




