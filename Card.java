public class Card {

    private String suit;
    private int value;

    public Card(String type, int val) {
        suit = type;
        value = val;
    }

    public String getSuit() {
        return suit;
    }

    public int getVal() {
        return value;
    }
}