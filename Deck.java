// package BlackjackModel;

import java.util.ArrayList;

public class Deck {
    
    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {
        String suits[] = {"spade", "heart", "club", "diamond"};
        for (int i = 0; i < 52; i += 1) {
            Card newCard = new Card(suits[i%4], i%4+1);
            deck.add(newCard);
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
    
}
