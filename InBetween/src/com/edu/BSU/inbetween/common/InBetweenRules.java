package com.edu.BSU.inbetween.common;

import java.util.ArrayList;

public class InBetweenRules 
{
	public static boolean areEnoughActivePlayers(ArrayList<AI_Player> aiPlayerList)
	{
		boolean aisRemain=false;
		for(AI_Player ai: aiPlayerList){
			if(!ai.isKicked()){
				aisRemain=true;
				break;
			}
		}
		return aisRemain;	}
	
	public static boolean doesPlayerHaveEnoughMoney(Money playerMoney, int anteAmount)
	{
		if(playerMoney.getAmount() < (anteAmount + 1) )
		{
			return false;
		}
		return true;
	}
	
	public static boolean areCardsOfEqualValue(Card firstCard, Card secondCard)
	{
		if(firstCard.getValue() == secondCard.getValue())
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean isRangeOfCardsOne(Hand hand)
	{
		if(hand.getRange() == 1)
		{
			return true;
		}
		return false;
	}
}
