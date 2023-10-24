package ooad.arcane;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ooad.arcane.Adventurer.Adventurer;

public class Treasure {

   // int totalTreasure;

    public Integer rollDice(){
        Random rand = new Random();

        int diceOne = rand.nextInt(6) + 1;
        int diceTwo = rand.nextInt(6) + 1;

        return (diceOne + diceTwo);

    }

    public void findTreasure(Adventurer goodGuy, Room currentRoom, Rooms tele_room){

        int adventurerRoll = rollDice();

        // add bonus to dice roll
        adventurerRoll += goodGuy.searchStrategy.Bonus();

        if ((goodGuy.type == "air") && (currentRoom.coordinates.floor == "air")){
            adventurerRoll += 2;
        }
        else if((goodGuy.type == "air") && (currentRoom.coordinates.floor == "earth")){
            adventurerRoll -= 2;
        }
        else{
            // nothing to see here
        }

        // roll of 7 or higher for treasure now
        if(adventurerRoll >= 7 && currentRoom.treasure != null){

            // level up adventurer //
            goodGuy.searchStrategy.LevelUpSearch(goodGuy);

            int value_before = goodGuy.treasure.value;

            // ADD TREASURE TO TREASURE BAG
            currentRoom.treasure.getTreasureBag(goodGuy.treasure);

            int value_after = goodGuy.treasure.value;

            if(value_before != value_after){
                // if portal was picked up, immediately teleport
                if(currentRoom.treasure.contents.contains("Portal")){

                    // Remove treasure from room
                    goodGuy.currentRoom.treasure = null;

                    List<Floor> rand_floor = Arrays.asList(tele_room.airFloor, tele_room.earthFloor, tele_room.fireFloor, tele_room.waterFloor);

                    Collections.shuffle(rand_floor);

                    Random rn = new Random();
                    int rand_room = rn.nextInt(9) + 0;

                    goodGuy.currentRoom = rand_floor.get(0).rooms[rand_room];
                }
                else{
                    // Remove treasure from room
                    goodGuy.currentRoom.treasure = null;
                    if((value_after - value_before) != 1000){
                        goodGuy.notify("adventurerFoundTreasure");
                    }
                }   
            
            }
            else{
                // Leave it in the room
            }
        }

    }


}
