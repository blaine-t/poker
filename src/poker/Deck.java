package poker;

public class Deck {
    // Create the deck size by multiplying amount of suits by amount of values
    private static int DECK_SIZE = Suit.values().length * Value.values().length;

    private Card[] cards;
    
    public Deck() {
        cards = new Card[DECK_SIZE];
        int index = 0;
        for(Suit suit : Suit.values()) {
            for(Value value : Value.values()) {
                cards[index] = new Card(suit, value);
                index++;
            }
        }
    }
    
    @Override
    public String toString() {
        String returnStr = "";
        for(Card card : cards) {
            returnStr += card.toString() + '\n';
        }
        return returnStr;
    }
}
