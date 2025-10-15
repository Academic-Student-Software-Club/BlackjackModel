import java.util.ArrayList;
// import java.lang.Math;

public class Deck {
    
    public ArrayList<Card> deck;

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
