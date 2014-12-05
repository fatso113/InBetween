package com.edu.BSU.inbetween.common;

public class Pot 
{

	private Money potSize = new Money(0);

	public Pot(Money potSize)
	{
		this.potSize = potSize;
	}
	
	public int getPotSize()
	{
		return potSize.getAmount();
	}
	
	public void addToPot(int i)
	{
		potSize.add(i);
	}
	

	public int collectWinnings(int bet) 
	{
		int returnAmount = bet*2;
		if (betReturnExceedsPotSize(returnAmount)==true)
		{
			returnAmount=potSize.getAmount();
		}
		potSize.subtract(returnAmount);
		return returnAmount;
	}
	
	private boolean betReturnExceedsPotSize(int bet)
	{
		if (potSize.getAmount()<bet)
		{
			return true;
		}
		return false;
	}
	
	public int returnAnteAmount(int amount)
	{
		if(betReturnExceedsPotSize(amount)==true)
			amount=potSize.getAmount();
		potSize.subtract(amount);
		return amount;
	}
	
}
