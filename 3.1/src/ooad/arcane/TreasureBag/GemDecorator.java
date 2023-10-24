package ooad.arcane.TreasureBag;

import java.util.ArrayList;

// decorator for gem treasure
public class GemDecorator extends TreasureBagDecorator{

    public GemDecorator(){
        this.value = 1000;
        this.creatureCombatBonus = 1;
        this.contents = new ArrayList<String>();
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){

        t_bag = t_bag.getTreasureBag(t_bag);
        t_bag.value += this.value;
        t_bag.creatureCombatBonus += this.creatureCombatBonus;
        
        return t_bag;
    }
    
}
