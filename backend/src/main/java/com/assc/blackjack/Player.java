package com.assc.blackjack;

import java.util.ArrayList;

public class Player {

    //list of player's hands
    private ArrayList<Hand> handList;
    private String playerName;
    private double money;

    public Player(String name) {
        playerName = name;
        money = 10000;
        System.out.println("Welcome " + name + ", you current have $10,000.");
    }
    

    //precondition: must have only 2 cards in current hand
    public void splitHand(Hand hand) {
        if (hand.handSize() == 2) {
            Hand newHand = new Hand();
            newHand.addCard(hand.popCard());
            handList.add(newHand);  //creates a new hand and adds it to player's handList
        }
        else {
            System.out.println("[HAND DID NOT SPLIT: CURRENT HANDSIZE != 2]");
        }
    }

    public void hit(int handIndex, Card card) {
        handList.get(handIndex).addCard(card);
    }
    
}
