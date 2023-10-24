package ooad.arcane.TreasureBag;

import java.util.ArrayList;
import java.util.List;

// DECORATOR PATTERN

// object for processing adventurer treasure and looting
public class TreasureBag {
    
    // value is just the amount of value contained in the bag
    public int value = 0;

    // contents is a list of objects that the adventurer has picked up (excluding gems)
    public List<String> contents = new ArrayList<>();

    // all of the below are the benefits granted by certain treasure items
    public float addedDodgeChance = 0; // increases adventurer dodge chance
    public float addedTeleportChance = 0; // increases adventurer teleport chance (unused)

    public int creatureCombatRollPenalty = 0; // decreases creature combat roll
    public int treasureRollBonus = 0; // increases adventurer treasure roll
    public int combatRollBonus = 0; // increases adventurer combat roll
    public int healthBonus = 0; // gives adventurer health

    public int creatureCombatBonus = 0; // measures the amount of gems that were picked up (each one increases creature combat roll)


    // method which will get decorated, just returns the argument for now
    public TreasureBag getTreasureBag(TreasureBag t_bag){
        return t_bag;
    }

}
