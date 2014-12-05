package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.Card;
import com.edu.BSU.inbetween.common.Hand;
import com.edu.BSU.inbetween.common.Suit;

public class HandTest
{
	Hand testHand = new Hand(new Card(Suit.DIAMONDS, 4), new Card(Suit.HEARTS, 7));
	
	@Test
	public void test()
	{
		testHandConstructor();
		testHandFirstCardValueAndSuit();
		testHandSecondCardValueAndSuit();
		testAreCardsBetweenHand();
		testAreCardsInHandSameValue();
	}
	
	private void testHandConstructor()
	{
		assertNotNull(testHand);
	}
	
	private void testHandFirstCardValueAndSuit()
	{
		assertEquals(4, testHand.getFirstCard().getValue());
		assertEquals(Suit.DIAMONDS, testHand.getFirstCard().getSuit());
	}
	
	private void testHandSecondCardValueAndSuit()
	{
		assertEquals(7, testHand.getSecondCard().getValue());
		assertEquals(Suit.HEARTS, testHand.getSecondCard().getSuit());
	}
	
	private void testAreCardsBetweenHand()
	{
		Card testCard= new Card(Suit.CLUBS , 6);
		Card testCard2= new Card(Suit.DIAMONDS , 10);
		
		assertTrue(testCard.isBetween(testHand));
		assertFalse(testCard2.isBetween(testHand));
	}
	
	private void testAreCardsInHandSameValue()
	{
		// Card are different values, so we want to return false
		assertTrue(!testHand.areCardsSameValue());
	}
}
