package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class ZephyrRogue extends Adventurer {

    public ZephyrRogue(GameObserver[] observers_){
        health = 3;
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