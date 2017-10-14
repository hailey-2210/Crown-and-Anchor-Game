package replication;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import game.Dice;
import game.DiceValue;
import game.Game;
import game.Player;

/** This automated test will create the bug that the player's balance
 * does not increase when he wins
 * The test mocks three dices in each game, and set pickHeart for the player. 
 */

public class Bug1Test {

	Game game;

	Player player;
	DiceValue pickHeart;

	Dice dAnchor, dHeart, dDiamond;

	List<Dice> dice;
	List<DiceValue> cdv;

	final static int START_BALANCE = 100;
	final static int BET = 5;

	@Before
	public void setUp() throws Exception {
		dAnchor = mock(Dice.class);
		dHeart = mock(Dice.class);
		dDiamond = mock(Dice.class);

		pickHeart = DiceValue.HEART;

		when(dAnchor.roll()).thenReturn(DiceValue.ANCHOR);
		when(dHeart.roll()).thenReturn(DiceValue.HEART);
		when(dDiamond.roll()).thenReturn(DiceValue.DIAMOND);

		when(dAnchor.getValue()).thenReturn(DiceValue.ANCHOR);
		when(dHeart.getValue()).thenReturn(DiceValue.HEART);
		when(dDiamond.getValue()).thenReturn(DiceValue.DIAMOND);

		dice = new ArrayList<Dice>();
		player = new Player("October", START_BALANCE);

		System.out.println("Automated Test for Bug 1: The player is not paid out correctly");
		System.out.println("Start game");
		System.out.println(String.format("%s starts with balance %d, limit %d",
				player.getName(), player.getBalance(), player.getLimit()));
		System.out.println();

	}

	@Test
	public void testBalanceWithNoMatch() {
		game = new Game(dAnchor, dAnchor, dDiamond);
		int winnings = game.playRound(player, pickHeart, BET);
		cdv = game.getDiceValues();
		System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
		System.out.printf("%s bet %d on %s\n", player.getName(), BET, pickHeart);
		System.out.printf("balance now %d\n\n", player.getBalance());
		System.out.printf("Winnings: %d\n", winnings);
		System.out.println();
		assertEquals(START_BALANCE - BET, player.getBalance());
	}

	@Test
	public void testBalanceWithOneMatch() {
		game = new Game(dHeart, dAnchor, dDiamond);
		int winnings = game.playRound(player, pickHeart, BET);
		cdv = game.getDiceValues();
		System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
		System.out.printf("%s bet %d on %s\n", player.getName(), BET, pickHeart);
		System.out.printf("balance now %d\n\n", player.getBalance());
		System.out.printf("Winnings: %d\n", winnings);
		System.out.println();
		assertEquals(START_BALANCE + BET, player.getBalance());
	}

	@Test
	public void testBalanceWithTwoMatches() {
		game = new Game(dHeart, dHeart, dDiamond);
		int winnings = game.playRound(player, pickHeart, BET);
		cdv = game.getDiceValues();
		System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
		System.out.printf("%s bet %d on %s\n", player.getName(), BET, pickHeart);
		System.out.printf("balance now %d\n\n", player.getBalance());
		System.out.printf("Winnings: %d\n", winnings);
		System.out.println();
		assertEquals(START_BALANCE + 2*BET, player.getBalance());
	}

	@Test
	public void testBalanceWithThreeMatches() {
		game = new Game(dHeart, dHeart, dHeart);
		int winnings = game.playRound(player, pickHeart, BET);
		cdv = game.getDiceValues();
		System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
		System.out.printf("%s bet %d on %s\n", player.getName(), BET, pickHeart);
		System.out.printf("balance now %d\n\n", player.getBalance());
		System.out.printf("Winnings: %d\n", winnings);
		System.out.println();
		assertEquals(START_BALANCE + 3*BET, player.getBalance());
	}
}

