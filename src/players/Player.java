package players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import poker.Card;
import poker.Rank;
import poker.Strength;
import poker.Table;
import poker.Value;

public class Player {
    private static int STARTING_BALANCE = 10000;
    private int balance;
    private Card[] hand;
    private Table table;

    public Player(Table table) {
        this.balance = STARTING_BALANCE;
        this.table = table;
    }

    private Card[] checkStraight(ArrayList<Card> cards) {
        int streak = 1;
        for (int i = 0; i < cards.size() - 1; i++) {
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
                Card[] returnCards = { cards.get(i - 3), cards.get(i - 2), cards.get(i - 1), cards.get(i),
                        cards.get(i + 1) };
                return returnCards;
            }
        }
        return null;
    }

    private ArrayList<Card> checkFlush(ArrayList<Card> cards) {
        ArrayList<Card> clubs = new ArrayList<Card>();
        ArrayList<Card> diamonds = new ArrayList<Card>();
        ArrayList<Card> hearts = new ArrayList<Card>();
        ArrayList<Card> spades = new ArrayList<Card>();
        for (Card card : cards) {
            switch (card.getSuit()) {
                case CLUB:
                    clubs.add(card);
                    break;
                case DIAMOND:
                    diamonds.add(card);
                    break;
                case HEART:
                    hearts.add(card);
                    break;
                case SPADE:
                    spades.add(card);
            }
        }
        if (clubs.size() >= 5) {
            return clubs;
        } else if (diamonds.size() >= 5) {
            return diamonds;
        } else if (hearts.size() >= 5) {
            return hearts;
        } else if (spades.size() >= 5) {
            return spades;
        } else {
            return null;
        }
    }

    private ArrayList<ArrayList<Card>> getSets(ArrayList<Card> cards) {
        ArrayList<ArrayList<Card>> sets = new ArrayList<ArrayList<Card>>();
        ArrayList<Card> cardsMut = new ArrayList<Card>(cards);
        while (cardsMut.size() > 0) {
            ArrayList<Card> set = new ArrayList<Card>();
            set.add(cardsMut.remove(0));
            Value currentValue = set.get(0).getValue();
            for (int i = 0; i < cardsMut.size(); i++) {
                if (cardsMut.get(i).getValue() == currentValue) {
                    set.add(cardsMut.remove(i));
                    i--;
                }
            }
            sets.add(set);
        }
        return sets;
    }

    public Strength checkStrength() {
        // Create arrayList of all cards available to the user
        ArrayList<Card> cardsInPlay = new ArrayList<Card>(table.getFaceUpCards());
        for (Card card : hand) {
            cardsInPlay.add(card);
        }
        // Sort by value
        cardsInPlay.sort(Comparator.comparing(Card::getValue));
        System.out.println(cardsInPlay.toString());
        ArrayList<Card> flushCards = checkFlush(cardsInPlay);
        Value flushHighValue = null;
        // Check for royal and straight flush
        if (flushCards != null) {
            flushHighValue = flushCards.get(flushCards.size() - 1).getValue();
            if (checkStraight(flushCards) != null) {
                if (flushHighValue == Value.ACE) {
                    return new Strength(Rank.ROYAL_FLUSH, flushHighValue);
                } else {
                    return new Strength(Rank.STRAIGHT_FLUSH, flushHighValue);
                }
            }
        }

        // Check for four of a kind
        ArrayList<ArrayList<Card>> sets = getSets(cardsInPlay);
        for (ArrayList<Card> set : sets) {
            if (set.size() >= 4) {
                return new Strength(Rank.FOUR_OF_A_KIND, set.get(0).getValue());
            }
        }

        // Check for full house
        Value highValue = Value.TWO;
        boolean setOf2 = false;
        boolean setOf3 = false;
        // TODO: Fix picking lowest value full house and thus pairs
        for (ArrayList<Card> set : sets) {
            Value setValue = set.get(0).getValue();
            // If set of 3 found and needed
            if (!setOf3 && set.size() >= 3) {
                setOf3 = true;
                if (highValue.ordinal() < setValue.ordinal()) {
                    highValue = setValue;
                }
            } else if (!setOf2 && set.size() >= 2) {
                // If set of 2 found and needed
                setOf2 = true;
                if (highValue.ordinal() < setValue.ordinal()) {
                    highValue = setValue;
                }
            } else {
                continue;
            }
            if (setOf2 && setOf3) {
                return new Strength(Rank.FULL_HOUSE, highValue);
            }
        }
        
        // Check for flush
        if (flushCards != null) {
            return new Strength(Rank.FLUSH, flushHighValue);
        }
        
        // Check for straight
        Card[] straight = checkStraight(cardsInPlay);
        if (straight != null) {
            return new Strength(Rank.STRAIGHT, straight[4].getValue());
        }
        
        // Check for three of a kind
        if(setOf3) {
            return new Strength(Rank.THREE_OF_A_KIND, highValue);
        }
        
        // Check for two pair
        if (setOf2) {
            return new Strength(Rank.TWO_PAIR, highValue);
        }
        return new Strength(Rank.HIGH_CARD, cardsInPlay.get(cardsInPlay.size() - 1).getValue());
    }

    public Card[] getHand() {
        return this.hand;
    }

    public void setHand(Card[] cards) {
        this.hand = cards;
    }
}
