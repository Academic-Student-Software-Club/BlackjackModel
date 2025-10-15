import java.util.ArrayList;
import java.util.Random;

public class Shoe {
    
    private int numberOfDecks;
    private ArrayList<Card> combinedDeck;

    public Shoe(int numDecks){
        numberOfDecks = numDecks;
        for (int i = 0; i < numDecks; i++) {
            combinedDeck.addAll(Deck);
        }
    }

    // Shuffles all cards in combinedDeck
    public void Shuffle() {
        
    }


}
