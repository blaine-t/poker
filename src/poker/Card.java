package poker;

public class Card {
    private Suit suit;
    private Value value;
    
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return(value + " of " + suit + "S");
    }
}
