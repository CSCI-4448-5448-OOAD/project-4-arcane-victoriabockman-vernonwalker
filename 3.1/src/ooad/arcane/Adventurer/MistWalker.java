package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class MistWalker extends Adventurer {

     // The new user-controlled Adventurer has twice the starting health from previous projects. 

    public MistWalker(GameObserver[] observers_){
        health = 6;
        isAlive = true;
        treasure = new TreasureBag();

        type = "water";


        dodgeChance = 0.5F;
        currentRoom = null;

        badRoomType = "air";
        goodRoomType = "water";

        searchStrategy = new NoviceStrategy();
        attackStrategy = new NoviceStrategy();
        this.observers = observers_;
    }
}
