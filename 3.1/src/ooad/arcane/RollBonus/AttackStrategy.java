package ooad.arcane.RollBonus;

import ooad.arcane.Adventurer.Adventurer;

// STRATEGY PATTERN

public interface AttackStrategy {
    
    int Bonus();

    default void LevelUpAttack(Adventurer goodGuy){
        if(goodGuy.attackStrategy instanceof NoviceStrategy){
            goodGuy.attackStrategy = new SeasonedStrategy();
        }
        else if(goodGuy.attackStrategy instanceof SeasonedStrategy){
            goodGuy.attackStrategy = new VeteranStrategy();
        }
        else if(goodGuy.attackStrategy instanceof VeteranStrategy){
            goodGuy.attackStrategy = new MasterStrategy();
        }
        else{
            // do nothing, has reached highest level
        }
    }
}

// remember to update battle and treasure to update adventurer strategy!


