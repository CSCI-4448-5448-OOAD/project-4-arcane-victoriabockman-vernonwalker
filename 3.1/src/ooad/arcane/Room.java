package ooad.arcane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Creatures.Creatures;
import ooad.arcane.TreasureBag.TreasureBag;

public class Room {

    public Coordinates coordinates = new Coordinates();

    public List<Adventurer> alliedOccupants = new ArrayList<>();
    public List<Creatures> evilOccupants = new ArrayList<>();
    public TreasureBag treasure;

    // for creatures only
    public List<Room> adjacentRoomsOnFloor = new ArrayList<>();

    // for adventurers only
    public List<Room> allAdjacentRooms = new ArrayList<>();

    public Integer rollDice(){
        Random rand = new Random();

        int diceOne = rand.nextInt(6) + 1;
        int diceTwo = rand.nextInt(6) + 1;

        return (diceOne + diceTwo);

    }


    public void Battle(Adventurer goodGuy, Creatures badGuy, Room currentRoom){
        Random random = new Random();

        int adventurerRoll = rollDice();
        int creatureRoll = rollDice();

        // add bonus to dice roll from level
        adventurerRoll += goodGuy.attackStrategy.Bonus();

        // add bonus to dice roll from treasure if applicable
        adventurerRoll += goodGuy.treasure.combatRollBonus;

        // minus from creature roll if applicable
        creatureRoll -= goodGuy.treasure.creatureCombatRollPenalty;

        // add to creature rool if applicable
        creatureRoll += goodGuy.treasure.creatureCombatBonus;

        if ((goodGuy.type == "fire") && (currentRoom.coordinates.floor == "fire")){
            adventurerRoll += 2;
        }
        else if((goodGuy.type == "fire") && (currentRoom.coordinates.floor == "water")){
            adventurerRoll -= 2;
        }
        else{
            // nothing to see here
        }

        if (creatureRoll > adventurerRoll){
            // adventurer has a chance to dodge, else loses health point(s), battle again if adventurer not dead
            float dodgeChance = goodGuy.dodgeChance;
            boolean dodge;

            switch(goodGuy.type) {
                case "fire":

                    // check for added dodge chance
                    dodgeChance += goodGuy.treasure.addedDodgeChance;
                    dodge = random.nextFloat() < dodgeChance;

                    if (dodge){
                        // dodged attack
                    }
                    else{
                        goodGuy.health -= 2;
                        goodGuy.notify("adventurerLostHealth");
                    }

                  break;

                case "water":
                    if (currentRoom.coordinates.floor == "water"){
                        dodgeChance = 0.75F;

                    }
                    else if (currentRoom.coordinates.floor == "air"){
                        dodgeChance = 0.25F;

                    }
                    else{
                        // do nothing
                    }

                    // check for added dodge chance
                    dodgeChance += goodGuy.treasure.addedDodgeChance;
                    dodge = random.nextFloat() < dodgeChance;

                    if (dodge){
                        // dodged attack
                    }
                    else{
                        goodGuy.health -= 2;
                        goodGuy.notify("adventurerLostHealth");
                    }

                  break;

                case "earth":
                    // check for added dodge chance
                    dodgeChance += goodGuy.treasure.addedDodgeChance;
                    dodge = random.nextFloat() < dodgeChance;

                    if (dodge){
                        // dodged attack
                    }
                    else{
                        if(currentRoom.coordinates.floor == "earth"){
                            goodGuy.health -= 1;
                            goodGuy.notify("adventurerLostHealth");
                        }
                        else if (currentRoom.coordinates.floor == "fire"){
                            goodGuy.health -= 3;
                            goodGuy.notify("adventurerLostHealth");
                        }
                        else{
                            goodGuy.health -= 2;
                            goodGuy.notify("adventurerLostHealth");
                        }
                    }

                  break;

                case "air":
                    // check for added dodge chance
                    dodgeChance += goodGuy.treasure.addedDodgeChance;
                    dodge = random.nextFloat() < dodgeChance;

                    if (dodge){
                        // dodged attack
                    }
                    else{
                        goodGuy.health -= 2;
                        goodGuy.notify("adventurerLostHealth");
                    }

                  break;
              }

              // check health + health bonus ???
           if ((goodGuy.health + goodGuy.treasure.healthBonus) > 0){

            Battle(goodGuy, badGuy, currentRoom); 

           }
           else{
            // adventurer dead, battle over
            goodGuy.isAlive = false;
            currentRoom.alliedOccupants.remove(goodGuy);
            badGuy.notify("creatureWonCombat",goodGuy);

           }
        }
        else if (adventurerRoll > creatureRoll){
            // creature dies, battle over
            badGuy.Die();
            currentRoom.evilOccupants.remove(badGuy);

            // level up adventurer
            goodGuy.attackStrategy.LevelUpAttack(goodGuy);
            goodGuy.notify("adventurerWonCombat", badGuy);
            goodGuy.notify("adventurerLeveledUp");
        }
        else{
            // tie, battle again
            Battle(goodGuy, badGuy, currentRoom);
        }

    }

    public class Coordinates {
        public String floor;
        public int x;
        public int y;
    }

}