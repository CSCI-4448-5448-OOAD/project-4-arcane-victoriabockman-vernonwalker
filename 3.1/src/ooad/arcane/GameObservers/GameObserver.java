package ooad.arcane.GameObservers;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creatures.Creatures;

// OBSERVER PATTERN

public interface GameObserver {
    
    // update that is called if the update involves an Adventurer moving
    public void adventurerMoved(Adventurer adventurer); // check

    // update that is called if the update involves a creature moving
    public void creatureMoved(Creatures creature); // check

    // update that is called if the update involves a adventurer winning combat (and killing a creature in the process)
    public void adventurerWonCombat(Adventurer adventurer, Creatures creature); // check

    // update that is called if the update involves a Creature winning combat (and killing an adventurer in the process)
    public void creatureWonCombat(Adventurer adventurer, Creatures creature); // check

    // update that is called if the update involves an adventurer leveling up
    public void adventurerLeveledUp(Adventurer adventurer); // check

    // update that is called if the update involves an adventurer getting resonance
    public void adventurerGainedResonance(Adventurer adventurer); // check

    // update that is called it the update involves an adventurer getting discord
    public void adventurerGainedDiscord(Adventurer adventurer); // check

    // update that is called if an adventurer lost health due to getting hit by a creature
    public void adventurerLostHealth(Adventurer adventurer); // check

    // update that is called if an adventurer found treasure
    public void adventurerFoundTreasure(Adventurer adventurer); // check

    // method that the tracker uses and the logger uses to simply print the current turn number
    public void printTurn(int turn);

    // logger method to flush and close the current text file
    public void closeFile();

    public void loggerDeleter();

}
