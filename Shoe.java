import java.util.ArrayList;
import java.util.Random;

public class Shoe {
    
    private int numberOfDecks;
    private ArrayList<Card> combinedDeck;

    public Shoe(int numDecks){
        Deck deck = new Deck();
        numberOfDecks = numDecks;
        for (int i = 0; i < numDecks; i++) {
            combinedDeck.addAll(deck.getDeck());
        }
    }

    // Shuffles all cards in combinedDeck
    public void Shuffle() {
        
    }


}
