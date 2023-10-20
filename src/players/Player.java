package players;

import java.util.ArrayList;

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

    public Rank checkRank() {
        // Create arrayList of all cards available to the user
        ArrayList<Card> cardsInPlay = new ArrayList<Card>(table.getFaceUpCards());
        for (Card card : hand) {
            cardsInPlay.add(card);
        }
        return Rank.HIGH_CARD;
    }

    public Card[] getHand() {
        return this.hand;
    }

    public void setHand(Card[] cards) {
        this.hand = cards;
    }
}
