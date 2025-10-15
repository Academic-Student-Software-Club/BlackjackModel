import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> singleHand;
    //list of lists, when having multiple hands with splitting
    private ArrayList<ArrayList<Card>> handList;

    public Hand() {
        singleHand = new ArrayList<Card>();
        handList.add(singleHand);
    }

    public void addCard(Card newCard) {
        singleHand.add(newCard);
    }

    //returns both hands
    public ArrayList(splitHand() {

    }
}
