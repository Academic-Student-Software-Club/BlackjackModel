package com.assc.blackjack;

import java.util.ArrayList;

public class Player {

    //list of player's hands
    private ArrayList<Hand> handList;
    private String playerName;
    private double money;

    public Player(String name) {
        handList = new ArrayList<Hand>();

        playerName = name;
        money = 10000;
        System.out.println("Welcome " + name + ", you currently have $10,000.");
    }

    //checks if we even need to deal the dealer if dealerhand better than the player's best hand
    public Hand getBestHand() {
        Hand bestHand = new Hand();
        for (Hand hand : handList) {
            if (hand.getHandValue() > bestHand.getHandValue()) {
                bestHand = hand;
            }
        }
        return bestHand;
    }

    public ArrayList<Hand> getHandList() {
        return handList;
    }

    public void addHand() {
        Hand hand = new Hand();
        handList.add(hand);
    }

    public void addHand(Hand hand) {
        handList.add(hand);
    }
    
    public Hand popHand() {
        return handList.remove(handList.size()-1);
    }

    //precondition: must have only 2 cards in current hand
    //always splits the last hand in handList
    public void splitHand() {
        Hand hand = getHand(-1);

        System.out.println("hand.handSize() -> " + hand.handSize());
        if (hand.handSize() == 2) {
            Hand newHand = new Hand();
            newHand.addCard(hand.popCard());
            handList.add(newHand);  //creates a new hand and adds it to player's handList

            hand.calculateHandValue();
            newHand.calculateHandValue();
            newHand.setWager(hand.getWager());
        }
        else {
            System.out.println("[HAND DID NOT SPLIT: CURRENT HANDSIZE != 2]");
        }
    }

    public boolean ableToSplit() {
        Hand hand = handList.get(handList.size()-1);

        if (hand.handSize() == 2 && hand.sameValue()) {
            return true;
        }

        return false;
    }

    public void hit(Card card) {
        handList.get(handList.size()-1).addCard(card);
    }

    public Hand getHand(int index) {
        if (index == -1) {
            return handList.get(handList.size()-1);
        } else {
            return handList.get(index);
        }
    }

    public int numberOfHands() {
        return handList.size();
    }

    public double getMoney() {
        return money;
    }

    public void subtractMoney(double bet) {
        money -= bet; 
    }

    public void payPlayer(double payout) {
        money += payout;
    }
    
}
