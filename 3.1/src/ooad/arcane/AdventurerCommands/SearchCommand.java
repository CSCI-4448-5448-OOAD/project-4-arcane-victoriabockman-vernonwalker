package ooad.arcane.AdventurerCommands;

import ooad.arcane.Adventurer.Adventurer;

// Search Command

public class SearchCommand implements AdventurerCommand{
    private Adventurer adventurer;

    public SearchCommand(Adventurer adv){
        adventurer = adv;
    }

    public void execute(){
        adventurer.findTreasure(adventurer, adventurer.currentRoom, adventurer.rooms_ad);
    }
}
