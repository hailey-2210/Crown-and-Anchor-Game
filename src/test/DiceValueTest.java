package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.DiceValue;

/* The test includes an array to store the times each dice return a value 
 * 
 */

public class DiceValueTest {
	
	int results[] = {0, 0, 0, 0, 0, 0}; 
	double min;
	double max;

	@Before
	public void setUp() {
		DiceValue diceValue = DiceValue.getRandom();
		for (int i = 0; i < 300; i++) {
			results[diceValue.ordinal()]++;
			diceValue =DiceValue.getRandom();
		}
		min = 300/6 * 0.9;
		
	}
	
	@Test
	public void testToString() {
		DiceValue diceValue = DiceValue.SPADE;
		String sValue = diceValue.toString(diceValue);
		assertTrue(sValue.equals("Spade"));

		diceValue = DiceValue.HEART;
		sValue = diceValue.toString(diceValue);
		assertTrue(sValue.equals("Heart"));

		diceValue = DiceValue.CLUB;
		sValue = diceValue.toString(diceValue);
		assertTrue(sValue.equals("Club"));

		diceValue = DiceValue.DIAMOND;
		sValue = diceValue.toString(diceValue);
		assertTrue(sValue.equals("Diamond"));

		diceValue = DiceValue.CROWN;
		sValue = diceValue.toString(diceValue);
		assertTrue(sValue.equals("Crown"));

		diceValue = DiceValue.ANCHOR;
		sValue = diceValue.toString(diceValue);
		assertTrue(sValue.equals("Anchor"));
	}
	
	@Test
	public void TestGetCrownRandomly()
	{
		assertTrue(results[(DiceValue.CROWN).ordinal()] > 0);
		
	}

	@Test
	public void TestGetAnchorRandomly()
	{
		assertTrue(results[(DiceValue.ANCHOR).ordinal()] > 0);
		
	}

	@Test
	public void TestGetHeartRandomly()
	{
		assertTrue(results[(DiceValue.HEART).ordinal()] > 0);
		
	}

	@Test
	public void TestGetDiamondRandomly()
	{
		assertTrue(results[(DiceValue.DIAMOND).ordinal()] > 0);
		
	}

	@Test
	public void TestGetClubRandomly()
	{
		assertTrue(results[(DiceValue.CLUB).ordinal()] > 0);
		
	}

	@Test
	public void TestGetSpadeRandomly()
	{
		assertTrue(results[(DiceValue.SPADE).ordinal()] > 0);
		
	}
	
	@Test
	public void TestGetEachValueRandomly()
	{
		assertTrue(results[(DiceValue.CROWN).ordinal()] >= min); // should be min 45
		assertTrue(results[(DiceValue.HEART).ordinal()] >= min); // should be min 45
		assertTrue(results[(DiceValue.ANCHOR).ordinal()] >= min); // should be min 45
		assertTrue(results[(DiceValue.CLUB).ordinal()] >= min); // should be min 45
		assertTrue(results[(DiceValue.SPADE).ordinal()] >= min); // should be min 45
		assertTrue(results[(DiceValue.DIAMOND).ordinal()] >= min); // should be min 45
	}
	
}

	
	