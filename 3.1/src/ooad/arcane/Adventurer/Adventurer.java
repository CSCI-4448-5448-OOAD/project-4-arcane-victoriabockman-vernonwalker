package ooad.arcane.Adventurer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ooad.arcane.Floor;
import ooad.arcane.Room;
import ooad.arcane.Rooms;
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

    public Rooms rooms_ad;

    public String name;




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

    public Integer rollDice(){
        Random rand = new Random();

        int diceOne = rand.nextInt(6) + 1;
        int diceTwo = rand.nextInt(6) + 1;

        return (diceOne + diceTwo);

    }

    public void findTreasure(Adventurer goodGuy, Room currentRoom, Rooms tele_room){

        int adventurerRoll = rollDice();

        // add bonus to dice roll
        adventurerRoll += goodGuy.searchStrategy.Bonus();

        if ((goodGuy.type == "air") && (currentRoom.coordinates.floor == "air")){
            adventurerRoll += 2;
        }
        else if((goodGuy.type == "air") && (currentRoom.coordinates.floor == "earth")){
            adventurerRoll -= 2;
        }
        else{
            // nothing to see here
        }

        // roll of 7 or higher for treasure now
        if(adventurerRoll >= 7 && currentRoom.treasure != null){

            // level up adventurer //
            goodGuy.searchStrategy.LevelUpSearch(goodGuy);

            int value_before = goodGuy.treasure.value;

            // ADD TREASURE TO TREASURE BAG
            currentRoom.treasure.getTreasureBag(goodGuy.treasure);

            int value_after = goodGuy.treasure.value;

            if(value_before != value_after){
                // if portal was picked up, immediately teleport
                if(currentRoom.treasure.contents.contains("Portal")){

                    // Remove treasure from room
                    goodGuy.currentRoom.treasure = null;

                    List<Floor> rand_floor = Arrays.asList(tele_room.airFloor, tele_room.earthFloor, tele_room.fireFloor, tele_room.waterFloor);

                    Collections.shuffle(rand_floor);

                    Random rn = new Random();
                    int rand_room = rn.nextInt(9) + 0;

                    goodGuy.currentRoom = rand_floor.get(0).rooms[rand_room];
                }
                else{
                    // Remove treasure from room
                    goodGuy.currentRoom.treasure = null;
                    if((value_after - value_before) != 1000){
                        goodGuy.notify("adventurerFoundTreasure");
                    }
                }   
            
            }
            else{
                // Leave it in the room
            }
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

    public void updateObservers(GameObserver[] obs){
        this.observers = obs;
    }

    public void printAdventurers(String name, Adventurer ad){

        String s = name + " " + ad.treasure.value + " Treasure(s) - " + ad.health + " Health Remaining\n";

        System.out.println(s);

    }
}