package com.edu.BSU.inbetween.common;

import com.BSU.inbetween.R;
import com.BSU.inbetween.activities.MainScreenActivity;

public enum IntegerValues {
	
	StartingMoney (R.integer.defaultStartingMoney), 
	StartingPotSize (R.integer.defaultStartingPotSize), 
	StartingAnte (R.integer.defaultStartingAnte), 
	StartingAmountOfAiPlayers (R.integer.defaultStartingAmountOfAIPlayers), 
	MaxAiPlayers (R.integer.maxAmountOfAiPlayers),
	DeckSize (R.integer.standardDeckSize),
	CardsPerSuit (R.integer.cardsPerSuit),
	CardsPerNumberValue (R.integer.cardsPerNumberValue),
	HighestRandomNumber (R.integer.highestRandomNumber),
	MinimumBetAmount (R.integer.smallestBetAmount);
	
	int value;
	
	private IntegerValues(int value){
		this.value = value;
	}
	
	public int getValue(){
        return MainScreenActivity.getContext().getResources().getInteger(value);
	}

}
