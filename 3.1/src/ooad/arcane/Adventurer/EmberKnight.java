package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;


public class EmberKnight extends Adventurer {

     // The new user-controlled Adventurer has twice the starting health from previous projects. 

    public EmberKnight(GameObserver[] observers_){
        health = 10;
        isAlive = true;
        treasure = new TreasureBag();

        type = "fire";

        dodgeChance = 0.2F;
        currentRoom = null;

        badRoomType = "water";
        goodRoomType = "fire";
        
        searchStrategy = new NoviceStrategy();
        attackStrategy = new NoviceStrategy();
        this.observers = observers_;
    }
}
