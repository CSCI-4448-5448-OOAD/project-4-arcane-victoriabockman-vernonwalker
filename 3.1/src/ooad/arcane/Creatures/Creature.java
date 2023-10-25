package ooad.arcane.Creatures;

import java.util.Random;
import ooad.arcane.Rooms;
import ooad.arcane.Adventurer.Adventurers;
import ooad.arcane.GameObservers.GameObserver;


public class Creature {

    public Fireborn[] creaturesFireborn = new Fireborn[4];
    public Aquarid[] creaturesAquarid = new Aquarid[4];
    public Zephyral[] creaturesZephyral = new Zephyral[4];
    public Terravore[] creaturesTerravore = new Terravore[4];
    public Adventurers ads;
    public CreaturesFactory CF = new CreaturesFactory();


    public Creature(Rooms rooms, Adventurers ad){

        ads = ad;

        creaturesFireborn[0] = (Fireborn) CF.createCreatures("Fireborn", 0);
        creaturesFireborn[1] = (Fireborn) CF.createCreatures("Fireborn", 1);
        creaturesFireborn[2] = (Fireborn) CF.createCreatures("Fireborn", 2);
        creaturesFireborn[3] = (Fireborn) CF.createCreatures("Fireborn", 3);

        creaturesAquarid[0] = (Aquarid) CF.createCreatures("Aquarid", 0);
        creaturesAquarid[1] = (Aquarid) CF.createCreatures("Aquarid", 1);
        creaturesAquarid[2] = (Aquarid) CF.createCreatures("Aquarid", 2);
        creaturesAquarid[3] = (Aquarid) CF.createCreatures("Aquarid", 3);

        creaturesZephyral[0] = (Zephyral) CF.createCreatures("Zephyral", 0);
        creaturesZephyral[1] = (Zephyral) CF.createCreatures("Zephyral", 1);
        creaturesZephyral[2] = (Zephyral) CF.createCreatures("Zephyral", 2);
        creaturesZephyral[3] = (Zephyral) CF.createCreatures("Zephyral", 3);

        creaturesTerravore[0] = (Terravore) CF.createCreatures("Terravore", 0);
        creaturesTerravore[1] = (Terravore) CF.createCreatures("Terravore", 1);
        creaturesTerravore[2] = (Terravore) CF.createCreatures("Terravore", 2);
        creaturesTerravore[3] = (Terravore) CF.createCreatures("Terravore", 3);

        Random rand = new Random();

        // Put Fireborns into room
        for(int i = 0; i < 4; i++){
            int randomCoordinate = rand.nextInt(7) + 0;
            if(randomCoordinate == 4){
                randomCoordinate = 8;
            }
            creaturesFireborn[i].currentRoom = rooms.fireFloor.rooms[randomCoordinate];
            rooms.fireFloor.rooms[randomCoordinate].evilOccupants.add(creaturesFireborn[i]);
        }

        // Put Aquarids into room
        for(int i = 0; i < 4; i++){
            int randomCoordinate = rand.nextInt(7) + 0;
            if(randomCoordinate == 4){
                randomCoordinate = 8;
            }
            creaturesAquarid[i].currentRoom = rooms.waterFloor.rooms[randomCoordinate];
            rooms.waterFloor.rooms[randomCoordinate].evilOccupants.add(creaturesAquarid[i]);
        }

        // Put Zephyrals into room
        for(int i = 0; i < 4; i++){
            int randomCoordinate = rand.nextInt(7) + 0;
            if(randomCoordinate == 4){
                randomCoordinate = 8;
            }
            creaturesZephyral[i].currentRoom = rooms.airFloor.rooms[randomCoordinate];
            rooms.airFloor.rooms[randomCoordinate].evilOccupants.add(creaturesZephyral[i]);
        }

        // Put Terravores into room
        for(int i = 0; i < 4; i++){
            int randomCoordinate = rand.nextInt(7) + 0;
            if(randomCoordinate == 4){
                randomCoordinate = 8;
            }
            creaturesTerravore[i].currentRoom = rooms.earthFloor.rooms[randomCoordinate];
            rooms.earthFloor.rooms[randomCoordinate].evilOccupants.add(creaturesTerravore[i]);
        }
    }

    public int countAlive(Creatures[] creatureList){

        int count = 0;

        for(int i = 0; i < 4; i++){
            if(creatureList[i].isAlive == true){
                count += 1;
            }
        }

        return count;
    }

    public void printCreatures(){

        String s = "Fireborns - " + countAlive(creaturesFireborn) + " Remaining\n" + //
                "Aquarids - " + countAlive(creaturesAquarid) + " Remaining\n" + //
                "Zephyrals - " + countAlive(creaturesZephyral) + " Remaining\n" + //
                "Terravores - " + countAlive(creaturesTerravore) + " Remaining\n";

        System.out.println(s);

    }

    public boolean allDead(){
        int howManyAlive = countAlive(creaturesFireborn) + countAlive(creaturesAquarid)
         + countAlive(creaturesZephyral) + countAlive(creaturesTerravore);

        if(howManyAlive == 0){
            return true;
        }
        else{
            return false;
        }
    }


    public void move(){
        for(int i = 0; i < 4; i++){
            if(creaturesFireborn[i].isAlive == true){
                creaturesFireborn[i].Move(ads);
            }
        }
        for(int i = 0; i < 4; i++){
            if(creaturesAquarid[i].isAlive == true){
                creaturesAquarid[i].Move(ads);
            }
        }
        for(int i = 0; i < 4; i++){
            if(creaturesZephyral[i].isAlive == true){
                creaturesZephyral[i].Move();
            }
        }
        for(int i = 0; i < 4; i++){
            if(creaturesTerravore[i].isAlive == true){
                creaturesTerravore[i].Move();
            }
        }
    }

    public void updateObservers(GameObserver[] observers){
        for(int i = 0; i < 4; i++){
            creaturesFireborn[i].gameObservers = observers;
            creaturesTerravore[i].gameObservers = observers;
            creaturesAquarid[i].gameObservers = observers;
            creaturesZephyral[i].gameObservers = observers;
        }
    }
    
}