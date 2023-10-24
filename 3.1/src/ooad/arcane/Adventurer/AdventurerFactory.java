package ooad.arcane.Adventurer;


public class AdventurerFactory {

	public static Adventurer createAdventurer(String type){
		if("EmberKnight".equalsIgnoreCase(type)){
          return new EmberKnight(null);  
        }
		else if("MistWalker".equalsIgnoreCase(type)){
            return new MistWalker(null);
        }
        else if("TerraVoyager".equalsIgnoreCase(type)){
            return new TerraVoyager(null);
        }
        else if("ZephyrRogue".equalsIgnoreCase(type)){
            return new ZephyrRogue(null);
        }
		
		return null;
	}
}
