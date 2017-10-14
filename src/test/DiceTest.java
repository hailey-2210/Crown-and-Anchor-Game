package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.After;

import game.Dice;
import game.Game;

public class DiceTest {
	static Game game;
	static Dice d1;
	static Dice d2;
	static Dice d3;
	
	
	@After
	public void after() {
		d1 = null;
		d2 = null;
		d3 = null;
		game = null;
		
	}
	

	@Test
	public void testGetRandom() {
		System.out.println("Test Game class and DiceValue class.");
		for (int i = 0; i < 10; i++) {
			d1 = new Dice();
			d2 = new Dice();
			d3 = new Dice();
			game = new Game(d1, d2, d3);
			System.out.println(game.getDiceValues().toString());
		}

	}
	
	@Test
	public void testDiceValues() {
		System.out.println("\nTest Dice random values ");
		for (int i = 0; i < 10; i++) {
			d1 = new Dice();
			d2 = new Dice();
			d3 = new Dice();
			
			System.out.println(d1.toString()+" , "+d2.toString()+" , "+d3.toString());
		}	
	}
	
	@Test
	public void testOneRoll() {
		System.out.println("\nTest Dice roll values ");
		Dice diceTest = new Dice();
		String valueTest = diceTest.roll().toString();
		System.out.println("Expected: " + valueTest + " | Actual result: " + diceTest.getValue().toString());
		assertEquals(valueTest, diceTest.getValue().toString());	
	}
	
	@Test
	public void testThreeRoll() {
		System.out.println("\nTest Dice roll values of three dices ");
		d1 = new Dice();
		d2 = new Dice();
		d3 = new Dice();
		System.out.println("Original dice values: " + d1.toString()+" , "+d2.toString()+" , "+d3.toString());
		
		game = new Game(d1, d2, d3);
		String d1Value = d1.roll().toString();
		String d2Value = d2.roll().toString();
		String d3Value = d1.roll().toString();
			
		System.out.println("Roll values: " + d1Value+" , " + d2Value +" , " + d3Value);
		System.out.println("Display dice values: " + d1.toString()+" , "+d2.toString()+" , "+d3.toString());
			
		}
		
	
}
