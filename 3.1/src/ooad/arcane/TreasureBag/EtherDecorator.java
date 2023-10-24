package ooad.arcane.TreasureBag;

import java.util.ArrayList;


// decorator for ether treasure
public class EtherDecorator extends TreasureBagDecorator{
    public EtherDecorator(){
        this.value = 900;
        this.contents = new ArrayList<String>();
        this.contents.add("Ether");
        this.combatRollBonus = 1;
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){
        
        if(t_bag.contents.contains("Ether")){
            return t_bag.getTreasureBag(t_bag);
        }

        t_bag = t_bag.getTreasureBag(t_bag);

        t_bag.contents.add("Ether");
        t_bag.treasureRollBonus = this.treasureRollBonus;
        t_bag.value += this.value;
        return t_bag;
    }
}
