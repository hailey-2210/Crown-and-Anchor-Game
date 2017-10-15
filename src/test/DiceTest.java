package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.After;

import game.Dice;

public class DiceTest {
	Dice dice;
	String value;
	
	@After
	public void tearDown() {
		dice = null;
	}
	
	@Test
	public void testGetValue() {
		System.out.print("Test Get Value");
		dice = new Dice();
		value = dice.toString();
		assertEquals(value, dice.getValue().toString());
	}
	
	@Test
	public void testRoll() {
		System.out.println("\nTest Dice roll values ");
		dice = new Dice();
		value = dice.roll().toString();
		System.out.println("Expected: " + value + " | Actual result: " + dice.getValue().toString());
		assertEquals(value, dice.getValue().toString());	
	}
		
	
}
