import java.util.ArrayList;
import java.util.Collection;
// import java.lang.Math;
import java.util.Collections;
// import java.util.Comparator;

public class Deck {
    
    private ArrayList<Card> deck;

    public Deck() {

        deck = new ArrayList<Card>();

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

