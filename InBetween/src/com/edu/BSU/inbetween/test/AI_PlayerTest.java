package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.AI_Player;
import com.edu.BSU.inbetween.common.Card;
import com.edu.BSU.inbetween.common.Hand;
import com.edu.BSU.inbetween.common.Suit;

public class AI_PlayerTest 
{
	@Test
	public void test() 
	{
		testAIConstructor();
		testAIHands();
		testAIMoney();
		testAIKicking();
		testAIToString();
	}
	
	private void testAIConstructor()
	{
		AI_Player testAI = new AI_Player(500, false); 
		AI_Player testAI2 = new AI_Player(250, true);	
		
		assertNotNull(testAI);
		assertNotNull(testAI2);
	}
	
	private void testAIHands()
	{
		AI_Player testAI = new AI_Player(500, false); 
		AI_Player testAI2 = new AI_Player(250, true);	
		Hand testHand = new Hand(new Card(Suit.DIAMONDS, 2),new Card(Suit.SPADES, 10));
		Hand testHand2 = new Hand(new Card(Suit.HEARTS, 13),new Card(Suit.SPADES, 1));
		
		testAI.setHand(testHand);
		testAI2.setHand(testHand2);
		
		assertEquals(testAI.getHand(), testHand);
		assertEquals(testAI2.getHand(), testHand2);
		assertNotSame(testAI.getHand(), testHand2);
		assertNotSame(testAI2.getHand(), testHand);
	}
	
	private void testAIMoney()
	{
		AI_Player testAI = new AI_Player(500, false); 
		AI_Player testAI2 = new AI_Player(250, true);	
		
		assertEquals(testAI.getMoney().getAmount(), 500);
		assertEquals(testAI2.getMoney().getAmount(), 250);
		
		testAI.addMoney(30);
		testAI2.addMoney(150);
		assertEquals(testAI.getMoney().getAmount(), 530);
		assertEquals(testAI2.getMoney().getAmount(), 400);
		
		testAI.removeMoney(40);
		testAI2.removeMoney(10);
		assertEquals(testAI.getMoney().getAmount(), 490);
		assertEquals(testAI2.getMoney().getAmount(), 390);
		
		testAI.payAnte(10);
		testAI2.payAnte(5);
		assertEquals(testAI.getMoney().getAmount(), 480);
		assertEquals(testAI2.getMoney().getAmount(), 385);
	}
	
	private void testAIKicking()
	{
		AI_Player testAI = new AI_Player(500, false); 
		AI_Player testAI2 = new AI_Player(250, true);	
		
		assertFalse(testAI.isKicked());
		assertFalse(testAI2.isKicked());
		
		testAI.kickAI();
		assertTrue(testAI.isKicked());
		assertFalse(testAI2.isKicked());
		
		testAI2.kickAI();
		assertTrue(testAI.isKicked());
		assertTrue(testAI2.isKicked());
	}
	
	private void testAIToString()
	{
		AI_Player testAI = new AI_Player(500, false); 
		AI_Player testAI2 = new AI_Player(250, true);
		
		assertNotNull(testAI.toString());	//toString test
		assertNotNull(testAI2.toString());
		
		assertEquals(testAI.toString(),"500,false");
		assertEquals(testAI2.toString(),"250,true");
	}
}
