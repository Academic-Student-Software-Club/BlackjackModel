package com.assc.blackjack;

import java.util.ArrayList;
import java.util.Collections;

// make sure you're cd'd into BlackjackModel file first...
// java -cp bin blackjack.Deck

public class Deck {
    
    final ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<>();

        String suits[] = {"spade", "heart", "club", "diamond"};
        String faces[] = {"jack", "queen", "king"};
        for (int i = 0; i < 52; i += 1) {
            int val;
            String face = null;
            if ((i%13+2) >= 12) {
                val = 10;
                face = faces[(i%13+2)-12];
            } else {
                val = i%13+2;
            }
            if (val == 11) {
                face = "ace";   //[WARNING] ACE CARD IS NOW CONSIDERED A FACE CARD, CHANGE IF NEEDED
            }
            Card newCard = new Card(suits[i%4], val, face);
            deck.add(newCard);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void printDeck() {
        for (Card i: deck) {
            if (i.getFace() != null) {
                System.out.println(i.getFace() + " of " + i.getSuit() +"s");
            } else {
                System.out.println(i.getVal() + " of " + i.getSuit() +"s");
            }  
        }
    }

    

    public static void main(String[] args) {
        Deck deck = new Deck();
        Collections.sort(deck.getDeck());
        deck.printDeck();
    }

}

