package replication;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import game.Dice;
import game.Game;

/**
 * This is an automated test to create the bug that the dice values are the same for each game
 * This test does not assert equals, but just observe results from console
 */

public class Bug3Test {
	static Game game;
	static Dice d1;
	static Dice d2;
	static Dice d3;
	
	@Before
	public void setUp() {
		System.out.println("Automated Test for Bug 3: The DiceValues are the same for each game");
	}
	
	@After
	public void after() {
		d1 = null;
		d2 = null;
		d3 = null;
		game = null;
		
	}
	

	@Test
	public void rollDice() {
		System.out.println("\nTest Dice roll values of three dices ");
		d1 = new Dice();
		d2 = new Dice();
		d3 = new Dice();
		System.out.println("Original dice values: " + d1.toString()+" , "+d2.toString()+" , "+d3.toString());
		System.out.println("Results of 10 turns rolling:");
		
		for (int i = 1; i < 11; i++) {
			game = new Game(d1, d2, d3);
			d1.roll();
			d2.roll();
			d3.roll();
			
			System.out.println("Turn " + i + ": " + d1.toString()+" , "+d2.toString()+" , "+d3.toString());
			
		}
		
	}
}
