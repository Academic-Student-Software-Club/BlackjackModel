import java.util.ArrayList;

public class Hand {
    
    //list of lists, when having multiple hands with splitting
    private ArrayList<ArrayList<Card>> playerCards;

    public Hand() {
        playerCards = new ArrayList<ArrayList<Card>>();
    }

    public void addCard(Card newCard) {
        playerCards.add(newCard);
    }
}
