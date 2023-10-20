package poker;

public class Strength {
    private Rank rank;
    private Value value;
    
    public Strength(Rank rank, Value value) {
        this.rank = rank;
        this.value = value;
    }
    
    @Override
    // TODO: REMOVE AS IS ONLY FOR DEBUGGING
    public String toString() {
        return (rank + " with highest card being: " + value);
    }
}
