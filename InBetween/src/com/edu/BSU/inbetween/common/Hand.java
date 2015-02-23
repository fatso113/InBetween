package com.edu.BSU.inbetween.common;

//TODO future-proof this
// Make the hand just a collection (ArrayList<Card>) in order to easily support other games
// (the hand won't always be just 2 cards for something like poker)

public class Hand
{
	private Card firstCard = new Card();
	private Card secondCard = new Card();
	
	public Hand(Card firstCard, Card secondCard) {
		this.firstCard = firstCard;
		this.secondCard = secondCard;
	}
	
	public int getRange() {
		return Math.abs(firstCard.getValue() - secondCard.getValue());
	}
	
	public Card getFirstCard() {
		return firstCard;
	}
	
	public Card getSecondCard() {
		return secondCard;
	}
	
	public void setFirstCard(Card settingCard) {
		firstCard = settingCard;
	}
	
	public void setSecondCard(Card settingCard) {
		secondCard = settingCard;
	}
	
    public boolean areCardsSameValue(){
		return firstCard.isSameValue(secondCard);
	}
}
