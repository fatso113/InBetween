package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.edu.BSU.inbetween.common.AI_Player;
import com.edu.BSU.inbetween.common.Card;
import com.edu.BSU.inbetween.common.Hand;
import com.edu.BSU.inbetween.common.InBetweenRules;
import com.edu.BSU.inbetween.common.Money;
import com.edu.BSU.inbetween.common.Suit;

public class InBetweenRulesTest
{

	@Test
	public void test()
	{
		testAreCardsEqualValues();
		testForEnoughMoney();
		testRangeOfCards();
		testForEnoughPlayers();
	}
	
	private void testAreCardsEqualValues()
	{
		assertTrue(InBetweenRules.areCardsOfEqualValue(new Card(Suit.DIAMONDS, 3),  new Card(Suit.CLUBS, 3)));
	}

	private void testForEnoughMoney()
	{
		assertTrue(InBetweenRules.doesPlayerHaveEnoughMoney(new Money(6), 5));
		assertFalse(InBetweenRules.doesPlayerHaveEnoughMoney(new Money(4), 5));
		assertFalse(InBetweenRules.doesPlayerHaveEnoughMoney(new Money(5), 5));
	}
	
	private void testRangeOfCards()
	{
		assertTrue(InBetweenRules.isRangeOfCardsOne(new Hand(new Card(Suit.DIAMONDS, 4), new Card(Suit.CLUBS, 5))));
		assertFalse(InBetweenRules.isRangeOfCardsOne(new Hand(new Card(Suit.DIAMONDS, 3), new Card(Suit.CLUBS, 5))));
		assertFalse(InBetweenRules.isRangeOfCardsOne(new Hand(new Card(Suit.DIAMONDS, 5), new Card(Suit.CLUBS, 5))));
	}
	
	private void testForEnoughPlayers()
	{
		ArrayList<AI_Player> testPlayerList = new ArrayList<AI_Player>();
		AI_Player testAI = new AI_Player(100, false);
		testPlayerList.add(testAI);
		
		assertTrue(InBetweenRules.areEnoughActivePlayers(testPlayerList));
		testAI.kickAI();
		assertFalse(InBetweenRules.areEnoughActivePlayers(testPlayerList));
	}
}
