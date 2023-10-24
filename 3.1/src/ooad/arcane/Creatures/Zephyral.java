package ooad.arcane.Creatures;

import java.util.Random;

import ooad.arcane.Room;

public class Zephyral extends Creatures{
    
    public Zephyral(int num){
        currentRoom = null;
        type = "air";
        isAlive = true;
        number = num;
    }
    // CONDITIONS:
    // They randomly move between rooms on the Air floor. They won't move if an Adventurer is present.

    // Check if there's an adventurer present in the current room

    public void Move(){
    boolean adventurerPresent = !(currentRoom.alliedOccupants.isEmpty());

    if (!adventurerPresent) {
            // Move to randomly generated adjacent room
            int size = this.currentRoom.adjacentRoomsOnFloor.size();

            Random rand = new Random();

            int choice = rand.nextInt(size);

            Room prevRoom = this.currentRoom;

            this.currentRoom = prevRoom.adjacentRoomsOnFloor.get(choice);

            prevRoom.evilOccupants.remove(this);

            this.currentRoom.evilOccupants.add(this);

        }

        notify("creatureMoved");
    }
}
