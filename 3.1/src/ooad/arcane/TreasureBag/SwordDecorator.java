package ooad.arcane.TreasureBag;

import java.util.ArrayList;

// decorator for sword treasure
public class SwordDecorator extends TreasureBagDecorator {

    public SwordDecorator(){
        this.value = 1100;
        this.combatRollBonus = 1;
        this.contents = new ArrayList<String>();
        this.contents.add("Sword");
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){
        if(t_bag.contents.contains("Sword")){
            return t_bag.getTreasureBag(t_bag);
        }
        else{

            t_bag = t_bag.getTreasureBag(t_bag);

            t_bag.combatRollBonus += this.combatRollBonus;
            t_bag.value += this.value;

            t_bag.contents.add("Sword");

            // remove from room?
            // or check value of treasure bag after searching the room

            return t_bag;
        }

    }
}
