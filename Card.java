import java.util.Comparator;

public class Card implements Comparable{

    private String suit;
    private int value;
    private String face;

    public Card(String type, int val, String cardFace) {
        suit = type;
        value = val;
        face = cardFace;
    }

    public String getSuit() {
        return suit;
    }

    public int getVal() {
        return value;
    }

    public String getFace() {
        return face;
    }

    @Override
    public int compareTo(Object obj) {
        Card other = (Card)obj;
        
        int suitCompare = suit.compareToIgnoreCase(other.suit);
        if (suitCompare != 0) return suitCompare;

        // Compares the face cards
        double thisRank = getSortValue();
        double otherRank = other.getSortValue();

        return Double.compare(thisRank, otherRank);
    }

    private double getSortValue() {
        if (face == null) return value; // regular numbers
        switch (face.toLowerCase()) {
            case "jack": return 10.1;
            case "queen": return 10.2;
            case "king": return 10.3;
            default: return value; // fallback
        }
    }

    
}