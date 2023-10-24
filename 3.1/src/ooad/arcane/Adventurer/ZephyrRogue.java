package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class ZephyrRogue extends Adventurer {

     // The new user-controlled Adventurer has twice the starting health from previous projects. 

    public ZephyrRogue(GameObserver[] observers_){
        health = 6;
        isAlive = true;
        treasure = new TreasureBag();

        type = "air";


        dodgeChance = 0.25F;


        currentRoom = null;

        badRoomType = "earth";
        goodRoomType = "air";

        searchStrategy = new NoviceStrategy();
        attackStrategy = new NoviceStrategy();

        observers = observers_;
    }
}