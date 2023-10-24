package ooad.arcane.GameObservers;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creatures.Creatures;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Logger class which captures observer updates and puts them into a new text file for each turn.
public class Logger implements GameObserver {

    // PrintWriter object which I use to print updates to a text file
    private PrintWriter output;


    // Constructor, takes in iteration counter to create a new text file.
    public Logger(int iteration) throws FileNotFoundException{


        String file_path = "Logger/Logger-" + iteration + ".txt";

        this.output = new PrintWriter(file_path);
    }

    // Method to make constructing the strings easier for the output stuff, in this case it gets the full name of a creature based on it's type.
    private String getCreatureName(String type){

        String name;

        if(type == "fire"){name = "FireBorn";}
        else if(type == "water"){name = "Aquarid";}
        else if(type == "air"){name = "Zephyral";}
        else{name = "TerraVore";}

        return name;

    }

    // Same as above except with adventurers
    private String getAdventurerName(String type){

        String name; 

        if(type == "fire"){name = "Ember Knight";}
        else if(type == "water"){name = "Mist Walker";}
        else if(type == "air"){name = "Zephyr Rogue";}
        else{name = "Terra Voyager";}

        return name;

    }

    // method which prints the update if adventurer moves and prints where they moved to
    public void adventurerMoved(Adventurer adventurer){

        String name = getAdventurerName(adventurer.type);

        output.println(name + " moved to floor " + adventurer.currentRoom.coordinates.floor
        + " room " + adventurer.currentRoom.coordinates.x + "-" + adventurer.currentRoom.coordinates.y);
    }

    // same as above except with creature
    public void creatureMoved(Creatures creature){

        String name = getCreatureName(creature.type);

        output.println(name + " moved to floor " + creature.currentRoom.coordinates.floor
        + " room " + creature.currentRoom.coordinates.x + "-" + creature.currentRoom.coordinates.y);
    }

    // prints an update where a creature was killed by an adventurer
    public void adventurerWonCombat(Adventurer adventurer, Creatures creature){

        String ad_name = getAdventurerName(adventurer.type);
        String cr_name = getCreatureName(creature.type);

        output.println(ad_name + " smashed a " + cr_name);
    }

    // prints an update where an adventurer was killed by a creature
    public void creatureWonCombat(Adventurer adventurer, Creatures creature){

        String ad_name = getAdventurerName(adventurer.type);
        String cr_name = getCreatureName(creature.type);

        output.println(ad_name + " was killed by a " + cr_name);
    }

    // prints an update where an adventurer's combat skill level increased.
    public void adventurerLeveledUp(Adventurer adventurer){

        String ad_name = getAdventurerName(adventurer.type);

        output.println(ad_name + " leveled up to level " + adventurer.level);
    }

    // method which prints when an adventurer gains resonance
    public void adventurerGainedResonance(Adventurer adventurer){

        String ad_name = getAdventurerName(adventurer.type);

        output.println(ad_name + " gained their elemental resonance!");
    }

    // method which prints when an adventurer enters a floor which gives them discord
    public void adventurerGainedDiscord(Adventurer adventurer){

        String ad_name = getAdventurerName(adventurer.type);

        output.println(ad_name + " is now afflicted by their elemental discord");
    }

    // method which prints when an adventurer loses health in battle
    public void adventurerLostHealth(Adventurer adventurer){

        String ad_name = getAdventurerName(adventurer.type);

        output.println(ad_name + " got damaged. Their health is now " + adventurer.health); // <--- IMPLEMENT THIS
    }

    // method which prints when an adventurer finds treasure (only non-gem items)
    public void adventurerFoundTreasure(Adventurer adventurer){

        String ad_name = getAdventurerName(adventurer.type);

        // IMPLEMENT THE TREASUREBAG FIRST
        if(adventurer.treasure.contents.size() == 0){

        }else{
            output.println(ad_name + " got Treasure! They picked up a " 
            + adventurer.treasure.contents.get(adventurer.treasure.contents.size() - 1));
        }
    }

    // method which flushes and closes PrintWriter object
    public void closeFile(){
        this.output.flush();
        this.output.close();
    }

    // method which just prints the turn to the text file
    public void printTurn(int turn){
        this.output.println("Current Turn:" + turn);
    }
    
}
