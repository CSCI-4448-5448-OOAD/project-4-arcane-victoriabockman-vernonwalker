package ooad.arcane.GameObservers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.EmberKnight;
import ooad.arcane.Adventurer.MistWalker;
import ooad.arcane.Adventurer.TerraVoyager;
import ooad.arcane.Adventurer.ZephyrRogue;
import ooad.arcane.Creatures.Aquarid;
import ooad.arcane.Creatures.Creatures;
import ooad.arcane.Creatures.Fireborn;
import ooad.arcane.Creatures.Terravore;
import ooad.arcane.Creatures.Zephyral;


// Tracker class which uses dictionaries to store all relevent data on the adventurers and creatures
public class Tracker implements GameObserver {

    // Singleton instance
    private static Tracker tracker;

    Dictionary<String, Adventurer> adventurerDictionary;

    // CreatureMap class object defined below this class
    CreatureMap creatureMap;

    // List of adventurers in discord (helpful for printing)
    ArrayList<Adventurer> inDiscord;
    // Same as above except in resonance
    ArrayList<Adventurer> inResonance;

    // Constructor
    private Tracker(){
        this.adventurerDictionary = new Hashtable<>();

        GameObserver[] empty = new GameObserver[0];

        this.adventurerDictionary.put("fire", new EmberKnight(empty));
        this.adventurerDictionary.put("water", new MistWalker(empty));
        this.adventurerDictionary.put("air", new ZephyrRogue(empty));
        this.adventurerDictionary.put("earth", new TerraVoyager(empty));

        this.creatureMap = new CreatureMap();
        this.inDiscord = new ArrayList<>();
        this.inResonance = new ArrayList<>();
    }

    public synchronized static Tracker getInstance(){

        if(tracker == null){
            tracker = new Tracker();
        }

        return tracker;
    }

    // updates adventurer dictionary when an adventurer moves
    public void adventurerMoved(Adventurer adventurer){
        this.adventurerDictionary.get(adventurer.type).currentRoom = adventurer.currentRoom;
    }

    // updates creaturemap object when creature moves
    public void creatureMoved(Creatures creature){
        this.creatureMap.get(creature).currentRoom = creature.currentRoom;
    }

    // updates creatureMap object when a creature dies
    public void adventurerWonCombat(Adventurer adventurer, Creatures creature){
        this.creatureMap.remove(creature);
    }

    // updates adventurer dicionary when an adventurer dies
    public void creatureWonCombat(Adventurer adventurer, Creatures creature){
        this.adventurerDictionary.get(adventurer.type).isAlive = false;
    }

    // updates an adventurer level when that adventurer's combat level increases
    public void adventurerLeveledUp(Adventurer adventurer){
        this.adventurerDictionary.get(adventurer.type).level += 1; // <-- UPDATE THIS TO MATCH ATTACK LEVEL AND SEARCH LEVEL
    }

    // updates the inResonance list when an adventurer gets resonance
    public void adventurerGainedResonance(Adventurer adventurer){
        this.inResonance.add(adventurer);
    }

    // same as above except discord
    public void adventurerGainedDiscord(Adventurer adventurer){
        this.inDiscord.add(adventurer);
    }

    // updates health on adventurer upon losing health
    public void adventurerLostHealth(Adventurer adventurer){
        this.adventurerDictionary.get(adventurer.type).health = adventurer.health;
    }

    // updates adventurer treasure when adventurer pickes up treasure (except gems)
    public void adventurerFoundTreasure(Adventurer adventurer){
        // Implement this when we implement treasure changes
        if(adventurer.treasure.contents.size() == 0){

        }else{

            this.adventurerDictionary.get(adventurer.type).treasure.contents.add(adventurer.treasure.contents.get(adventurer.treasure.contents.size()-1));
            this.adventurerDictionary.get(adventurer.type).treasure.value = adventurer.treasure.value;

        }
    }

    // method to print the Trackers contents to the command line
    public void printTurn(int turn){

        getDiscordAndResonance();

        String s = """
            Tracker: turn \n"""+ turn + """
                
            Number of active adventurers: """ + numActiveAdventurers() + """
                
            Adventurers   |  Room   |  Health   |  Treasure  |  Treasure Value
            EmberKnight""" + "  |  " + getCoords("fire") + "  |  " + getHealth("fire") + "  |  " + getTreasure("fire") + "  |  " + getValue("fire") + "\n" + """
            MistWalker""" + "  |  "+ getCoords("water") + "  |  " + getHealth("water") + "  |  " + getTreasure("water") + " |   " + getValue("water") +"\n" + """
            ZephyrRogue""" + "  |  "+ getCoords("air") + "  |  " + getHealth("air") + "  |  " + getTreasure("air") + "  |  " + getValue("air") + "\n" +"""
            TerraVoyager""" + "  |  "+ getCoords("earth") + "  |  " + getHealth("earth") + "  |  " + getTreasure("earth") + "  |  " + getValue("earth") +"\n" + """

            Resonance: \n"""
            + printResonance() + """

            Discord: \n"""
            + printDiscord() + """

            Number of Creatures: """ + creatureMap.getNumberOfCreatures() + """

            Creatures       Room\n"""
            + creatureMap.printCreatures();

        System.out.println(s);
    }
 
    // method to quickly show the number of alive adventurers (using the 'isAlive' boolean)
    private int numActiveAdventurers(){

        int num = 0;
        ArrayList<Adventurer> ads = Collections.list(this.adventurerDictionary.elements());
        for(int i = 0; i < ads.size(); i++){
            if(ads.get(i).isAlive){
                num++;
            }
        }
        return num;
    }

    // transformes adventurers coordinates to a string
    private String getCoords(String key){
        String floor = this.adventurerDictionary.get(key).currentRoom.coordinates.floor;
        int x = this.adventurerDictionary.get(key).currentRoom.coordinates.x;
        int y = this.adventurerDictionary.get(key).currentRoom.coordinates.y;

        return floor + "-" + x + "-" + y;
    }

    // helper method to help with printing health
    private int getHealth(String key){
        return this.adventurerDictionary.get(key).health;
    }

    // helper method to return a string of all of adventurers treasure
    private String getTreasure(String key){
        int size = this.adventurerDictionary.get(key).treasure.contents.size();

        String val = "";
        for(int i = 0; i < size; i++){
            val += this.adventurerDictionary.get(key).treasure.contents.get(i);
            val += ", ";
        }
        return val;
    }

    // helper method to retrieve adventurer's treasure bag value
    private int getValue(String key){
        return this.adventurerDictionary.get(key).treasure.value;
    }

    // helper method to turn inResonance and inDiscord lists into strings
    private void getDiscordAndResonance(){

        ArrayList<Adventurer> ads = Collections.list(this.adventurerDictionary.elements());

        this.inDiscord.clear();
        this.inResonance.clear();

        for(int i = 0; i < ads.size(); i++){
            if(ads.get(i).goodRoomType == ads.get(i).currentRoom.coordinates.floor){
                this.inResonance.add(ads.get(i));
            }
            else if(ads.get(i).badRoomType == ads.get(i).currentRoom.coordinates.floor){
                this.inDiscord.add(ads.get(i));
            }
            else{
                this.inResonance.remove(ads.get(i));
                this.inDiscord.remove(ads.get(i));
            }
        }
    }
    
    // helper method to retrieve inResonance string
    private String printResonance(){

        String s = "";
        for(int i = 0; i < this.inResonance.size(); i++){
            if(inResonance.get(i).type == "fire"){
                s += "EmberKnight\n";
            }
            else if(inResonance.get(i).type == "water"){
                s += "MistWalker\n";
            }
            else if(inResonance.get(i).type == "air"){
                s += "ZephyrRogue\n";
            }
            else{
                s += "TerraVoyager\n";
            }
        }

        return s;
    }

    // same as above except with inDiscord
    private String printDiscord(){

        String s = "";
        for(int i = 0; i < this.inDiscord.size(); i++){
            if(inDiscord.get(i).type == "fire"){
                s += "EmberKnight\n";
            }
            else if(inDiscord.get(i).type == "water"){
                s += "MistWalker\n";
            }
            else if(inDiscord.get(i).type == "air"){
                s += "ZephyrRogue\n";
            }
            else{
                s += "TerraVoyager\n";
            }
        }

        return s;
    }

    // needed to be implemented to appease the interface gods
    public void closeFile(){
        // do nothing
    }

    
}

// Creature map helper class which contains a dicrionary of creature arrays (one for each type of creature) and implements the getter
// and setter methods for that data structure (and the remove method)
class CreatureMap{

    Dictionary<String, Creatures[]> map;

    CreatureMap(){
        map = new Hashtable<String, Creatures[]>();

        map.put("fire", getArr("fire"));
        map.put("water", getArr("water"));
        map.put("air", getArr("air"));
        map.put("earth", getArr("earth"));
    }

    private Creatures[] getArr(String type){

        Creatures[] arr = new Creatures[4];
        
        for(int i = 0; i < 4; i++){

            if(type == "fire"){
                arr[i] = new Fireborn(i);
            }

            else if(type == "water"){
                arr[i] = new Aquarid(i);
            }

            else if(type == "air"){
                arr[i] = new Zephyral(i);
            }

            else{
                arr[i] = new Terravore(i);
            }
        }

        return arr;
    }

    protected Creatures get(String type, int number){
        return map.get(type)[number];
    }

    protected Creatures get(Creatures creature){
        return map.get(creature.type)[creature.number];
    }

    protected void remove(Creatures creature){
        map.get(creature.type)[creature.number] = null;
    }

    // method which just gets the number of alive creatures
    protected int getNumberOfCreatures(){

        int num = 0;

        ArrayList<Creatures[]> ctrs = Collections.list(map.elements());

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(ctrs.get(i)[j] != null){
                    num++;
                }
            }
        }

        return num;
    }

    // method which prints the alive creatures for use in the 'printTurn' method above
    protected String printCreatures(){

        ArrayList<Creatures[]> ctrs = Collections.list(map.elements());

        String s = "";

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(ctrs.get(i)[j] != null){
                    Creatures c = ctrs.get(i)[j];
                    if(c.currentRoom != null){
                        if(c.type == "fire"){
                            s += "Fireborn     " + c.currentRoom.coordinates.floor +"-"+ c.currentRoom.coordinates.x +"-"+ c.currentRoom.coordinates.y + "\n";
                        }
                        else if(c.type == "water"){
                            s += "Aquarid     " + c.currentRoom.coordinates.floor +"-"+ c.currentRoom.coordinates.x +"-"+ c.currentRoom.coordinates.y + "\n";
                        }
                        else if(c.type == "air"){
                            s += "Zephyral     " + c.currentRoom.coordinates.floor +"-"+ c.currentRoom.coordinates.x +"-"+ c.currentRoom.coordinates.y + "\n";
                        }
                        else{
                            s += "Terravore     " + c.currentRoom.coordinates.floor +"-"+ c.currentRoom.coordinates.x +"-"+ c.currentRoom.coordinates.y + "\n";
                        }
                    }
                }
            }
        }

        return s;
    }
}
