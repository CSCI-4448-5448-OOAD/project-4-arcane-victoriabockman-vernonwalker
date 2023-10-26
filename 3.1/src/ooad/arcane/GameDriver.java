package ooad.arcane;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

import ooad.arcane.Adventurer.Adventurer;
import ooad.arcane.Adventurer.AdventurerFactory;
import ooad.arcane.AdventurerCommands.AttackCommand;
import ooad.arcane.AdventurerCommands.ExitCommand;
import ooad.arcane.AdventurerCommands.MoveCommand;
import ooad.arcane.AdventurerCommands.SearchCommand;
import ooad.arcane.Creatures.Creature;
import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.GameObservers.Logger;
import ooad.arcane.GameObservers.Tracker;


public class GameDriver {

    public static void main(String[] args) throws FileNotFoundException {

        File dir = new File("Logger");
        dir.mkdir();
        for(File file: dir.listFiles()){
            if (!file.isDirectory()){
                file.delete();
            }
        }
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];
        tracker[0] = null;
        Logger logger = Logger.getInstance(0);
        tracker[1] = logger;

        int loop = 1;


        // Begin the game and user input

        System.out.println("Welcome to Dungeon Crawler CSCI4448");
        String name = null;
        String choice = null;
        int num = 1;
        Scanner reader = new Scanner(System.in);
        while(num == 1){

            System.out.println("Choose your player!");

            System.out.println("Enter 'E' for Ember Knight.");
            System.out.println("Enter 'M' for Mist Walker.");
            System.out.println("Enter 'T' for Terra Voyager.");
            System.out.println("Enter 'Z' for Zephyr Rogue.");

            choice = (String)reader.nextLine();
            choice = choice.trim();
            choice = choice.toLowerCase();

            if (choice.equals("e")){
                choice = "fire";
                break;
            }
            else if(choice.equals("m")){
                choice = "water";
                break;
            }
            else if(choice.equals("t")){
                choice = "earth";
                break;
            }
            else if(choice.equals("z")){
                choice = "air";
                break;
            }
            else{
                System.out.println("Invalid Option. Try again.");
            }

        }

        // By the time we get here, Player has made a choice.

        // initialize our characters

        Adventurer ad = AdventurerFactory.createAdventurer(choice, rooms, tracker);

        Creature cres = new Creature(rooms, ad);


        // have user name their adventurer
        System.out.println("Name your player: ");

        name = reader.nextLine();

        ad.name = name.trim();

        System.out.println(ad.name);

        // eager instantiation of tracker object (because we know we will need it)
        tracker[0] = Tracker.getInstance(ad);
        ad.updateObservers(tracker);
        


        AttackCommand attackCommand = new AttackCommand(ad); 
        ExitCommand exitCommand = new ExitCommand(cres, ad);
        SearchCommand searchCommand = new SearchCommand(ad);
        MoveCommand moveCommand = new MoveCommand(ad);

        while(true){

            // resetting logger (lazy instantiation since we only use when we need it, which in this case is at the start of this while loop)
            logger = Logger.getInstance(loop);
            tracker[1] = logger;
            ad.updateObservers(tracker);
            cres.updateObservers(tracker);
            // printing curr loop
            System.out.println("Current Loop: " + loop + "\n");
            // printing map
            rooms.printFloor();
            // printing adventurer state
            ad.printAdventurers(ad.name, ad);
            // printing creature states
            cres.printCreatures();

            // User makes choice here

            int Num = 1;
            while(Num == 1){

                System.out.println("Choose your next move!");

                System.out.println("Enter 'S' to Search.");
                System.out.println("Enter 'M' to Move.");
                System.out.println("Enter 'A' to Attack.");
                if(ad.currentRoom == rooms.startingRoom){
                    System.out.println("Enter 'E' to Exit.");
                }

                choice = reader.nextLine();
                choice = choice.trim();
                choice = choice.toLowerCase();

                if (choice.equals("s")){
                    Num = 0;
                    searchCommand.execute();
                }
                else if(choice.equals("m")){
                    Num = 0;
                    moveCommand.execute();
                }
                else if(choice.equals("a")){
                    Num = 0;
                    attackCommand.execute();
                }
                else if((choice.equals("e")) && (ad.currentRoom == rooms.startingRoom)){
                    Num = 0;
                    exitCommand.execute();
                }
                else{
                    System.out.println("Invalid Option. Try again.");
                }

            }

            // move all the creatures
            cres.move();

            // print all necessary info stored in observers
            tracker[0].printTurn(loop);
            tracker[1].printTurn(loop);
            loop++;
            
            // if adventurer dies, then just immediately stop the game
            if(!ad.isAlive){
                System.out.println(name +" lost the game by being destoyed by enemies!");
                break;
            }

            // close logger file and delete singleton instance
            tracker[1].closeFile();
            tracker[1].loggerDeleter();
        }
        
        reader.close();

        
    }

}
