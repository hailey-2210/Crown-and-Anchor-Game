package replication;

import java.util.List;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Dice;
import game.DiceValue;
import game.Game;
import game.Player;

/**
 * This is an automated test to create the bug that the odds ratio for the game
 * is incorrect (should be approximately 0.42)
 */

public class Bug5Test {

	Dice d1 = new Dice();
	Dice d2 = new Dice();
	Dice d3 = new Dice();

	Game game = new Game(d1, d2, d3);
	static Player player;
	List<DiceValue> cdv = game.getDiceValues();

	final static int STARTING_BALANCE = 100000; // ensure the balance won't be zero after 1000 turns
	final static int BET = 5;
	final static int ZERO_LIMIT = 0;
	final static int TURNS = 1000; 
	final static float DESIRED_RATIO = (float) 42.0;

	@Before
	public void setUp() {
		player = new Player("October", STARTING_BALANCE);
		player.setLimit(ZERO_LIMIT);
		
		System.out.println("Automated Test for Bug 5: Odds ratio is incorrect");

		System.out.println("Start Game: Number of Turns = " + TURNS);
		System.out.println(String.format("%s starts with balance %d, limit %d",
				player.getName(), player.getBalance(), player.getLimit()));
		System.out.println();
	}

	@Test
	public void testIncorrectOddsRatio() {

		DiceValue pick = DiceValue.CROWN;

		int winnings = 0;
		int totalWins = 0;
		int totalLosses = 0;
		float ratio = 0.0f;

		// loop for a number of turns
		for (int i = 0; i < TURNS; i++) {

			int winCount = 0;
			int loseCount = 0;

			Dice d1 = new Dice();
			Dice d2 = new Dice();
			Dice d3 = new Dice();

			game = new Game(d1,d2,d3);

			// Play the round with pick
			winnings = game.playRound(player, pick, BET);

			// Get the dice rolled
			cdv = game.getDiceValues();

			System.out.println("\nTurn " + (i+1));
			System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
			System.out.printf("%s bet %d on %s\n", player.getName(), BET, pick);
			System.out.printf("Balance now %d | Limit: %d\n", player.getBalance(), player.getLimit());
			System.out.printf("Winnings for this bet: %d", winnings);

			if (winnings > 0) {
				System.out.printf("\n%s won %d, balance now %d\n",
						player.getName(), winnings, player.getBalance());
				winCount++;
			}
			else {
				System.out.printf("\n%s lost, balance now %d\n",
						player.getName(), player.getBalance());
				loseCount++;
			}
			
			totalWins += winCount;
			totalLosses += loseCount;


		}

		System.out.println("\nGAME OVER");
		
		System.out.println(String.format("Total Win count = %d, Total Loss Count = %d",
				totalWins, totalLosses));

		ratio = (float)(totalWins * 100) / (totalWins + totalLosses);

		System.out.println(String.format("Overall win rate = %.1f%%", ratio));
		System.out.println();

		// assert that odds ratio is not approximately 42% (within +/- 5%)

		assertTrue((ratio >= 0.95*DESIRED_RATIO) && (ratio <= 1.05*DESIRED_RATIO));

	}
}