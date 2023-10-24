package ooad.arcane;

import java.io.FileNotFoundException;
import java.io.File;

import ooad.arcane.Adventurer.Adventurers;
import ooad.arcane.Creatures.Creature;
import ooad.arcane.GameObservers.GameObserver;
import ooad.arcane.GameObservers.Logger;
import ooad.arcane.GameObservers.Tracker;


public class GameDriver {

    public static void main(String[] args) throws FileNotFoundException {
        // Placeholder comment

        File dir = new File("Logger");
        dir.mkdir();
        for(File file: dir.listFiles()){
            if (!file.isDirectory()){
                file.delete();
            }
        }
        Rooms rooms = new Rooms();
        GameObserver[] tracker = new GameObserver[2];
        tracker[0] = new Tracker();
        Logger logger = new Logger(0);
        tracker[1] = logger;
        Adventurers ads = new Adventurers(rooms, tracker);
        Creature cres = new Creature(rooms, ads);
        int loop = 1;
        while(true){

            logger = new Logger(loop);
            tracker[1] = logger;
            ads.updateObservers(tracker);
            cres.updateObservers(tracker);
            System.out.println("Current Loop: " + loop + "\n");
            rooms.printFloor();
            ads.printAdventurers();
            cres.printCreatures();
            ads.move();
            cres.move();
            tracker[0].printTurn(loop);
            tracker[1].printTurn(loop);
            loop++;
            
            // Check whether all 24 treasure items are found
            if((ads.fire.treasure.contents.size() + ads.water.treasure.contents.size() + ads.earth.treasure.contents.size() + ads.air.treasure.contents.size()) >= 24){
                System.out.println("Adventurers won the game by collecting 15000 in treasure!");
                break;
            }

            // Check whether adventurers have at least 15000 in treasure points
            if((ads.fire.treasure.value + ads.water.treasure.value + ads.earth.treasure.value + ads.air.treasure.value) >= 15000){
                System.out.println("Adventurers won the game by collecting 15000 in treasure!");
                break;
            }

            // Adventurers dead
            if(!ads.fire.isAlive && !ads.water.isAlive && !ads.earth.isAlive && !ads.air.isAlive){
                System.out.println("Adventurers lost the game by being destoyed by enemies!");
                break;
            }

            // Creatures dead
            if(cres.allDead()){
                System.out.println("Adventurers won by slaying all their enemies!");
                break;
            }

            tracker[1].closeFile();
        }    
    }

}
