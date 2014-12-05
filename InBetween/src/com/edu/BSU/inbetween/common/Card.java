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

	// compares cards in hand to the one drawn to see if they are in between
	public boolean isBetween(Hand comparingHand) {
		if (this.getValue() < comparingHand.getFirstCard().getValue()
				&& this.getValue() > comparingHand.getSecondCard().getValue()) {
			return true;
		} else if (this.getValue() > comparingHand.getFirstCard().getValue()
				&& this.getValue() < comparingHand.getSecondCard().getValue()) {
			return true;
		} else
			return false;
	}

	public boolean isSameValue(Card comparingCard) {
		if (comparingCard.getValue() == value) {
			return true;
		}

		return false;
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
