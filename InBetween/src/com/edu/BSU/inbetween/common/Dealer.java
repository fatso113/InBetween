package com.edu.BSU.inbetween.common;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer
{
	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static ArrayList<Card> playedCards = new ArrayList<Card>();
	private static ArrayList<Card> tempDeck = CardXMLParser.generateFiftyTwoCardDeck();
	
	public static void generateDeck()
	{
		deck.clear();
		playedCards.clear();
		deck.addAll(tempDeck);
		Collections.shuffle(deck);
	}
	
	public static Hand dealHand()
	{
		Card card1 = getCard();
		Card card2 = getCard();
		
		return new Hand(card1, card2);
	}
	
	public static Card revealTopCard()
	{	
		Card topCard = deck.remove(0);
		playedCards.add(topCard);
		
		return topCard;
	}
	
	private static Card getCard(){
		Card card = deck.remove(0);
		playedCards.add(card);
		return card;
	}
	
	public static ArrayList<Card> getPlayedCards()
	{
		return playedCards;
	}
}
