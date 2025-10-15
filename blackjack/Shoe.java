import java.util.ArrayList;
import java.util.Collections;
// import java.util.Random;

public class Shoe {
    
    final int numberOfDecks;
    final ArrayList<Card> combinedDeck;

    public Shoe(int numDecks){
        Deck deck = new Deck();
        numberOfDecks = numDecks;
        combinedDeck = new ArrayList<>();
        for (int i = 0; i < numDecks; i++) {
            combinedDeck.addAll(deck.getDeck());
        }
    }

    public ArrayList<Card> getShoe() {
        return combinedDeck;
    }

    public int getDeckSize() {
        return numberOfDecks;
    }

    public void displayShoe(ArrayList<Card> shoe) {
        for (Card i: shoe) {
            if (i.getFace() != null) {
                System.out.println(i.getFace() + " of " + i.getSuit() +"s");
            } else {
                System.out.println(i.getVal() + " of " + i.getSuit() +"s");
            }  
        }
    }

    public void displayShoe() {
        for (Card i: combinedDeck) {
            if (i.getFace() != null) {
                System.out.println(i.getFace() + " of " + i.getSuit() +"s");
            } else {
                System.out.println(i.getVal() + " of " + i.getSuit() +"s");
            }  
        }
    }

    // Shuffles all cards in combinedDeck
    public void shuffle() {
        Collections.shuffle(combinedDeck);
    }

    public void sort() {
        Collections.sort(combinedDeck);
    }

    public Card draw() {
        return combinedDeck.remove(0);
    }

    public static void main(String[] args) {
        Shoe shoe = new Shoe(6);
        shoe.shuffle();
        System.out.println(shoe.getShoe().size());
        for (int i = 0; i < 10; i++) {
            shoe.draw().displayCard();
        }
        System.out.println(shoe.getShoe().size());
    }
}
