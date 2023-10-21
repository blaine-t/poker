package poker;

import java.util.Arrays;

public class Strength {
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

    @Override
    public boolean equals(Object o) {
        Strength otherStrength = (Strength) o;
        if (this.rank == otherStrength.getRank() && Arrays.equals(this.hand, otherStrength.getHand())) {
            return true;
        }
        return false;
    }

    @Override
    // TODO: REMOVE AS IS ONLY FOR DEBUGGING
    public String toString() {
        return (rank + " with highest card being: " + hand[0] + '\n' + "Hand was: " + Arrays.toString(hand));
    }
}
