package ooad.arcane.Adventurer;

import ooad.arcane.Rooms;
import ooad.arcane.GameObservers.GameObserver;

// Factory Pattern For Adventurers

public class AdventurerFactory {

	public static Adventurer createAdventurer(String type, Rooms rooms, GameObserver[] observers){
		if("fire".equalsIgnoreCase(type)){
          return new EmberKnight(observers, rooms);  
        }
		else if("water".equalsIgnoreCase(type)){
            return new MistWalker(observers, rooms);
        }
        else if("earth".equalsIgnoreCase(type)){
            return new TerraVoyager(observers, rooms);
        }
        else if("air".equalsIgnoreCase(type)){
            return new ZephyrRogue(observers, rooms);
        }
		
		return null;
	}
}