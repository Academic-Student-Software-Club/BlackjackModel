// package BlackjackModel;

import java.util.ArrayList;

public class Deck {
    
    private ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<Card>();

        String suits[] = {"spade", "heart", "club", "diamond"};
        for (int i = 0; i < 52; i += 1) {
            Card newCard = new Card(suits[i%4], i+1);
            deck.add(newCard);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

}
