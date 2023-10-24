package ooad.arcane;

// Floor class which stores 9 rooms on the floor and deals with printing the rooms.
public class Floor {
        public String type;
        public Room[] rooms = new Room[ 9 ];
        public Room middleRoom = rooms[4];
    

        // constructor for room graph for rooms on floor.
        Floor(String s){
    
            // initializes each room.
            for(int i = 0; i < 9; i++){
                this.rooms[i] = new Room();
            }
            
            // sets a pointer for the middle room
            this.middleRoom = this.rooms[4];
    
            // gives the floor a type so that it can be found easily later
            this.type = s;
    
            // constructs and connects the graph object so that each character and creature can move appropriately
            int a = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
    
                    this.rooms[a].coordinates.floor = s;
                    this.rooms[a].coordinates.y = i;
                    this.rooms[a].coordinates.x = j;
    
                    if(j != 2){
                        this.rooms[a].adjacentRoomsOnFloor.add(rooms[a+1]);
                        this.rooms[a].allAdjacentRooms.add(rooms[a+1]);
                    }
                    if(j != 0){
                        this.rooms[a].adjacentRoomsOnFloor.add(rooms[a-1]);
                        this.rooms[a].allAdjacentRooms.add(rooms[a-1]);
                    }
                    if(i != 2){
                        this.rooms[a].adjacentRoomsOnFloor.add(rooms[a+3]);
                        this.rooms[a].allAdjacentRooms.add(rooms[a+3]);
                    }
                    if(i != 0){
                        this.rooms[a].adjacentRoomsOnFloor.add(rooms[a-3]);
                        this.rooms[a].allAdjacentRooms.add(rooms[a-3]);
                    }
    
                    a++;
                }
            }
        }

        // prints the floor with all present creatures and their respective rooms.
    
        public void printFloor(){
            String s = 
                "+------------------------------+------------------------------+------------------------------+\n" +
                "| " + printAlliedOccupants(this.rooms[0]) + ":" + printEvilOccupants(this.rooms[0]) + 
                " | " + printAlliedOccupants(this.rooms[1]) + ":" + printEvilOccupants(this.rooms[1]) + 
                " | " + printAlliedOccupants(this.rooms[2]) + ":" + printEvilOccupants(this.rooms[2]) + " |\n" +
                "+------------------------------+------------------------------+------------------------------+\n" + 
                "| " + printAlliedOccupants(this.rooms[3]) + ":" + printEvilOccupants(this.rooms[3]) + 
                " | " + printAlliedOccupants(this.rooms[4]) + ":" + printEvilOccupants(this.rooms[4]) + 
                " | " + printAlliedOccupants(this.rooms[5]) + ":" + printEvilOccupants(this.rooms[5]) + " |\n" +
                "+------------------------------+------------------------------+------------------------------+\n" +
                "| " + printAlliedOccupants(this.rooms[6]) + ":" + printEvilOccupants(this.rooms[6]) + 
                " | " + printAlliedOccupants(this.rooms[7]) + ":" + printEvilOccupants(this.rooms[7]) + 
                " | " + printAlliedOccupants(this.rooms[8]) + ":" + printEvilOccupants(this.rooms[8]) + " |\n" +
                "+------------------------------+------------------------------+------------------------------+\n";
    
                System.out.println(this.type + " floor:");
                System.out.println(s);
        }
        
        // method that is used to construct the string signifiying present Adventurers for each room in the method above
        public String printAlliedOccupants(Room room){
    
            if(room.alliedOccupants.isEmpty()){
                return "-";
            }
    
            String s = "";
    
            for(int i = 0; i < room.alliedOccupants.size(); i++){
                if(room.alliedOccupants.get(i).type == "fire"){
                    s += "EK,";
                }
                if(room.alliedOccupants.get(i).type == "water"){
                    s += "MW,";
                }
                if(room.alliedOccupants.get(i).type == "earth"){
                    s += "TV,";
                }
                if(room.alliedOccupants.get(i).type == "air"){
                    s += "ZR,";
                }
            }
    
            return s;
        }

        // method which constructs a string to signify present creatures for each room in the 'printFloor' method.
        public String printEvilOccupants(Room room){
    
            if(room.evilOccupants.isEmpty()){
                return "-";
            }
    
            String s = "";
    
            for(int i = 0; i < room.evilOccupants.size(); i++){
                if(room.evilOccupants.get(i).type == "fire"){
                    s += "F,";
                }
                if(room.evilOccupants.get(i).type == "water"){
                    s += "A,";
                }
                if(room.evilOccupants.get(i).type == "earth"){
                    s += "T,";
                }
                if(room.evilOccupants.get(i).type == "air"){
                    s += "Z,";
                }
            }
    
            return s;
        }
    }
