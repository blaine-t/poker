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
        ArrayList<Card> returnCards = new ArrayList<Card>();
        returnCards.add(cards.get(0));
        for (int i = 0; i < cards.size() - 1; i++) {
            Card card1 = cards.get(i);
            Card card2 = cards.get(i + 1);
            int card1Value = card1.getValue().ordinal();
            int card2Value = card2.getValue().ordinal();
            // If current card is 1 less than card after
            if ((card1Value - 1) == (card2Value)) {
                returnCards.add(card2);
            } else if (card1Value == card2Value) {
                // Duplicate of card so don't break streak
                continue;
            } else {
                // Otherwise break streak
                returnCards.clear();
                returnCards.add(card2);
            }
            // If straight found
            if (returnCards.size() >= 5) {
                return returnCards.toArray(new Card[returnCards.size()]);
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

    // TODO: Change to protected after testing
    public ArrayList<Card> getCardsInPlay() {
        // Create arrayList of all cards available to the user
        ArrayList<Card> cardsInPlay = new ArrayList<Card>(table.getFaceUpCards());
        for (Card card : hand) {
            cardsInPlay.add(card);
        }
        return cardsInPlay;
    }

    public Strength checkStrength(ArrayList<Card> cardsInPlay) {

        // Sort by value to be able to check for straight and find high card
        cardsInPlay.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder()));
        System.out.println(cardsInPlay.toString());

        // If flush get the cards that made that flush
        ArrayList<Card> flushCards = checkFlush(cardsInPlay);
        Value flushHighValue = null;
        // Check for royal and straight flush
        if (flushCards != null) {
            Card[] straight = checkStraight(flushCards);
            if (straight != null) {
                // If first card in a straight is an ACE that means it is a royal flush
                if (straight[0].getValue() == Value.ACE) {
                    return new Strength(Rank.ROYAL_FLUSH, straight);
                }
                // Otherwise it is just a normal straight flush
                return new Strength(Rank.STRAIGHT_FLUSH, straight);
            }
            // Can't declare flush yet because there are better hands to check first
        }

        // Get sets for future use checking sets
        ArrayList<ArrayList<Card>> sets = getSets(cardsInPlay);

        // Check for four of a kind
        for (int i = 0; i < sets.size(); i++) {
            ArrayList<Card> set = sets.get(i);
            if (set.size() >= 4) {
                if (i == 0) {
                    set.add(sets.get(1).get(0));
                } else {
                    set.add(0, sets.get(0).get(0));
                }
                Card[] combo = set.toArray(new Card[set.size()]);
                return new Strength(Rank.FOUR_OF_A_KIND, combo);
            }
        }

        // Check for full house
        ArrayList<Card> setOf2 = null;
        ArrayList<Card> setOf3 = null;
        Card[] cards = new Card[5];
        for (int i = 0; i < sets.size(); i++) {
            ArrayList<Card> set = sets.get(i);
            int k = 0;
            // If set of 3 found and needed
            if (setOf3 == null && set.size() >= 3) {
                setOf3 = set;
                if (setOf2 != null) {
                    // If don't have a set of 2 put in last slot
                    k = 2;
                }
                for (int j = 0; j < 3; j++) {
                    cards[j + k] = set.get(j);
                }
            } else if (setOf2 == null && set.size() >= 2) {
                // If set of 2 found and needed
                setOf2 = sets.remove(i);
                i--;
                if (setOf3 != null) {
                    // If don't have a set of 3 put in last slot
                    k = 3;
                    // Remove set from list to be able to test for two pair later
                }
                for (int j = 0; j < 2; j++) {
                    cards[j + k] = set.get(j);
                }
            } else {
                continue;
            }
            if (setOf2 != null && setOf3 != null) {
                return new Strength(Rank.FULL_HOUSE, cards);
            }
        }

        // Check for flush
        if (flushCards != null) {
            while (flushCards.size() > 5) {
                flushCards.remove(0);
            }
            Card[] returnCards = flushCards.toArray(new Card[flushCards.size()]);
            return new Strength(Rank.FLUSH, returnCards);
        }

        // Check for straight
        Card[] straight = checkStraight(cardsInPlay);
        if (straight != null) {
            return new Strength(Rank.STRAIGHT, straight);
        }

        // Check for three of a kind
        if (setOf3 != null) {
            int i = 0;
            while (setOf3.size() < 5) {
                Card card = cardsInPlay.get(i);
                if (!setOf3.contains(card)) {
                    setOf3.add(i, cardsInPlay.get(i));
                }
                i++;
            }
            return new Strength(Rank.THREE_OF_A_KIND, setOf3.toArray(new Card[setOf3.size()]));
        }

        // Check for two pair and pair
        if (setOf2 != null) {
            // Initialize returnCards
            ArrayList<Card> returnCards = new ArrayList<Card>();
            for (int i = 0; i < setOf2.size(); i++) {
                returnCards.add(setOf2.get(i));
            }
            // Check other sets to see if there is another pair
            for (ArrayList<Card> set : sets) {
                // If there is check to see if right highValue is set then return
                if (set.size() >= 2) {
                    for (int i = 0; i < set.size(); i++) {
                        returnCards.add(set.get(i));
                    }
                    int i = 0;
                    while (returnCards.size() < 5) {
                        Card card = cardsInPlay.get(i);
                        if (!returnCards.contains(card)) {
                            returnCards.add(i, cardsInPlay.get(i));
                        }
                        i++;
                    }
                    return new Strength(Rank.TWO_PAIR, returnCards.toArray(new Card[returnCards.size()]));
                }
            }
            // If no second pair found return a standard pair
            int i = 0;
            while (returnCards.size() < 5) {
                Card card = cardsInPlay.get(i);
                if (!returnCards.contains(card)) {
                    returnCards.add(i, cardsInPlay.get(i));
                }
                i++;
            }
            return new Strength(Rank.PAIR, returnCards.toArray(new Card[returnCards.size()]));
        }
        // If no hands are found just return the highest value card
        Card[] returnCards = new Card[5];
        for (int i = 0; i < 5; i++) {
            returnCards[i] = cardsInPlay.get(i);
        }
        return new Strength(Rank.HIGH_CARD, returnCards);
    }

    public Card[] getHand() {
        return this.hand;
    }

    public void setHand(Card[] cards) {
        this.hand = cards;
    }
}
