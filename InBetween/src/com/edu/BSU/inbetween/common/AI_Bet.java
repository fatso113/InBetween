package com.edu.BSU.inbetween.common;

import java.util.ArrayList;

import android.util.Log;

import com.BSU.inbetween.activities.SharedValues;

/**
 * Returns a bet amount based off of regular betting or advanced betting for an AI player.
 * Given that a each round has at least 2 AI players and a human player, the minimum betting
 * chance for an AdvancedBet would be 2%
 * Case 1: 2 AI's and regular player
 * 		No one bets when it reaches an AI player, 46 cards are left and assuming the worst
 * 		case scenario, they have a 1 in 46 chance to win - 2% integer value
 * Case 2: 5 AI's and regular player
 * 		No one bets, 40 cards left. Worst case = 1 in 40 - 2% integer value
 * Case 3: 5 AI's and regular player
 * 		Everyone bets, last player has 1 card to win. 1 in 35 - 2% integer value
 * @return amount to bet
 */

public interface AI_Bet {
	
	public final int MINIMUM_BET_AMOUNT = IntegerValues.MinimumBetAmount.getValue();
	public final int CARDS_PER_DECK = IntegerValues.DeckSize.getValue();
	public final int CARDS_PER_SUIT = IntegerValues.CardsPerSuit.getValue();
	public final int CARDS_PER_NUMBER_VALUE = IntegerValues.CardsPerNumberValue.getValue();
	public final int HIGHEST_RANDOM_NUMBER = IntegerValues.HighestRandomNumber.getValue();
	public final int ANTE = SharedValues.getCreatedInstance().getAnteAmount();
	public final double REGULAR_MAX_BET_PERCENTAGE = 0.75;
	
	int getBetAmount();

}

class AdvancedBet implements AI_Bet{

	private int cardSpread;
	private Hand hand;
	private int money;
	
	public AdvancedBet(int cardSpread, int money, Hand hand) {
		this.cardSpread = cardSpread;
		this.money = money;
		this.hand = hand;
	}
	
	public int getBetAmount() {
		int random = RandomNumberGenerator.generateRandomInt(HIGHEST_RANDOM_NUMBER);
		if(random < bettingChance()){
			return (int) ((this.money * 1.0) * (bettingChance() * 1.0 / 100.0));
		}
		return 0;
	}

	private int bettingChance() {
		ArrayList<Card> listOfCardsDealt = Dealer.getPlayedCards();
		double winningCards = ((this.cardSpread - 1.0) * CARDS_PER_NUMBER_VALUE);
		winningCards -= cardsBetweenCurrentUserHand(listOfCardsDealt);
		double remainingCards = CARDS_PER_DECK - listOfCardsDealt.size();
		double bettingPercentage = winningCards/remainingCards;
		return (int) ((bettingPercentage) * 100);
	}

	private double cardsBetweenCurrentUserHand(ArrayList<Card> listOfCardsDealt) {
		int losingCards = 0;
		for(Card card: listOfCardsDealt){
			if(card.isBetween(this.hand)){
				losingCards++;
			}
		}
		return losingCards;
	}
	
}

class RegularBet implements AI_Bet{

	/**
	 * Regular based betting works off of a Sigmoid curve. Any spread that gets passed in is on the
	 * interval of 2 <= X <= 12 where a spread of 6 is the point of decline in increasing percentage. A
	 * spread of 2 has a betting percentage of 21% and a spread of 11 has a betting percentage of 91%.
	 * A spread of 12 is automatically 100% in regular betting.
	 */
	
	private int cardSpread;
	private int money;
	
	public RegularBet(int cardSpread, int money) {
		this.cardSpread = cardSpread;
		this.money = money;
	}

	public int getBetAmount(){
		if(this.cardSpread == 12){
			return (this.money);
		}
		int random = RandomNumberGenerator.generateRandomInt(HIGHEST_RANDOM_NUMBER);
		if(random < bettingChance()){
			return (int)(this.money * (bettingChance() / 100.0));
		}
		return 0;
	}
	
	private int bettingChance() {
		double numerator = (this.cardSpread - 1.0);
		double denominator = Math.sqrt(20 + (numerator * numerator));
		return (int) ((numerator/denominator) * 100.0);
	}
	
}
