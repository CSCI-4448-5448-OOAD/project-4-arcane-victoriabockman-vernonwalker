package ooad.arcane.Creatures;

import java.util.Random;

import ooad.arcane.Room;
import ooad.arcane.Adventurer.Adventurers;

public class Fireborn extends Creatures{

    public Fireborn(int num){
        currentRoom = null;
        type = "fire";
        isAlive = true;
        number = num;
    }

    public boolean adventurerPresent(Adventurers ads){

        boolean adPresent = false;

        if(ads.fire.currentRoom.coordinates.floor == type || ads.water.currentRoom.coordinates.floor == type || 
        ads.earth.currentRoom.coordinates.floor == type || ads.air.currentRoom.coordinates.floor == type){

            adPresent = true;

        }

        return adPresent;
    }

    public void Move(Adventurers ads){
    // CONDITIONS:
    // They spawn in the outer rooms and move clockwise or counterclockwise.
    // They won't move if an Adventurer is present on the floor.

    // Check if there's an adventurer present on the floor

        if (!adventurerPresent(ads)) {
            // Generate a random direction (clockwise or counterclockwise)
            Random rand = new Random();
            boolean clockwise = rand.nextBoolean();

            // Get the index of the current room in adjacentRoomsOnFloor
            int currentIndex = currentRoom.adjacentRoomsOnFloor.indexOf(currentRoom);

            // Calculate the index of the next room based on the direction
            int nextIndex;
            if (clockwise) {
                nextIndex = (currentIndex + 1) % currentRoom.adjacentRoomsOnFloor.size();
            } else { // counterclockwise
                nextIndex = (currentIndex - 1 + currentRoom.adjacentRoomsOnFloor.size()) % currentRoom.adjacentRoomsOnFloor.size();
            }

            Room prevRoom = this.currentRoom;

            prevRoom.evilOccupants.remove(this);

            Room nextRoom = currentRoom.adjacentRoomsOnFloor.get(nextIndex);

            this.currentRoom = nextRoom;

            this.currentRoom.evilOccupants.add(this);

            notify("creatureMoved");
        }
    
    }
}
