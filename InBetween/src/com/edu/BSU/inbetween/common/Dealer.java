package com.edu.BSU.inbetween.common;

import java.util.ArrayList;
import java.util.Collections;

public class Dealer {
    
    private static ArrayList<Card> deck = new ArrayList<Card>();
    private static ArrayList<Card> playedCards = new ArrayList<Card>();
    
    public static void generateDeck() {
        deck.clear();
        playedCards.clear();
        deck.addAll(CardXMLParser.generateFiftyTwoCardDeck());
        Collections.shuffle(deck);
    }

    public static Hand dealHand() {
        return new Hand(getCard(), getCard());
    }

    public static Card getCard() {
        Card card = deck.remove(0);
        playedCards.add(card);
        return card;
    }

    public static ArrayList<Card> getPlayedCards() {
        return playedCards;
    }
    
}
