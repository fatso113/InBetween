package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.Card;
import com.edu.BSU.inbetween.common.Hand;
import com.edu.BSU.inbetween.common.Player;
import com.edu.BSU.inbetween.common.Suit;

public class PlayerTest {

	@Test
	public void test() 
	{
		testPlayerConstructor();
		testRemovePlayerMoney();
		testAddPlayerMoney();
		testSettingPlayerHand();
		testPlayerPayingAnte();
	}
	
	private void testPlayerConstructor()
	{
		Player testPlayer = new Player(500);
		
		assertNotNull(testPlayer);
	}

	private void testRemovePlayerMoney()
	{
		Player testPlayer = new Player(500);
		
		testPlayer.removeMoney(50);
		assertEquals(testPlayer.getMoney().getAmount(), 450);
		
		testPlayer.removeMoney(0);
		assertEquals(testPlayer.getMoney().getAmount(), 450);
	}
	
	private void testAddPlayerMoney()
	{
		Player testPlayer = new Player(500);
		
		testPlayer.addMoney(50);
		assertEquals(testPlayer.getMoney().getAmount(), 550);
		
		testPlayer.addMoney(0);
		assertEquals(testPlayer.getMoney().getAmount(), 550);
	}
	
	private void testSettingPlayerHand()
	{
		Player testPlayer = new Player(500);
		Hand testHand = new Hand(new Card(Suit.DIAMONDS, 5), new Card(Suit.HEARTS, 11));
		Hand playerTestHand = new Hand(new Card(Suit.DIAMONDS, 5), new Card(Suit.HEARTS, 11));
		
		assertNotNull(testHand);
		assertNotNull(playerTestHand);
		
		testPlayer.setHand(playerTestHand);
		assertEquals(testPlayer.getHand().getFirstCard().getValue(), testHand.getFirstCard().getValue());
		assertEquals(testPlayer.getHand().getSecondCard().getValue(), testHand.getSecondCard().getValue());
		
		assertEquals(testPlayer.getHand().getFirstCard().getSuit(), testHand.getFirstCard().getSuit());
		assertEquals(testPlayer.getHand().getSecondCard().getSuit(), testHand.getSecondCard().getSuit());
	}
	
	private void testPlayerPayingAnte()
	{
		Player testPlayer = new Player(505);
		int testAnte = 5;
		
		testPlayer.payAnte(testAnte);
		assertEquals(testPlayer.getMoney().getAmount(), 500);
	}
}
