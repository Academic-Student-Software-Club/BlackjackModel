package blackjack;

public class Card implements Comparable<Card>{

    final String suit;
    private int value;
    final String face;

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
    public void setVal(int val) {
        value = val;
    }

    public String getFace() {
        return face;
    }

    public void displayCard() {
        if (face != null) {
            System.out.print(face + " of " + suit + "s");
        } else {
            System.out.print(value + " of " + suit + "s");
        }  
    }

    @Override
    public int compareTo(Card obj) {
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
        return switch (face.toLowerCase()) {
            case "jack" -> 10.1;
            case "queen" -> 10.2;
            case "king" -> 10.3;
            default -> value;
        };
    }
}