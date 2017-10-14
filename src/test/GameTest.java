package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Dice;
import game.DiceValue;
import game.Game;
import game.Player;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
	Game game;

	Player player;
	DiceValue pickCrown;
	DiceValue pickHeart;
	DiceValue pickSpade;
	DiceValue pickDiamond;
	DiceValue pickAnchor;
	DiceValue pickClub;

	Dice dCrown, dAnchor, dHeart, dSpade, dDiamond, dClub;

	List<Dice> dice;
	List<DiceValue> values;

	final static int START_BALANCE = 100;
	final static int BET = 5;


	@Before
	public void setUp() throws Exception {
		dCrown = mock(Dice.class);
		dAnchor = mock(Dice.class);
		dHeart = mock(Dice.class);
		dDiamond = mock(Dice.class);
		dClub = mock(Dice.class);
		dSpade = mock(Dice.class);

		pickCrown = DiceValue.CROWN;
		pickHeart = DiceValue.HEART;
		pickSpade = DiceValue.SPADE;
		pickDiamond = DiceValue.DIAMOND;
		pickAnchor = DiceValue.ANCHOR;
		pickClub = DiceValue.CLUB;

		when(dAnchor.roll()).thenReturn(DiceValue.ANCHOR);
		when(dHeart.roll()).thenReturn(DiceValue.HEART);
		when(dDiamond.roll()).thenReturn(DiceValue.DIAMOND);

		when(dAnchor.getValue()).thenReturn(DiceValue.ANCHOR);
		when(dHeart.getValue()).thenReturn(DiceValue.HEART);
		when(dDiamond.getValue()).thenReturn(DiceValue.DIAMOND);

		dice = new ArrayList<Dice>();
	}

	@Test
	public void testInit() {
		assertTrue(dCrown instanceof Dice);
		assertTrue(dHeart instanceof Dice);
		assertTrue(dAnchor instanceof Dice);
		assertTrue(dSpade instanceof Dice);
		assertTrue(dClub instanceof Dice);
		assertTrue(dDiamond instanceof Dice);
	}

	@Test(expected=RuntimeException.class)
	public void testPlayRoundWithNullPlayer()
	{
		Game game = new Game(dAnchor, dHeart, dDiamond);
		game.playRound(null, pickCrown, BET);
		fail("Should have thrown exception");
	}

	@Test(expected=RuntimeException.class)
	public void testPlayRoundWithNullPick()
	{
		Game game = new Game(dAnchor, dHeart, dDiamond);
		player = new Player("October", 20);
		game.playRound(player, null, BET);
		fail("Should have thrown exception");
	}

	@Test(expected=RuntimeException.class)
	public void testPlayRoundWithNegativeBet()
	{
		Game game = new Game(dAnchor, dHeart, dDiamond);
		player = new Player("October", 20);
		game.playRound(player, pickCrown, -5);
		fail("Should have thrown exception");
	}

	@Test
	public void testNumberOfMatchesWithNoMatch() {
		player = new Player("October", START_BALANCE);
		game = new Game(dAnchor, dAnchor, dDiamond);
		dice.add(dAnchor);
		dice.add(dAnchor);
		dice.add(dDiamond);
		int matches = 0;

		game.playRound(player, pickHeart, BET);
		for ( Dice d : dice) {
			d.roll();
			if (d.getValue().equals(pickHeart)) { 
				matches ++;
			}
		}
		assertEquals(0, matches);
	}
	
	@Test
	public void testNumberOfMatchesWithOneMatch() {
		player = new Player("October", START_BALANCE);
		game = new Game(dAnchor, dAnchor, dDiamond);
		dice.add(dAnchor);
		dice.add(dAnchor);
		dice.add(dDiamond);
		int matches = 0;
		game.playRound(player, pickDiamond, BET);
		for ( Dice d : dice) {
			d.roll();
			if (d.getValue().equals(pickDiamond)) { 
				matches ++;
			}
		}
		assertEquals(1, matches);
	}

	@Test
	public void testNumberOfMatchesWithTwoMatches() {
		player = new Player("October", START_BALANCE);
		game = new Game(dAnchor, dAnchor, dDiamond);
		dice.add(dAnchor);
		dice.add(dAnchor);
		dice.add(dDiamond);

		int matches = 0;
		game.playRound(player, pickAnchor, BET);
		for ( Dice d : dice) {
			d.roll();
			if (d.getValue().equals(pickAnchor)) { 
				matches ++;
			}
		}
		assertEquals(2, matches);
	}
	
	@Test
	public void testNumberOfMatchesWithThreeMatches() {
		player = new Player("October", START_BALANCE);
		game = new Game(dAnchor, dAnchor, dAnchor);
		dice.add(dAnchor);
		dice.add(dAnchor);
		dice.add(dAnchor);

		int matches = 0;
		game.playRound(player, pickAnchor, BET);
		for ( Dice d : dice) {
			d.roll();
			if (d.getValue().equals(pickAnchor)) { 
				matches ++;
			}
		}
		assertEquals(3, matches);
	}
	
	@Test
	public void testPlayRoundWithNoMatch() {
		player = new Player("October", START_BALANCE);
		game = new Game(dAnchor, dAnchor, dDiamond);

		game.playRound(player, pickHeart, BET);
		assertEquals(START_BALANCE - BET, player.getBalance());
	}

	@Test
	public void testPlayRoundWithOneMatch() {
		player = new Player("October", START_BALANCE);
		game = new Game(dHeart, dAnchor, dDiamond);

		game.playRound(player, pickHeart, BET);
		assertEquals(START_BALANCE + BET, player.getBalance());
	}

	@Test
	public void testPlayRoundWithTwoMatches() {
		player = new Player("October", START_BALANCE);
		game = new Game(dHeart, dHeart, dDiamond);

		game.playRound(player, pickHeart, BET);
		assertEquals(START_BALANCE + 2*BET, player.getBalance());
	}

	@Test
	public void testPlayRoundWithThreeMatches() {
		player = new Player("October", START_BALANCE);
		game = new Game(dHeart, dHeart, dHeart);

		game.playRound(player, pickHeart, BET);
		assertEquals(START_BALANCE + 3*BET, player.getBalance());
	}






}


