package ooad.arcane.Adventurer;

import java.util.Random;

import ooad.arcane.Room;
import ooad.arcane.Creatures.Creatures;
import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.AttackStrategy;
import ooad.arcane.RollBonus.SearchStrategy;
import ooad.arcane.TreasureBag.TreasureBag;


// Adventurer parent class which holds all expected methods and attributes for each adventurer subclass
public abstract class Adventurer {

    public String type;
    // stores the type of the adventurer (fire, water, etc;)

    public int health;
    // measure of the amount of health that the adventurer has left

    public TreasureBag treasure;
    // amount of treasure that the adventurer has

    public float dodgeChance;
    // chance that adventurer dodges an attack

    public boolean isAlive;
    // whether adventurer is alive or not

    public Room currentRoom;
    // current room pointer
    
    public int level = 0;

    public AttackStrategy attackStrategy;

    public SearchStrategy searchStrategy;
    
    // public GameObserver observers;

    public GameObserver[] observers;




    public String badRoomType;
    // room type which causes the elemental discord
    public String goodRoomType;
    // room type which causes the elemental affinity
    public boolean inDiscord;
    public boolean inResonance;

    public void Move(){
        // randomly chooses 1 room from the set of all adjacent rooms which the adventurer moves to

        int size = this.currentRoom.allAdjacentRooms.size();

        Random rand = new Random();

        int choice = rand.nextInt(size);

        Room prevRoom = this.currentRoom;

        this.currentRoom = prevRoom.allAdjacentRooms.get(choice);

        prevRoom.alliedOccupants.remove(this);

        this.currentRoom.alliedOccupants.add(this);

        // notifies the observers

        notify("adventurerMoved");
    }

    public void printHealth(){
        System.out.println(this.health);

        // helper method to print the health of the adventurer
    }

    public void notify(String hint){

        // takes in the hint string and calls the apporpriate method for the observers
        switch(hint){
            case "adventurerMoved":
                for(GameObserver i : observers){
                    i.adventurerMoved(this);
                }
                break;
            
            case "adventurerLeveledUp":
                for(GameObserver i : observers){
                    i.adventurerLeveledUp(this);
                }
                break;

            case "adventurerGainedResonance":
                for(GameObserver i : observers){
                    i.adventurerGainedResonance(this);
                }
                break;

            case "adventurerGainedDiscord":
                for(GameObserver i : observers){
                    i.adventurerGainedDiscord(this);
                }
                break;
            
            case "adventurerLostHealth":
                for(GameObserver i : observers){
                    i.adventurerLostHealth(this);;
                }
                break;

            case "adventurerFoundTreasure":
                for(GameObserver i : observers){
                    i.adventurerFoundTreasure(this);
                }
                break;
        }
    }

    public void notify(String hint, Creatures creature){
        // if the notify also includes a creature, then the hint is also transformed
        if(hint == "adventurerWonCombat"){
            for(GameObserver i : observers){
                i.adventurerWonCombat(this, creature);
            }
        }
    }


    // Possible additions for changes

    public void printAdventurers(String name, Adventurer ad){

        String s = name + ad.treasure.value + " Treasure(s) - " + ad.health + " Health Remaining\n";

        System.out.println(s);

    }



























}
