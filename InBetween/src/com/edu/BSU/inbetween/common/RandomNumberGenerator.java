package com.edu.BSU.inbetween.common;

import java.util.Random;

public class RandomNumberGenerator
{
	private static Random rng = new Random();
	
	public static int generateRandomInt(int index)
	{
		int randomNum=rng.nextInt(index);
		if(randomNum<0)
			randomNum=0;
		
		return randomNum;
	}
}
