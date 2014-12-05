package com.edu.BSU.inbetween.common;

public class Player 
{

	private Money money;
	private Hand hand;
	
	public Player(int startingMoney)
	{
		money = new Money(startingMoney);
		hand = new Hand(null,null);
	}
	
	public Hand getHand()
	{
		return hand;
	}
	
	public void setHand(Hand hand)
	{
		this.hand = hand;
	}
	
	public Money getMoney()
	{
		return money;
	}
	
	public void addMoney(int amount)
	{
		this.money.add(amount);
	}
	
	public void removeMoney(int amount)
	{
		this.money.subtract(amount);
	}
	
	public void payAnte(int anteAmount)
	{
		this.money.subtract(anteAmount);
	}
	
	public static int determineMaxBet(GameSession session) {//used in the bet slider
		int maxBet;
		if (session.getPlayer().getMoney().getAmount() > (session.getPot().getPotSize())) {
			maxBet = (session.getPot().getPotSize());
		}
		else {
			maxBet = session.getPlayer().getMoney().getAmount();
		}
		return maxBet;
	}
	
	
}
