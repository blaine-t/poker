package poker;

import java.util.Arrays;

public class Strength implements Comparable {
    private Rank rank;
    private Card[] hand;

    public Strength(Rank rank, Card[] hand) {
        this.rank = rank;
        this.hand = hand;
    }

    public Rank getRank() {
        return rank;
    }

    public Card[] getHand() {
        return hand;
    }

    public int compareTo(Object o) {
        Strength s = (Strength) o;
        int thisRankOrdinal = this.getRank().ordinal();
        int otherRankOrdinal = s.getRank().ordinal();
        if (thisRankOrdinal > otherRankOrdinal) {
            return 1;
        } else if (otherRankOrdinal > thisRankOrdinal) {
            return -1;
        } else {
            Card[] thisHand = this.getHand();
            Card[] otherHand = s.getHand();
            for (int i = 0; i < thisHand.length; i++) {
                int thisCardValue = thisHand[i].getValue().ordinal();
                int otherCardValue = otherHand[i].getValue().ordinal();
                if (thisCardValue > otherCardValue) {
                    return 1;
                } else if (otherCardValue > thisCardValue) {
                    return -1;
                }
            }
            // If hand matches that means the hands have the same value
            return 0;
        }

    }

    @Override
    public boolean equals(Object o) {
        Strength s = (Strength) o;
        if (this.rank == s.getRank() && Arrays.equals(this.hand, s.getHand())) {
            return true;
        }
        return false;
    }

    @Override
    // TODO: REMOVE AS IS ONLY FOR DEBUGGING
    public String toString() {
        return (rank + " with highest card being: " + hand[0] + '\n' + "Hand was: " + Arrays.toString(hand) + "\n");
    }
}
