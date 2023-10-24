package ooad.arcane.Adventurer;

import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.TreasureBag.TreasureBag;

public class TerraVoyager extends Adventurer {

    public TerraVoyager(GameObserver[] observers_){
        health = 7;
        isAlive = true;
        treasure = new TreasureBag();

        type = "earth";

        dodgeChance = 0.1F;

        currentRoom = null;

        badRoomType = "fire";
        goodRoomType = "earth";

        searchStrategy = new NoviceStrategy();
        attackStrategy = new NoviceStrategy();

        observers = observers_;
    }
}
