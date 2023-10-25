package ooad.arcane.AdventurerCommands;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creatures.Creature;

public class ExitCommand implements AdventurerCommand {
    private Creature creatures;
    private Adventurer adventurer;

    public ExitCommand(Creature cre, Adventurer adv){
        creatures = cre;
        adventurer = adv;
    }

    public void execute(){
        if (adventurer.treasure.value >= 6000){
            System.out.println(adventurer.name + " has won the game by collecting more than 6000 treasure!");
            System.exit(0);
        }
        else if(creatures.allDead()){
            System.out.println(adventurer.name + " has won the game by killing all their enemies!");
            System.exit(0);
        }
        else{
            System.out.println(adventurer.name + " has lost the game by failing to collect 6000 treasure or kill their enemies...");
            System.exit(0);
        }
    }
}
