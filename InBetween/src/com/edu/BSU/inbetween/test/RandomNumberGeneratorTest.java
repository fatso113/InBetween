package com.edu.BSU.inbetween.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.edu.BSU.inbetween.common.RandomNumberGenerator;

public class RandomNumberGeneratorTest
{

	@Test
	public void test()
	{
		testRangeOfInt();
		testForPositiveInt();
	}
	
	private void testRangeOfInt()
	{
		assertTrue(101 > RandomNumberGenerator.generateRandomInt(100));
		assertTrue(0 <= RandomNumberGenerator.generateRandomInt(100));
	}
	
	private void testForPositiveInt()
	{
		assertTrue(RandomNumberGenerator.generateRandomInt(1) >= 0);
		assertTrue(RandomNumberGenerator.generateRandomInt(100) >= 0);
	}

}
