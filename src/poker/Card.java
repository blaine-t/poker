package poker;

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }
    
    public Value getValue() {
        return value;
    }
    
    @Override
    public boolean equals(Object o) {
        Card otherCard = (Card) o;
        if (this.suit == otherCard.getSuit() && this.value == otherCard.getValue()) {
            return true;
        }
        return false;
    }
    
    @Override
    // TODO: REMOVE AS IS ONLY FOR DEBUGGING
    public String toString() {
        return (value + " of " + suit + "S");
    }
}
