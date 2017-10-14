package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Player;

public class PlayerTest {
	
	Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player("October", 100);
	}
	
	@Test
	public void TestInitialLimit() {
		assertEquals(0, player.getLimit());
	}
	
	@Test
	public void TestInitialBalance() {
		assertEquals(100, player.getBalance());
	}
	
	@Test(expected=RuntimeException.class)
	public void TestBetWithNegativeBet() {
		int bet = -1;
		player.takeBet(bet);
		fail("Should have thrown exception");
	}
	
	@Test(expected=RuntimeException.class)
	public void TestBetThatExceedsBalance( ) {
		int balance = player.getBalance();
		int bet = balance + 1;
		player.takeBet(bet);
		fail("Should have thrown exception");
	}
	
	@Test
	public void TestBet() {
		int balance = player.getBalance();
		int bet = balance - 1;
		player.takeBet(bet);
		assertEquals(balance - bet, player.getBalance());
	}
	
	@Test
	public void TestReceiveWinnings()
	{
		int balance = player.getBalance();
		int winnings = 10;
		player.receiveWinnings(winnings);
		assertEquals(balance + winnings, player.getBalance());
	}
	
}
