package com.assc.blackjack;

import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> hand;
    private int value;

    public Hand() {
        hand = new ArrayList<Card>();
        value = 0;
    }

    public void addCard(Card newCard) {
        hand.add(newCard);
        value += newCard.getVal();
    }

    //removes the most recent given card in the hand (mainly for when splitting)
    public Card popCard() {
        return hand.remove(hand.size()-1);
    }

    public int handSize() {
        return hand.size();
    }

    public void clearHand() {
        hand.clear();
    }
}
