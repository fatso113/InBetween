package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.Card;
import com.edu.BSU.inbetween.common.Hand;
import com.edu.BSU.inbetween.common.Suit;

public class CardTest
{
	Card testCard = new Card(Suit.DIAMONDS, 5);
	
	@Test
	public void test()
	{
		testCardConstructor();
		testCardSuit();
		testCardValue();
		testCardSameValue();
		testCardIsBetween();
	}
	
	private void testCardConstructor()
	{
		assertNotNull(testCard);
	}
	
	private void testCardSuit()
	{
		assertEquals(Suit.DIAMONDS, testCard.getSuit());
	}
	
	private void testCardValue()
	{
		assertEquals(5, testCard.getValue());
	}
	
	private void testCardSameValue()
	{
		assertTrue(testCard.isSameValue(new Card(Suit.DIAMONDS, 5)));
	}
	
	private void testCardIsBetween()
	{
		Hand testHand = new Hand(new Card(Suit.DIAMONDS, 1), new Card(Suit.DIAMONDS, 6));
		assertTrue(testCard.isBetween(testHand));
	}
}