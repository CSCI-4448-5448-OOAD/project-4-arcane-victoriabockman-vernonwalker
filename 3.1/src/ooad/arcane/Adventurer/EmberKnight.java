package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;


public class EmberKnight extends Adventurer {

    public EmberKnight(GameObserver[] observers_){
        health = 5;
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
