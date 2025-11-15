package com.assc.blackjack;

import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> hand;
    private int value;
    private double winMultiplier;
    private double wager;

    //used to create both player and dealer hand
    public Hand() {
        hand = new ArrayList<Card>();
        value = 0;
    }

    public void setWager(double wager) {
        this.wager = wager;
    }
    public double getWager() {
        return wager;
    }

    public int getHandValue() {
        return value;
    }

    //FOR ARTIFICIAL HAND ONLY!!!
    public void reduceToTwo() { //mainly to reset artificial hands
        if (hand.size() > 2) {
            ArrayList<Card> reducedHand = new ArrayList<>();
            reducedHand.add(hand.get(0));
            reducedHand.add(hand.get(1));
    
            hand = reducedHand;

            //ONLY BECAUSE THIS IS ONLY FOR ARTIFICIAL HANDS THIS FUNCTION WILL HAVE TWO RESPONSIBILITIES

            hardenAces();
        }
    }

    public void hardenAces() {
        for (Card card : hand) {
            if (card.getVal() == 1) {
                card.setVal(11);
            }
        }
    }
    
    public void calculateHandValue() {  //mainly for artificial hands
        value = 0;
        for (Card card : hand) {
            value += card.getVal();
        }

        System.out.println("calculateHandValue() -> " + value);
    }

    //if the player has an ace and busts, we bring the value of their hand down by 10
    public boolean checkCalculateBust() {
        System.out.println(value);
        if (value > 21) {
            //check if the hand has an ace, subtracts 10 if it does
            if (containsAce()) {
                softenAce();
                value -= 10;
                System.out.println("ace softened, now value: " + value);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkForBust() {
        if (value > 21) {
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
                return true;
            }
        }
        return false;
    }

    public void softenAce() {
        for (Card card : hand) {
            if (card.getVal() == 11) {
                card.setVal(1);
                return;
            }
        }
    }

    public void setWin(double multiplier) {
        winMultiplier = multiplier;
    }
    public double getWinMultiplier() {
        return winMultiplier;
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
        System.out.print("\n(unknown)");
        System.out.println("]");
    }



    //FOR TESTING
    public void artificialHand(String[] cards) {
        for (String cardName : cards) {
            Card newCard = new Card(cardName);
            hand.add(newCard);
        }
    }
}
