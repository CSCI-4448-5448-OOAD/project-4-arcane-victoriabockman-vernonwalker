package ooad.arcane.Creatures;

import java.util.Random;

import ooad.arcane.Room;
import ooad.arcane.Adventurer.Adventurer;

public class Fireborn extends Creatures{

    public Fireborn(int num){
        currentRoom = null;
        type = "fire";
        isAlive = true;
        number = num;
    }

    public boolean adventurerPresent(Adventurer ad){

        boolean adPresent = false;

        if(ad.currentRoom.coordinates.floor.toLowerCase().equals("fire")){

            adPresent = true;

        }

        return adPresent;
    }

    public void Move(Adventurer ad){
    // CONDITIONS:
    // They spawn in the outer rooms and move clockwise or counterclockwise.
    // They won't move if an Adventurer is present on the floor.

    // Check if there's an adventurer present on the floor

        if (!adventurerPresent(ad)) {
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
