package players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import poker.Card;
import poker.Rank;
import poker.Table;

public class Player {
    private static int STARTING_BALANCE = 10000;
    private int balance;
    private Card[] hand;
    private Table table;

    public Player(Table table) {
        this.balance = STARTING_BALANCE;
        this.table = table;
    }
    
    private boolean checkStraight(ArrayList<Card> cards) {
        int streak = 1;
        for(int i = 0; i < cards.size() - 1; i++) {
            Card card1 = cards.get(i);
            Card card2 = cards.get(i + 1);
            int card1Value = card1.getValue().ordinal();
            int card2Value = card2.getValue().ordinal();
            // If current card is 1 less than card after
            if ((card1Value + 1) == card2Value) {
                streak++;
            } else if (card1Value == card2Value) {
                // Duplicate of card so don't break streak
                continue;
            } else {
                // Otherwise break streak
                streak = 1;
            }
            // If straight found
            if (streak >= 5) {
                return true;
            }
        }
        return false;
    }
    
    private boolean checkFlush(ArrayList<Card> cards) {
        int clubs = 0;
        int diamonds = 0;
        int hearts = 0;
        int spades = 0;
        for(Card card : cards) {
            switch(card.getSuit()) {
                case CLUB:
                    clubs++;
                    break;
                case DIAMOND:
                    diamonds++;
                    break;
                case HEART:
                    hearts++;
                    break;
                case SPADE:
                    spades++;
            }
        }
        if (clubs >= 5 || diamonds >= 5 || hearts >= 5 || spades >= 5) {
            return true;
        }
        return false;
    }

    public Rank checkRank() {
        // Create arrayList of all cards available to the user
        ArrayList<Card> cardsInPlay = new ArrayList<Card>(table.getFaceUpCards());
        for (Card card : hand) {
            cardsInPlay.add(card);
        }
        // Sort by value
        cardsInPlay.sort(Comparator.comparing(Card::getValue));
        System.out.println(cardsInPlay.toString());
        boolean straight = checkStraight(cardsInPlay);
        boolean flush = checkFlush(cardsInPlay);
        System.out.println(straight);
        System.out.println(flush);
        return Rank.HIGH_CARD;
    }

    public Card[] getHand() {
        return this.hand;
    }

    public void setHand(Card[] cards) {
        this.hand = cards;
    }
}
