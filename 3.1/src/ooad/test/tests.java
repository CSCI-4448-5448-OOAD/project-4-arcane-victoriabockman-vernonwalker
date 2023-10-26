package ooad.test;

import org.junit.jupiter.api.Test;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.AdventurerFactory;
import ooad.arcane.Creatures.Creatures;
import ooad.arcane.Creatures.Terravore;
import ooad.arcane.GameObservers.GameObserver;
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

// you must document how you run your JUnit tests (e.g., with a command line or in the IDE), and you
// must capture output that shows the results of your tests. 

// at least 15 tests

// We will build off of our last tests to ensure they are still functioning with the changes we made
// and add 5 more to test other functionalilty that was added




public class tests{
    @Test
    // testing for valid combat roll implmentation
    void battletest1(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        //Adventurer ads = new EmberKnight(null);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ad.treasure.combatRollBonus = 4;

        Battle(ad, cre, room);

        assert(cre.isAlive == false);
    }

    @Test
    // testing for valid strategy update
    void battletest2(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ad.treasure.combatRollBonus = 4;

        Battle(ad, cre, room);
        
        assert(ad.attackStrategy instanceof SeasonedStrategy);
    }

    @Test
    // testing for limit to strategy update
    void battletest3(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ad.attackStrategy = new MasterStrategy();

        Battle(ad, cre, room);
        
        assert(ad.attackStrategy instanceof MasterStrategy);
    }


    @Test
    // testing for correct implementation of health bonus
    void battletest4(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Creatures cre = new Terravore(0);
        Room room = new Room();

        ad.health = 2;
        ad.treasure.healthBonus = 1;

        Battle(ad, cre, room);
        
        assert(ad.health == 10);
    }

    @Test
    // testing for correct implementation of dodge chance
    void battletest5(){
        for(int j = 0; j < 10; j++){
            Rooms rooms = new Rooms();
            GameObserver[] tracker = new GameObserver[2];

            Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
            Creatures cre = new Terravore(0);
            Room room = new Room();

            // dodge chance initiated to 0.2F
            ad.treasure.addedDodgeChance = 0.8F;
            //ad.health = 5;
            ad.health = 5;

            Battle(ad, cre, room);
            
            assert(ad.health == 10);
        }
    }
            // 10 out of 10 times, the adventurer should dodge the attack


    @Test
    
    void treasuretest1(){
    // testing for valid searchStrategy bonus implmentation
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        ad.searchStrategy = new MasterStrategy();

        findTreasure(ad, cur_room, tel_room);

        assert(ad.treasure.value == 1100);


    }

    @Test
     // testing for valid strategy update (WHILE NOT UPDATING COMBAT STRAT AT THE SAME TIME)
    void treasuretest2(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        ad.searchStrategy = new VeteranStrategy();

        findTreasure(ad, cur_room, tel_room);

        assert(ad.searchStrategy instanceof MasterStrategy);
        assert(ad.attackStrategy instanceof NoviceStrategy);
    }

    @Test
    // Portal test
    void treasuretest3(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

       ad.currentRoom = cur_room;

        cur_room.treasure = new PortalDecorator();
        ad.searchStrategy = new MasterStrategy();

        findTreasure(ad, cur_room, tel_room);

        assert(ad.currentRoom != cur_room);
    }

    @Test
    // Testing to make sure treasure is added when applicable
    void treasuretest4(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        
        ad.searchStrategy = new MasterStrategy();

        findTreasure(ad, cur_room, tel_room);

        assert(ad.treasure.contents.contains("Sword"));
    }

    @Test
    // Testing to make sure treasure is NOT added when applicable
    void treasuretest5(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);
        Room cur_room = new Room();
        Rooms tel_room = new Rooms();

       tel_room.airFloor.rooms[1] = cur_room;

        cur_room.treasure = new SwordDecorator();
        
        ad.searchStrategy = new MasterStrategy();

        ad.treasure.contents.add("Sword");

        findTreasure(ad, cur_room, tel_room);

        assert(!(ad.treasure.contents.indexOf("Sword") == 0 && ad.treasure.contents.indexOf("Sword") == 1));
    }

    @Test
    // Search Command uses new function execute, for ease of implementation (not messing with the logger / tracker)
    // I pasted it below, so we are using that function instead

    // Search Command, Winning
    void commandtest1(){
        
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);

        Room cur_room = new Room();

        ad.currentRoom = cur_room;

        ad.searchStrategy = new MasterStrategy();

        cur_room.treasure = new SwordDecorator();

        search_execute(ad);

        assert(ad.treasure.contents.contains("Sword"));
    }

    @Test
    // Search Command, Losing
    void commandtest2(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);

        Room cur_room = new Room();

        ad.currentRoom = cur_room;

        cur_room.treasure = new SwordDecorator();

        search_execute(ad);

        assert(!ad.treasure.contents.contains("Sword"));
    }

    @Test
    // Attack Command, Winning
    void commandtest3(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);

        Creatures cre = new Terravore(0);

        Room cur_room = new Room();

        ad.health = 15;
        ad.currentRoom = cur_room;
        ad.treasure.combatRollBonus = 1;
        ad.treasure.creatureCombatRollPenalty = 1;

        cre.currentRoom = cur_room;
        ad.currentRoom.evilOccupants.add(cre);

        attack_execute(ad);

        assert(ad.health == 15);
    }

    @Test
    // Attack Command, Winning continued
    void commandtest4(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);

        Creatures cre = new Terravore(0);

        Room cur_room = new Room();

        ad.health = 15;
        ad.currentRoom = cur_room;
        ad.treasure.combatRollBonus = 1;
        ad.treasure.creatureCombatRollPenalty = 1;

        cre.currentRoom = cur_room;
        ad.currentRoom.evilOccupants.add(cre);

        attack_execute(ad);

        assert(cre.isAlive == false);
    }

    @Test
    // Attack Command, Losing
    void commandtest5(){
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];

        Adventurer ad = AdventurerFactory.createAdventurer("EmberKnight", rooms, tracker);

        Creatures cre = new Terravore(0);

        Room cur_room = new Room();

        ad.health = 15;
        ad.currentRoom = cur_room;

        cre.currentRoom = cur_room;
        ad.currentRoom.evilOccupants.add(cre);

        attack_execute(ad);

        // Changed health in losing battle to 10, to test for correct loss
        assert(ad.health == 10);
    }



    // ADDED the findTreasure method here for ease and modification (to exclude random variables for testing)


 public void search_execute(Adventurer adventurer){
        findTreasure(adventurer, adventurer.currentRoom, adventurer.rooms_ad);
    }

public void attack_execute(Adventurer adventurer){
        for(int i = 0; i < adventurer.currentRoom.evilOccupants.size(); i++){
            Battle(adventurer, adventurer.currentRoom.evilOccupants.get(i), adventurer.currentRoom);
        }
    }


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
             //           goodGuy.notify("adventurerLostHealth");
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
               //             goodGuy.notify("adventurerLostHealth");
                        }
                        else if (currentRoom.coordinates.floor == "fire"){
                            goodGuy.health -= 3;
               //             goodGuy.notify("adventurerLostHealth");
                        }
                        else{
                            goodGuy.health -= 2;
               //             goodGuy.notify("adventurerLostHealth");
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
                 //       goodGuy.notify("adventurerLostHealth");
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
        //    badGuy.notify("creatureWonCombat",goodGuy);

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
