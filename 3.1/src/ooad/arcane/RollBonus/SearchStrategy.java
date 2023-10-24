package ooad.arcane.RollBonus;

import ooad.arcane.Adventurer.Adventurer;

// STRATEGY PATTERN

public interface SearchStrategy {
    
    // Same signature as attack, can I do this? Should I do this?
    
    int Bonus();

    default void LevelUpSearch(Adventurer goodGuy){

        if(goodGuy.searchStrategy instanceof NoviceStrategy){
            goodGuy.searchStrategy = new SeasonedStrategy();
        }
        else if(goodGuy.searchStrategy instanceof SeasonedStrategy){
            goodGuy.searchStrategy = new VeteranStrategy();
        }
        else if(goodGuy.searchStrategy instanceof VeteranStrategy){
            goodGuy.searchStrategy = new MasterStrategy();
        }
        else{
            // do nothing, has reached highest level
        }
    }

}
