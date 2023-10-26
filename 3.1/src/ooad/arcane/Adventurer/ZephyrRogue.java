package ooad.arcane.Adventurer;

import ooad.arcane.Rooms;
import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class ZephyrRogue extends Adventurer {

     // The new user-controlled Adventurer has twice the starting health from previous projects. 

    public ZephyrRogue(GameObserver[] observers_, Rooms rooms){

        System.out.println("here");
        health = 6;
        isAlive = true;
        treasure = new TreasureBag();

        type = "air";


        dodgeChance = 0.25F;


        if(rooms != null){
            currentRoom = rooms.startingRoom;
            rooms.startingRoom.alliedOccupants.add(this);
        }


        badRoomType = "earth";
        goodRoomType = "air";

        searchStrategy = new NoviceStrategy();
        attackStrategy = new NoviceStrategy();

        observers = observers_;
        rooms_ad = rooms;
    }
}