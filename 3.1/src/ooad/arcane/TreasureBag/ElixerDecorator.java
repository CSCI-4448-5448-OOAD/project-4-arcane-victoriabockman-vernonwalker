package ooad.arcane.TreasureBag;

import java.util.ArrayList;

// decorator for Elixer treasure
public class ElixerDecorator extends TreasureBagDecorator{
    
    public ElixerDecorator(){
        this.value = 500;
        this.contents = new ArrayList<String>();
        this.contents.add("Elixer");
        this.addedDodgeChance = 0.1F;
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){

        if(t_bag.contents.contains("Elixer")){
            return t_bag.getTreasureBag(t_bag);
        }

        t_bag = t_bag.getTreasureBag(t_bag);

        t_bag.contents.add("Elixer");
        t_bag.addedDodgeChance = this.addedDodgeChance;
        t_bag.value += this.value;
        return t_bag;
    }
}
