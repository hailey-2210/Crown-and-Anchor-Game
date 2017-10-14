package replication;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import game.Dice;
import game.DiceValue;
import game.Game;

/**
 * This is an automated test to create the bug that 
 * SPADE is never picked
 * SPADE is never rolled
 */

public class Bug4Test {
	Game game;

	Dice d1, d2, d3;

	DiceValue pick;

	final static int START_BALANCE = 100;
	final static int BET = 5;

	int pickSpade = 0;
	int rollSpade = 0;
	
	@Before
	public void setUp() {
		System.out.println("Automated Test for Bug 4: SPADE is never picked or rolled");
	}

	@After
	public void after() {
		d1 = null;
		d2 = null;
		d3 = null;
		game = null;
	}

	@Test
	public void testPickSpade() {
		System.out.println("Test pick SPADE");

		for (int i = 1; i< 21; i++) {
			pick = DiceValue.getRandom();
			System.out.println("Turn " + i + ": " + pick.toString());
			if (pick == DiceValue.SPADE) {
				pickSpade++;
			}
		}
		assertNotEquals(0, pickSpade);
	}

	@Test
	public void testRollSpade() {
		d1 = new Dice();
		d2 = new Dice();
		d3 = new Dice();

		System.out.println("Test roll SPADE");

		for (int i = 1; i < 21; i++) {
			game = new Game(d1, d2, d3);
			d1.roll();
			d2.roll();
			d3.roll();
			if (d1.getValue() == DiceValue.SPADE || d2.getValue() == DiceValue.SPADE || d3.getValue() == DiceValue.SPADE) {
				rollSpade++;
			}
			System.out.println("Turn " + i + ": " + d1.toString()+" , "+d2.toString()+" , "+d3.toString());
		}
		assertNotEquals(0, rollSpade);
	}







}


