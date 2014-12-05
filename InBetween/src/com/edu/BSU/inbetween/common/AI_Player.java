package com.edu.BSU.inbetween.common;

public class AI_Player extends Player {
	
	private boolean viewOtherHandsDealt;
	private boolean isKicked;
	
	public AI_Player(int startingMoney, boolean viewHands){
		super(startingMoney);
		this.viewOtherHandsDealt = viewHands;
		isKicked = false;
	}
	
	public AI_Player(String[] params){
		super(Integer.valueOf(params[0]));
		this.viewOtherHandsDealt = Boolean.valueOf(params[1]);
		isKicked = false;
	}
	
	public int getBetAmount(){
		if(checkSpread()){
			return betAmount();
		} else { 
			return 0;
		}
	}
	
	private boolean checkSpread() {
		if(getCardSpread() > 1){
			return true;
		}
		return false;
	}
	
	private int betAmount() {
		int cardSpread = getCardSpread();
		int aiMoney = super.getMoney().getAmount();
		if(this.viewOtherHandsDealt){
			return new AdvancedBet(cardSpread, aiMoney, super.getHand()).getBetAmount();
		} else {
			return new RegularBet(cardSpread, aiMoney).getBetAmount();
		}
	}

	private int getCardSpread() {
		return super.getHand().getRange();
	}
	
	public boolean isKicked(){
		return this.isKicked;
	}
	
	public void kickAI(){
		this.isKicked = true;
	}
	
	public String toString(){
		return super.getMoney().getAmount() + "," + this.viewOtherHandsDealt;
	}
}