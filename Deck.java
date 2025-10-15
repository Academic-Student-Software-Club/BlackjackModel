// package BlackjackModel;

import java.util.ArrayList;
import java.lang.Math;

public class Deck {
    
    private ArrayList<Card> deck = new ArrayList<Card>();

    public Deck() {
        String suits[] = {"spade", "heart", "club", "diamond"};
        for (int i = 0; i < 52; i += 1) {
            Card newCard = new Card(suits[i%4], i%4+1);
            deck.add(newCard);
        }
    }

    //testing function, should be initialized in "Shoe" class though
    public void drawCard() {
        return;
    }

    public static void main(String[] args) {
        Deck deck = new Deck();
    }
}
