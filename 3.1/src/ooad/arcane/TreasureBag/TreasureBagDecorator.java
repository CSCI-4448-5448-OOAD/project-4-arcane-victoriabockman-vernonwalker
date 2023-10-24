package ooad.arcane.TreasureBag;

public class TreasureBagDecorator extends TreasureBag {

    TreasureBag treasureBag;

    public TreasureBag getTreasureBag(TreasureBag t_bag){
        return treasureBag.getTreasureBag(t_bag);
    }
}
