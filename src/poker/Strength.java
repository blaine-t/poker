package poker;

public class Strength {
    private Rank rank;
    private Card[] hand;
    
    public Strength(Rank rank, Card[] hand) {
        this.rank = rank;
        this.hand = hand;
    }
    
    @Override
    // TODO: REMOVE AS IS ONLY FOR DEBUGGING
    public String toString() {
        return (rank + " with highest card being: " + hand[hand.length - 1] + '\n' + "Hand was: " + hand.toString());
    }
}
