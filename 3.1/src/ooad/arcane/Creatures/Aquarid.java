package ooad.arcane.Creatures;

import java.util.Random;

import ooad.arcane.Room;
import ooad.arcane.Adventurer.Adventurers;

public class Aquarid extends Creatures{
    
    public Aquarid(int num){
        currentRoom = null;
        type = "water";
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
        // If they are in a room adjacent to an Adventurer, they will move into that Adventurer's room.
        // If no Adventurer is adjacent or if an Adventurer is already on their floor, Aquarids won't move.

        boolean Moved = false;

        int adjaRooms = currentRoom.adjacentRoomsOnFloor.size();

        for(int i = 0; i < adjaRooms; i++){


            if(!(currentRoom.adjacentRoomsOnFloor.get(i).alliedOccupants.isEmpty())){

                Room prevRoom = this.currentRoom;

                this.currentRoom = prevRoom.adjacentRoomsOnFloor.get(i);

                prevRoom.evilOccupants.remove(this);

                this.currentRoom.evilOccupants.add(this);

                Moved = true;

                break;
            }

        }

        if(Moved == false){
            if(adventurerPresent(ads)){
                // dont move
            }
            else{
                //move into randomly generated adjacent room
                // little vague. This is what we interpreted

                int size = this.currentRoom.adjacentRoomsOnFloor.size();

                Random rand = new Random();

                int choice = rand.nextInt(size);

                Room prevRoom = this.currentRoom;

                this.currentRoom = prevRoom.adjacentRoomsOnFloor.get(choice);

                prevRoom.evilOccupants.remove(this);

                this.currentRoom.evilOccupants.add(this);
            }


        }
        notify("creatureMoved");
    }
}
