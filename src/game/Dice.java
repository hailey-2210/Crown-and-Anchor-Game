package game;
public class Dice {
			
	private DiceValue value;
	
	public Dice() {
		value =  DiceValue.getRandom();
	}
	
	public DiceValue getValue() {
		return value;
	}

	public DiceValue roll() {
		// return DiceValue.getRandom();
		value = DiceValue.getRandom();
		return value;
	}		
	
	public String toString() {
		return value.toString();
	}
}
