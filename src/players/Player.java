package players;

import java.util.ArrayList;
import java.util.Comparator;

import poker.Card;
import poker.Rank;
import poker.Strength;
import poker.Table;
import poker.Value;

public class Player {
    private static int STARTING_BALANCE = 10000;
    private static int MAX_CARDS = 7;
    private static int HAND_SIZE = 5;
    private static int SET_4_SIZE = 4;
    private static int SET_3_SIZE = 3;
    private static int SET_2_SIZE = 2;

    private int balance;
    private Card[] hand;
    private Table table;
    private Strength strength;

    public Player(Table table) {
        this.balance = STARTING_BALANCE;
        this.table = table;
    }
    
    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    private Card[] checkStraight(ArrayList<Card> cards) {
        ArrayList<Card> returnCards = new ArrayList<Card>(HAND_SIZE);
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
            if (returnCards.size() >= HAND_SIZE) {
                return returnCards.toArray(new Card[HAND_SIZE]);
            }
        }
        return null;
    }

    private ArrayList<Card> checkFlush(ArrayList<Card> cards) {
        ArrayList<Card> clubs = new ArrayList<Card>(MAX_CARDS);
        ArrayList<Card> diamonds = new ArrayList<Card>(MAX_CARDS);
        ArrayList<Card> hearts = new ArrayList<Card>(MAX_CARDS);
        ArrayList<Card> spades = new ArrayList<Card>(MAX_CARDS);
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
        if (clubs.size() >= HAND_SIZE) {
            return clubs;
        } else if (diamonds.size() >= HAND_SIZE) {
            return diamonds;
        } else if (hearts.size() >= HAND_SIZE) {
            return hearts;
        } else if (spades.size() >= HAND_SIZE) {
            return spades;
        } else {
            return null;
        }
    }

    private ArrayList<ArrayList<Card>> getSets(ArrayList<Card> cards) {
        ArrayList<ArrayList<Card>> sets = new ArrayList<ArrayList<Card>>(MAX_CARDS);
        ArrayList<Card> cardsMut = new ArrayList<Card>(cards);
        while (cardsMut.size() > 0) {
            ArrayList<Card> set = new ArrayList<Card>(HAND_SIZE);
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
        if (cardsInPlay == null) {
            cardsInPlay = getCardsInPlay();
        }

        // Sort by value to be able to check for straight and find high card
        cardsInPlay.sort(Comparator.comparing(Card::getValue, Comparator.reverseOrder()));

        // If flush get the cards that made that flush
        ArrayList<Card> flushCards = checkFlush(cardsInPlay);
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
            if (set.size() >= SET_4_SIZE) {
                // If set is largest values than add next largest
                if (i == 0) {
                    set.add(sets.get(1).get(0));
                } else {
                    // If set is not largest numbers add largest value
                    set.add(sets.get(0).get(0));
                }
                return new Strength(Rank.FOUR_OF_A_KIND, set.toArray(new Card[HAND_SIZE]));
            }
        }

        // Check for full house
        ArrayList<Card> setOf2 = null;
        ArrayList<Card> setOf3 = null;
        Card[] fullHouseCards = new Card[HAND_SIZE];
        for (int i = 0; i < sets.size(); i++) {
            ArrayList<Card> set = sets.get(i);
            // If set of 3 found and needed
            if (setOf3 == null && set.size() >= 3) {
                setOf3 = set;
                for (int j = 0; j < SET_3_SIZE; j++) {
                    fullHouseCards[j] = set.get(j);
                }
            } else if (setOf2 == null && set.size() >= 2) {
                // If set of 2 found and needed
                setOf2 = sets.remove(i);
                i--;
                // When comparing full houses you evaluate the set of 3 first
                for (int j = 0; j < SET_2_SIZE; j++) {
                    fullHouseCards[j + SET_3_SIZE] = set.get(j);
                }
            } else {
                continue;
            }
            // If have both a set of 2 and 3 then declare full house
            if (setOf2 != null && setOf3 != null) {
                return new Strength(Rank.FULL_HOUSE, fullHouseCards);
            }
        }

        // Check for flush
        if (flushCards != null) {
            Card[] highFlushCards = new Card[HAND_SIZE];
            // Take the top 5 highest cards of the flush
            for (int i = 0; i < HAND_SIZE; i++) {
                highFlushCards[i] = flushCards.get(i);
            }
            return new Strength(Rank.FLUSH, highFlushCards);
        }

        // Check for straight
        Card[] straight = checkStraight(cardsInPlay);
        if (straight != null) {
            return new Strength(Rank.STRAIGHT, straight);
        }

        // Check for three of a kind
        // Use already done calculation from full house
        if (setOf3 != null) {
            int i = 0;
            while (setOf3.size() < HAND_SIZE) {
                // Get the largest cards that aren't in the set of 3
                Card card = cardsInPlay.get(i);
                if (!setOf3.contains(card)) {
                    setOf3.add(cardsInPlay.get(i));
                }
                i++;
            }
            return new Strength(Rank.THREE_OF_A_KIND, setOf3.toArray(new Card[HAND_SIZE]));
        }

        // Check for two pair and pair
        // Use already done calculation from full house
        if (setOf2 != null) {
            Rank pairRank = Rank.PAIR;
            // Check other sets to see if there is another pair
            for (ArrayList<Card> set : sets) {
                if (set.size() == SET_2_SIZE) {
                    setOf2.addAll(set);
                    pairRank = Rank.TWO_PAIR;
                }
            }
            // If no second pair found return a standard pair
            // Fill up rest of ArrayList
            int i = 0;
            while (setOf2.size() < HAND_SIZE) {
                Card card = cardsInPlay.get(i);
                if (!setOf2.contains(card)) {
                    // Get the largest cards that aren't in the set of 2
                    setOf2.add(cardsInPlay.get(i));
                }
                i++;
            }
            return new Strength(pairRank, setOf2.toArray(new Card[HAND_SIZE]));
        }

        // If no hands are found just return the highest value card
        Card[] returnCards = new Card[HAND_SIZE];
        for (int i = 0; i < HAND_SIZE; i++) {
            returnCards[i] = cardsInPlay.get(i);
        }
        return new Strength(Rank.HIGH_CARD, returnCards);
    }
    
    public Strength getStrength() {
        return strength;
    }
    
    public void setStrength(Strength strength) {
        this.strength = strength;
    }
}
