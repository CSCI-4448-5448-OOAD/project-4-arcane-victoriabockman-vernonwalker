package ooad.arcane.Adventurer;

import ooad.arcane.Rooms;
import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class TerraVoyager extends Adventurer {

     // The new user-controlled Adventurer has twice the starting health from previous projects. 

    public TerraVoyager(GameObserver[] observers_, Rooms rooms){
        health = 14;
        isAlive = true;
        treasure = new TreasureBag();

        type = "earth";

        dodgeChance = 0.1F;

        if(rooms != null){
            currentRoom = rooms.startingRoom;
            rooms.startingRoom.alliedOccupants.add(this);
        }


        badRoomType = "fire";
        goodRoomType = "earth";

        searchStrategy = new NoviceStrategy();
        attackStrategy = new NoviceStrategy();

        observers = observers_;
        rooms_ad = rooms;
    }
}
