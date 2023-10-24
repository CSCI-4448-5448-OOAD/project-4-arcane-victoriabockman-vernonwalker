package ooad.arcane.TreasureBag;

import java.util.ArrayList;


// Decorator for armor treasure
public class ArmorDecorator extends TreasureBagDecorator {

    public ArmorDecorator(){
        this.value = 800;
        this.creatureCombatRollPenalty = 1;
        this.contents = new ArrayList<String>();
        this.contents.add("Armor");
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){
        if(t_bag.contents.contains("Armor")){
            return t_bag.getTreasureBag(t_bag);
        }
        else{

            t_bag = t_bag.getTreasureBag(t_bag);

            t_bag.creatureCombatRollPenalty += this.creatureCombatRollPenalty;
            t_bag.value += this.value;

            t_bag.contents.add("Armor");

            return t_bag;
        }
    }
}