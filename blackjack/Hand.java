import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<Card>();
    }

    public void addCard(Card newCard) {
        hand.add(newCard);
    }

    //removes the most recent given card in the hand
    public Card popCard() {
        return hand.remove(hand.size()-1);
    }

    public int handSize() {
        return hand.size();
    }

    public void clearHand() {
        hand.clear();
    }
}
