package ooad.arcane.TreasureBag;

import java.util.ArrayList;

// decorator for portal treasure
public class PortalDecorator extends TreasureBagDecorator {

    public PortalDecorator(){
        this.value = 1200;
        this.addedTeleportChance = 1;
        this.contents = new ArrayList<String>();
        this.contents.add("Portal");
        
    }

    @Override
    public TreasureBag getTreasureBag(TreasureBag t_bag){
        if(t_bag.contents.contains("Portal")){
            return t_bag.getTreasureBag(t_bag);
        }
        else{

            t_bag = t_bag.getTreasureBag(t_bag);

            t_bag.addedTeleportChance += this.addedTeleportChance;
            t_bag.value += this.value;

            t_bag.contents.add("Portal");

            return t_bag;
        }
    }
}