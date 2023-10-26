package ooad.arcane.Adventurer;

import ooad.arcane.Rooms;
import ooad.arcane.GameObservers.GameObserver;

public class AdventurerFactory {

	public static Adventurer createAdventurer(String type, Rooms rooms, GameObserver[] observers){
		if("EmberKnight".equalsIgnoreCase(type)){
          return new EmberKnight(observers, rooms);  
        }
		else if("MistWalker".equalsIgnoreCase(type)){
            return new MistWalker(observers, rooms);
        }
        else if("TerraVoyager".equalsIgnoreCase(type)){
            return new TerraVoyager(observers, rooms);
        }
        else if("ZephyrRogue".equalsIgnoreCase(type)){
            return new ZephyrRogue(observers, rooms);
        }
		
		return null;
	}
}