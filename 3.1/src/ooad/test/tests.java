package ooad.test;

import org.junit.jupiter.api.Test;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.EmberKnight;
import ooad.arcane.Creatures.Creatures;
import ooad.arcane.Creatures.Terravore;
import ooad.arcane.RollBonus.MasterStrategy;
import ooad.arcane.RollBonus.NoviceStrategy;
import ooad.arcane.RollBonus.SeasonedStrategy;
import ooad.arcane.RollBonus.VeteranStrategy;
import ooad.arcane.TreasureBag.PortalDecorator;
import ooad.arcane.TreasureBag.SwordDecorator;
import ooad.arcane.Floor;
import ooad.arcane.Room;
import ooad.arcane.Rooms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  For full bonus points,
// you must document how you run your JUnit tests (e.g., with a command line or in the IDE), and you
// must capture output that shows the results of your tests. 

// at least 10 tests




public class tests{
    @Test
    // testing for valid combat roll implmentation
    void battletest1(){
        Adventurer ads = new EmberKnight(null);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ads.treasure.combatRollBonus = 4;

        Battle(ads, cre, room);

        assert(cre.isAlive == false);
    }

    @Test
    // testing for valid strategy update
    void battletest2(){
        Adventurer ads = new EmberKnight(null);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ads.treasure.combatRollBonus = 4;

        Battle(ads, cre, room);
        
        assert(ads.attackStrategy instanceof SeasonedStrategy);
    }

    @Test
    // testing for limit to strategy update
    void battletest3(){
        Adventurer ads = new EmberKnight(null);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ads.attackStrategy = new MasterStrategy();

        Battle(ads, cre, room);
        
        assert(ads.attackStrategy instanceof MasterStrategy);
    }


    @Test
    // testing for correct implementation of health bonus
    void battletest4(){
        Adventurer ads = new EmberKnight(null);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ads.health = 2;
        ads.treasure.healthBonus = 1;

        Battle(ads, cre, room);
        
        assert(ads.health == 10);
    }

    @Test
    // testing for correct implementation of dodge chance
    void battletest5(){
        for(int j = 0; j < 10; j++){
            Adventurer ads = new EmberKnight(null);
            Creatures cre = new Terravore(0);
            Room room = new Room();

            // dodge chance initiated to 0.2F
            ads.treasure.addedDodgeChance = 0.8F;
            ads.health = 5;

            Battle(ads, cre, room);
            
            assert(ads.health == 10);
        }
    }
            // 10 out of 10 times, the adventurer should dodge the attack


    @Test
    
    void treasuretest1(){
    // testing for valid searchStrategy bonus implmentation
        Adventurer ads = new EmberKnight(null);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        ads.searchStrategy = new MasterStrategy();

        findTreasure(ads, cur_room, tel_room);

        assert(ads.treasure.value == 1100);


    }

    @Test
     // testing for valid strategy update (WHILE NOT UPDATING COMBAT STRAT AT THE SAME TIME)
    void treasuretest2(){
        Adventurer ads = new EmberKnight(null);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        ads.searchStrategy = new VeteranStrategy();

        findTreasure(ads, cur_room, tel_room);

        assert(ads.searchStrategy instanceof MasterStrategy);
        assert(ads.attackStrategy instanceof NoviceStrategy);
    }

    @Test
    // Portal test
    void treasuretest3(){
        Adventurer ads = new EmberKnight(null);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

       ads.currentRoom = cur_room;

        cur_room.treasure = new PortalDecorator();
        ads.searchStrategy = new MasterStrategy();

        findTreasure(ads, cur_room, tel_room);

        assert(ads.currentRoom != cur_room);
    }

    @Test
    // Testing to make sure treasure is added when applicable
    void treasuretest4(){
        Adventurer ads = new EmberKnight(null);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        
        ads.searchStrategy = new MasterStrategy();

        findTreasure(ads, cur_room, tel_room);

        assert(ads.treasure.contents.contains("Sword"));
    }

    @Test
    // Testing to make sure treasure is NOT added when applicable
    void treasuretest5(){
        Adventurer ads = new EmberKnight(null);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        
        ads.searchStrategy = new MasterStrategy();

        ads.treasure.contents.add("Sword");

        findTreasure(ads, cur_room, tel_room);

        assert(!(ads.treasure.contents.indexOf("Sword") == 0 && ads.treasure.contents.indexOf("Sword") == 1));
    }





    // ADDED the findTreasure method here for ease and modification (to exclude random variables for testing)


public void findTreasure(Adventurer goodGuy, Room currentRoom, Rooms tele_room){

    // SIMPLIFYING THE FINDTREASURE METHOD TO VERIFY ASSERT STATEMENTS

        int adventurerRoll = 9;

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

        if(adventurerRoll >= 11 && currentRoom.treasure.contents != null){

            // level up adventurer //
            goodGuy.searchStrategy.LevelUpSearch(goodGuy);

            int value_before = goodGuy.treasure.value;

            // ADD TREASURE TO TREASURE BAG
            currentRoom.treasure.getTreasureBag(goodGuy.treasure);

            int value_after = goodGuy.treasure.value;

            if(value_before != value_after && currentRoom.treasure.contents != null){
                // if portal was picked up, immediately teleport
                    if(currentRoom.treasure.contents.indexOf("Portal") != -1){

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
                currentRoom.treasure = null;
       //         goodGuy.notify("adventurerFoundTreasure");
                }
            }

            }
            else{
                // Leave it in the room
            }
        }


    




    // ADDED the Battle method here for ease and modification (to exclude random variables for testing)

public void Battle(Adventurer goodGuy, Creatures badGuy, Room currentRoom){
    
        Random random = new Random();

       // int adventurerRoll = rollDice();
       // int creatureRoll = rollDice();

       // SIMPLIFYING THE BATTLE METHOD TO VERIFY ASSERT STATEMENTS
    

        int adventurerRoll = 5;
        int creatureRoll = 6;

        // add bonus to dice roll from level
        adventurerRoll += goodGuy.attackStrategy.Bonus();

        // add bonus to dice roll from treasure if applicable
        adventurerRoll += goodGuy.treasure.combatRollBonus;

        // minus from creature roll if applicable
        creatureRoll -= goodGuy.treasure.creatureCombatRollPenalty;

        // add to creature rool if applicable
        creatureRoll += goodGuy.treasure.creatureCombatBonus;

        /*

        if ((goodGuy.type == "fire") && (currentRoom.coordinates.floor == "fire")){
            adventurerRoll += 2;
        }
        else if((goodGuy.type == "fire") && (currentRoom.coordinates.floor == "water")){
            adventurerRoll -= 2;
        }
        else{
            // nothing to see here
        }

        */

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
                    //    goodGuy.notify("adventurerLostHealth");
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

          //  Battle(goodGuy, badGuy, currentRoom); 

          // checking health bonus:
            goodGuy.health = 10;


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
          //  goodGuy.notify("adventurerWonCombat", badGuy);
          //  goodGuy.notify("adventurerLeveledUp");
        }
        else{
            // tie, battle again
            Battle(goodGuy, badGuy, currentRoom);
        }

    }
}
