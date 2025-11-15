package com.assc.blackjack;

import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> hand;
    private double wager;
    private int value;
    private double winMultiplier;

    public Hand(double bet) {   //player hand (with wager)
        hand = new ArrayList<Card>();
        wager = bet;
    }

    public double getWinMultiplier() {
        return winMultiplier;
    }

    public Hand() { //dealer hand
        hand = new ArrayList<Card>();
    }

    public int getHandValue() {
        return value;
    }
    public void setHandValue(int val) {
        value = val;
    }

    public boolean checkForBust() {
        System.out.println(value);
        if (value > 21) {
            //check if the hand has an ace, subtracts 10 if it does
            if (containsAce()) {
                value -= 10;
                System.out.println("ace softened, now value: " + value);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean has17() {
        System.out.println("checking dealer for 17...");
        if (value > 16) {
            return true;
        }
        return false;
    }

    public boolean containsAce() {
        for (Card card : hand) {
            if (card.getVal() == 11) {  //changes only the first ace it sees
                card.softenAce();
                return true;
            }
        }
        return false;
    }

    public void setWin(double multiplier) {
        winMultiplier = multiplier;
    }
    public double getWin() {
        return winMultiplier;
    }

    public double getWager() {
        return wager;
    }

    //returns true if every card in the hand is the same value
    //used mainly for checking if able to split
    public boolean sameValue() {
        for (Card card : hand) {
            if (hand.get(0).getVal() != card.getVal()) {
                return false;
            }
        }
        return true;
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
        value = 0;
    }

    public void printHand() {
        System.out.print("[");
        hand.get(0).displayCard();
        if (hand.size() > 1) {
            for (int i=1; i < hand.size(); i++) {
                System.out.println();
                hand.get(i).displayCard();
            }
        }
        System.out.print("]\n");
    }

    //precondition: retard
    public void printDealer() {
        System.out.print("[");
        hand.get(0).displayCard();
        System.out.println("\n(unknown)");
        System.out.println("]");
    }
}
