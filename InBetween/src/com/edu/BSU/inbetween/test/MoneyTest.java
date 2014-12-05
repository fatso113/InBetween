package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.Money;

public class MoneyTest 
{

	@Test
	public void test() 
	{
		testMoneyConstructor();
		testMoneyAmount();
		testMoneyAddition();
		testMoneySubtraction();
	}
	
	private void testMoneyConstructor()
	{
		Money testMoney = new Money(12);
		
		assertNotNull(testMoney);
	}
	
	private void testMoneyAmount()
	{
		Money testMoney = new Money(12);
		
		assertEquals(12, testMoney.getAmount());
	}
	
	private void testMoneyAddition()
	{
		Money testMoney = new Money(12);
		
		assertEquals(12, testMoney.getAmount());
		
		testMoney.add(2);
		assertEquals(14, testMoney.getAmount());
	}
	
	private void testMoneySubtraction()
	{
		Money testMoney = new Money(12);
		
		assertEquals(12, testMoney.getAmount());
		
		testMoney.subtract(4);
		assertEquals(8, testMoney.getAmount());
	}
}