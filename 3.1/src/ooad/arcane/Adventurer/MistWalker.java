package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class MistWalker extends Adventurer {

    public MistWalker(GameObserver[] observers_){
        health = 3;
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
