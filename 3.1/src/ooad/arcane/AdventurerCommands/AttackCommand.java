package ooad.arcane.AdventurerCommands;

import ooad.arcane.Adventurer.Adventurer;

public class AttackCommand implements AdventurerCommand{
    private Adventurer adventurer;

    public AttackCommand(Adventurer adv){
        adventurer = adv;
    }

    public void execute(){
        for(int i = 0; i < adventurer.currentRoom.evilOccupants.size(); i++){
            adventurer.currentRoom.Battle(adventurer, adventurer.currentRoom.evilOccupants.get(i), adventurer.currentRoom);
        }
    }
}
