package ooad.arcane.Creatures;

import ooad.arcane.Room;
import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.GameObservers.GameObserver;

public abstract class Creatures {
    public String type;
    public boolean isAlive;
    public Room currentRoom;
    public int number;
    public GameObserver[] gameObservers;
    // All creatures have one health point
    public void Die(){
        this.isAlive = false;
    }

    public void notify(String hint){
        if(hint == "creatureMoved"){
            for(GameObserver i : gameObservers){
                i.creatureMoved(this);
            }
        }
    }

    public void notify(String hint, Adventurer adventurer){
        if(hint == "creatureWonCombat"){
            for(GameObserver i : gameObservers){
                i.creatureWonCombat(adventurer, this);
            }
        }
    }

}
