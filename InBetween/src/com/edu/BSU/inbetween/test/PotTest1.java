package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.Money;
import com.edu.BSU.inbetween.common.Pot;

public class PotTest1 
{
	@Test
	public void test() 
	{
		testPotConstructor();
		testAddingToPot();
		testCollectingWinnings();
		testCollectWinningsOverflow();
		testReturningAnteAmount();
		testReturnAnteOverflow();
	}
	
	private void testPotConstructor()
	{
		Pot testPot = new Pot(new Money(0));
		
		assertNotNull(testPot);
		assertEquals(testPot.getPotSize(), 0);
	}

	private void testAddingToPot()
	{
		Pot testPot = new Pot(new Money(0));
		
		testPot.addToPot(50);
		assertEquals(testPot.getPotSize(), 50);
		testPot.addToPot(2001);
		assertEquals(testPot.getPotSize(), 2051);
		testPot.addToPot(0);
		assertEquals(testPot.getPotSize(), 2051);
	}
	
	private void testCollectingWinnings()
	{
		Pot testPot = new Pot(new Money(500));
		int winnings = 0;
		
		winnings += testPot.collectWinnings(50);
		assertEquals(winnings, 100);
		assertEquals(testPot.getPotSize(), 400);
	}
	
	private void testReturningAnteAmount()
	{
		Pot testPot = new Pot(new Money(100));
		int anteAmount = 5;
		int returnedAnte;
		
		returnedAnte = testPot.returnAnteAmount(anteAmount);
		assertEquals(returnedAnte, anteAmount);
		assertEquals(testPot.getPotSize(), 95);
	}
	
	private void testCollectWinningsOverflow()
	{
		Pot testPot = new Pot(new Money(500));
		int winnings;
		
		winnings = testPot.collectWinnings(300);
		assertEquals(testPot.getPotSize(), 0);
		assertEquals(winnings, 500);
	}
	
	private void testReturnAnteOverflow()
	{
		Pot testPot = new Pot(new Money(5));
		int anteAmount = 10;
		int returnedAnte;
		
		returnedAnte = testPot.returnAnteAmount(anteAmount);
		assertEquals(returnedAnte, 5);
		assertEquals(testPot.getPotSize(), 0);
	}
}
