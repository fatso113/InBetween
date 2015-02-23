package com.edu.BSU.inbetween.common;

public class Card{

	private Suit suit;
	private int value;

	public Card() {

	}

	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	public boolean isBetween(Hand comparingHand) {
		if (this.getValue() < comparingHand.getFirstCard().getValue()
				&& this.getValue() > comparingHand.getSecondCard().getValue()) {
			return true;
		} else if (this.getValue() > comparingHand.getFirstCard().getValue()
				&& this.getValue() < comparingHand.getSecondCard().getValue()) {
			return true;
		}
	    return false;
	}

	public boolean isSameValue(Card comparingCard) {
		return comparingCard.getValue() == value;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}
    
}
