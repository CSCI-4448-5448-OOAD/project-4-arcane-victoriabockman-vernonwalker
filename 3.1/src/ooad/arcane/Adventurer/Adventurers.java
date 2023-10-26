
// WE NO LONGER HAVE NEED FOR THIS FILE


// package ooad.arcane.Adventurer;

// import ooad.arcane.Rooms;
// import ooad.arcane.Treasure;
// import ooad.arcane.GameObservers.GameObserver;

// public class Adventurers {

//     public Adventurer fire;
//     public Adventurer  water;
//     public Adventurer earth;
//     public Adventurer air;
//     public Treasure treasure;
//     public Rooms rooms_ads;

//     public Adventurers(Rooms rooms, GameObserver[] observers){

//         treasure = new Treasure();
//         // treasure object to track total treasure and deal with treasure collection when an edventurer enters an empty room.
        
//         // declaring the set of adventurers and setting their starting room as the dungeons starting room.
//         fire = new EmberKnight(observers);
//         water = new MistWalker(observers);
//         earth = new TerraVoyager(observers);
//         air = new ZephyrRogue(observers);

//         fire.currentRoom = rooms.startingRoom;
//         water.currentRoom = rooms.startingRoom;
//         earth.currentRoom = rooms.startingRoom;
//         air.currentRoom = rooms.startingRoom;

//         rooms.startingRoom.alliedOccupants.add(fire);
//         rooms.startingRoom.alliedOccupants.add(water);
//         rooms.startingRoom.alliedOccupants.add(earth);
//         rooms.startingRoom.alliedOccupants.add(air);
        
//         this.rooms_ads = rooms;
//     }

//     // method which prints the health and treasure of each adventurer
//     // do we not need this anymore? 
//     public void printAdventurers(){

//         String s = "EmberKnight - " + fire.treasure.value + " Treasure(s) - " + fire.health + " Health Remaining\n" + //
//                 "MistWalker - " + water.treasure.value + " Treasure(s) - " + water.health + " Health Remaining\n" + //
//                 "TerraVoyager - " + earth.treasure.value + " Treasure(s) - " + earth.health + " Health Remaining\n" + //
//                 "ZephyrRogue - " + air.treasure.value + " Treasure(s) - " + air.health + " Health Remaining\n";

//         System.out.println(s);

//     }

//     // method which moves each adventurer if their alive and then updates the resonance/discord accordingly and then notifies the observers
//     public void move(){
//         if(fire.isAlive){
//             fire.Move();
//             if(fire.badRoomType == fire.currentRoom.coordinates.floor){
//                 fire.inDiscord = true;
//                 fire.inResonance = false;
//                 fire.notify("adventurerGainedDiscord");
//             }
//             if(fire.goodRoomType == fire.currentRoom.coordinates.floor){
//                 fire.inResonance = true;
//                 fire.inDiscord = false;
//                 fire.notify("adventurerGainedResonance");
//             }
//             else{
//                 fire.inResonance = false;
//                 fire.inDiscord = false;
//             }
//             battleOrGetTreasure(fire);
//         }
//         if(water.isAlive){
//             water.Move();
//             if(water.badRoomType == water.currentRoom.coordinates.floor){
//                 water.inDiscord = true;
//                 water.inResonance = false;
//                 water.notify("adventurerGainedDiscord");
//             }
//             if(water.goodRoomType == water.currentRoom.coordinates.floor){
//                 water.inResonance = true;
//                 water.inDiscord = false;
//                 water.notify("adventurerGainedResonance");
//             }
//             else{
//                 water.inResonance = false;
//                 water.inDiscord = false;
//             }
//             battleOrGetTreasure(water);
//         }
//         if(earth.isAlive){
//             earth.Move();
//             if(earth.badRoomType == earth.currentRoom.coordinates.floor){
//                 earth.inDiscord = true;
//                 earth.inResonance = false;
//                 earth.notify("adventurerGainedDiscord");
//             }
//             if(earth.goodRoomType == earth.currentRoom.coordinates.floor){
//                 earth.inResonance = true;
//                 earth.inDiscord = false;
//                 earth.notify("adventurerGainedResonance");
//             }
//             else{
//                 earth.inResonance = false;
//                 earth.inDiscord = false;
//             }
//             battleOrGetTreasure(earth);
//         }
//         if(air.isAlive){
//             air.Move();
//             if(air.badRoomType == air.currentRoom.coordinates.floor){
//                 air.inDiscord = true;
//                 air.inResonance = false;
//                 air.notify("adventurerGainedDiscord");
//             }
//             if(fire.goodRoomType == fire.currentRoom.coordinates.floor){
//                 air.inResonance = true;
//                 air.inDiscord = false;
//                 air.notify("adventurerGainedResonance");
//             }
//             else{
//                 air.inResonance = false;
//                 air.inDiscord = false;
//             }
//             battleOrGetTreasure(air);
//         }
//     }

//     // method which decides whether an adventurer gets treasure or battles creatures whenever they enter a room.
//     public void battleOrGetTreasure(Adventurer ad){
//         if(ad.currentRoom.evilOccupants.isEmpty()){
//             treasure.findTreasure(ad, ad.currentRoom, this.rooms_ads);
//         }
//         else{

//             for(int i = 0; i < ad.currentRoom.evilOccupants.size(); i++){
//                 ad.currentRoom.Battle(ad, ad.currentRoom.evilOccupants.get(i), ad.currentRoom);
//             }

//         }
//     }

//     // method to update the observers of every adventurer easily.
//     public void updateObservers(GameObserver[] observers){
//         fire.observers = observers;
//         water.observers = observers;
//         air.observers = observers;
//         earth.observers = observers;
//     }
// }
