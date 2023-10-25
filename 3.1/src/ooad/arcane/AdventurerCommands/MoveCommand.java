package ooad.arcane.AdventurerCommands;

import ooad.arcane.Adventurer.Adventurer;

public class MoveCommand implements AdventurerCommand{
    private Adventurer adventurer;

    public MoveCommand(Adventurer adv){
        adventurer = adv;
    }

    public void execute(){
        adventurer.Move();
    }
}
