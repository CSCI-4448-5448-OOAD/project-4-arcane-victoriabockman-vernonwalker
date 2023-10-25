package ooad.arcane.Creatures;


public class CreaturesFactory {

    public Creatures createCreatures(String type, int num){
		if("Fireborn".equalsIgnoreCase(type)){ 
          return new Fireborn(num);  
        }
		else if("Aquarid".equalsIgnoreCase(type)){
            return new Aquarid(num);
        }
        else if("Terravore".equalsIgnoreCase(type)){
            return new Terravore(num);
        }
        else if("Zephyral".equalsIgnoreCase(type)){
            return new Zephyral(num);
        }
		
		return null;
	}
}

