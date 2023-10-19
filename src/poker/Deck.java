package poker;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards = new Stack<Card>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.push(new Card(suit, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card takeTopCard() {
        return cards.pop();
    }

    @Override
    // TODO: REMOVE AS IS ONLY FOR DEBUGGING
    public String toString() {
        String returnStr = "";
        for (Card card : cards) {
            returnStr += card.toString() + '\n';
        }
        return returnStr;
    }
}
