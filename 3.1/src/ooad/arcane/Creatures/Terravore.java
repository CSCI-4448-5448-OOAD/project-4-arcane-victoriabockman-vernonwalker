package ooad.arcane.Creatures;

public class Terravore extends Creatures{

    public Terravore(int num){
        currentRoom = null;
        type = "earth";
        isAlive = true;
        number = num;
    }

    public void Move(){
    // CONDITIONS:
    // They don’t move and stay at the position they spawn
        notify("creatureMoved");
    }
}
