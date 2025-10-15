package blackjack;
import java.awt.desktop.PrintFilesHandler;
import java.util.ArrayList;
import java.util.Random;

public class Shoe {
    
    private int numberOfDecks;
    private  ArrayList<Card> combinedDeck;

    public Shoe(int numDecks){
        // Deck deck = new Deck();
        // numberOfDecks = numDecks;
        // for (int i = 0; i < numDecks; i++) {
        //     combinedDeck.addAll(deck.getDeck());
        // }
    }

    public ArrayList<Card> getShoe() {
        return combinedDeck;
    }

    public void displayShoe(ArrayList<Card> shoe) {
        for (Card i: shoe) {
            System.out.println("Suit: " + i.getSuit() + "Val: " + i.getVal());
        }
    }


    // Shuffles all cards in combinedDeck
    public void shuffle() {
        
    }

    public static void main(String[] args) {
        // Shoe shoe = new Shoe(6);
        // shoe.displayShoe(shoe.getShoe());

        Deck deck = new Deck();
        deck.getDeck();
    }

}
