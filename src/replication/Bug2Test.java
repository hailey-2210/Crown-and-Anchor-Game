package replication;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import game.Dice;
import game.DiceValue;
import game.Game;
import game.Player;

import java.util.List;

/**
 * This is an automated test to create the bug that a player can never reach the
 * betting limit (there will always be $5 left in the balance)
 */

public class Bug2Test {
	Game game;
	
	Dice dAnchor;
	List<DiceValue> cdv;
	
	static String name = "October";
	final static int BET = 5;
	final static int ZERO_LIMIT = 0;
	


	@Before
	public void setUp() throws Exception {
		dAnchor = mock(Dice.class);

		when(dAnchor.roll()).thenReturn(DiceValue.ANCHOR);
		when(dAnchor.getValue()).thenReturn(DiceValue.ANCHOR);
		
		game = new Game(dAnchor, dAnchor, dAnchor);
		
		System.out.println("Automated Test for Bug 2: Player cannot reach betting limit");
		System.out.println("Start Game");
		System.out.println();

	}


	@Test
	public void testPlayWithBalanceExceedsBet() {
		Player player = new Player(name, 6);
		cdv = game.getDiceValues();
		DiceValue pick = DiceValue.HEART;
		System.out.println("Test with balance exceeds bet");
		System.out.println(String.format("%s starts with balance %d, limit %d",
				player.getName(), player.getBalance(), player.getLimit()));
		System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
		System.out.printf("%s bet %d on %s\n", player.getName(), BET, pick);
		
		int winnings = game.playRound(player, pick, BET);
		cdv = game.getDiceValues();
		
		
		System.out.printf("Balance now %d | Limit: %d\n\n", player.getBalance(), player.getLimit());
		System.out.println("Winnings for this bet:" +  winnings);
		System.out.println();
		assertNotEquals(ZERO_LIMIT, player.getBalance());
		
	}


	@Test
	public void testPlayWithBalanceEqualsBet() {
		Player player = new Player(name, 5);
		cdv = game.getDiceValues();
		DiceValue pick = DiceValue.HEART;
		System.out.println("Test with balance equals bet");
		System.out.println(String.format("%s starts with balance %d, limit %d",
				player.getName(), player.getBalance(), player.getLimit()));
		System.out.printf("Rolled %s, %s, %s\n", cdv.get(0), cdv.get(1), cdv.get(2));
		System.out.printf("%s bet %d on %s\n", player.getName(), BET, pick);
		
		int winnings = game.playRound(player, pick, BET);
		cdv = game.getDiceValues();
		
		System.out.printf("Balance now %d | Limit: %d\n\n", player.getBalance(), player.getLimit());
		System.out.println("Winnings for this bet:" +  winnings);
		System.out.println();
		assertEquals(ZERO_LIMIT, player.getBalance());
	}
}
