package ooad.arcane;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import ooad.arcane.TreasureBag.ArmorDecorator;
import ooad.arcane.TreasureBag.ElixerDecorator;
import ooad.arcane.TreasureBag.EtherDecorator;
import ooad.arcane.TreasureBag.GemDecorator;
import ooad.arcane.TreasureBag.PortalDecorator;
import ooad.arcane.TreasureBag.PotionDecorator;
import ooad.arcane.TreasureBag.SwordDecorator;
import ooad.arcane.TreasureBag.TreasureBagDecorator;

// class to hold rooms and floors graph, as well as methods which pertain to the whole dungeon.
public class Rooms {


    // object for every floor and the starting room
    public Floor fireFloor = new Floor("fire");
    public Floor waterFloor = new Floor("water");
    public Floor earthFloor = new Floor("earth");
    public Floor airFloor = new Floor("air");
    public Room startingRoom = new Room();

    public Rooms(){
        // constructor which individually constructs each floor and then connects them with the starting room.
        this.startingRoom.coordinates.floor = "starting";
        this.startingRoom.coordinates.x = 0;
        this.startingRoom.coordinates.y = 0;

        this.startingRoom.allAdjacentRooms.add(this.airFloor.middleRoom);
        this.startingRoom.allAdjacentRooms.add(this.fireFloor.middleRoom);
        this.startingRoom.allAdjacentRooms.add(this.waterFloor.middleRoom);
        this.startingRoom.allAdjacentRooms.add(this.earthFloor.middleRoom);

        this.airFloor.middleRoom.allAdjacentRooms.add(this.startingRoom);
        this.fireFloor.middleRoom.allAdjacentRooms.add(this.startingRoom);
        this.waterFloor.middleRoom.allAdjacentRooms.add(this.startingRoom);
        this.earthFloor.middleRoom.allAdjacentRooms.add(this.startingRoom);

        assortTreasure();

    }

    private void assortTreasure(){


        List<TreasureBagDecorator> treasureBags = new ArrayList<>();

        int i = 0;
        for(int j = 0; j < 4; j++){
            treasureBags.add(new SwordDecorator());
            i++;
        }
        for(int j = 0; j < 4; j++){
            treasureBags.add(new PotionDecorator());
            i++;
        }
        for(int j = 0; j < 4; j++){
            treasureBags.add(new EtherDecorator());
            i++;
        }
        for(int j = 0; j < 4; j++){
            treasureBags.add(new ElixerDecorator());
            i++;
        }
        for(int j = 0; j < 4; j++){
            treasureBags.add(new ArmorDecorator());
            i++;
        }
        for(int j = 0; j < 4; j++){
            treasureBags.add(new PortalDecorator());
            i++;
        }
        while(i < (fireFloor.rooms.length + waterFloor.rooms.length + airFloor.rooms.length + earthFloor.rooms.length)){
            treasureBags.add(new GemDecorator());
            i++;
        }

        Collections.shuffle(treasureBags);

        i = 0;
        for(int j = 0; j < 9; j++){
            fireFloor.rooms[j].treasure = treasureBags.get(i);
            i++;
        }
        for(int j = 0; j < 9; j++){
            waterFloor.rooms[j].treasure = treasureBags.get(i);
            i++;
        }
        for(int j = 0; j < 9; j++){
            earthFloor.rooms[j].treasure = treasureBags.get(i);
            i++;
        }
        for(int j = 0; j < 9; j++){
            airFloor.rooms[j].treasure = treasureBags.get(i);
            i++;
        }

        this.startingRoom.treasure = new GemDecorator();
    }

    // method to print the starting room and every floor thereafter
    public void printFloor(){


        printStartingRoom();

        this.fireFloor.printFloor();
        this.waterFloor.printFloor();
        this.earthFloor.printFloor();
        this.airFloor.printFloor();
    }


    // Method which defines how to print the starting room
    private void printStartingRoom(){

        String advens = "";
        if(startingRoom.alliedOccupants.isEmpty()){
            advens = "-";
        }
        else{
            for(int i = 0; i < this.startingRoom.alliedOccupants.size(); i++){
                if(this.startingRoom.alliedOccupants.get(i).type == "fire"){
                    advens += "EK,";
                }
                if(this.startingRoom.alliedOccupants.get(i).type == "water"){
                    advens += "MW,";
                }
                if(this.startingRoom.alliedOccupants.get(i).type == "earth"){
                    advens += "TV,";
                }
                if(this.startingRoom.alliedOccupants.get(i).type == "air"){
                    advens += "ZR,";
                }
            }
        }

        String s = "starting Room:\n" + 
        "+------------------------------+\n" +
        "| " + advens + ":- |\n" + 
        "+------------------------------+\n";

        System.out.println(s);
    }

}

