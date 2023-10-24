package ooad.arcane.TreasureBag;

import java.util.ArrayList;

// decorator for potion treasure
public class PotionDecorator extends TreasureBagDecorator{
    
    public PotionDecorator(){
        this.value = 500;
        this.contents = new ArrayList<String>();
        this.contents.add("Potion");
        this.healthBonus = 1;
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){
        
        if(t_bag.contents.contains("Potion")){
            return t_bag.getTreasureBag(t_bag);
        }

        t_bag = t_bag.getTreasureBag(t_bag);

        t_bag.contents.add("Potion");
        t_bag.healthBonus = this.healthBonus;
        t_bag.value += this.value;
        return t_bag;
    }
}

