package poker;

public class Player {
    private static int STARTING_BALANCE = 10000;
    private int balance;
    private Card[] hand;

    public Player() {
        this.balance = STARTING_BALANCE;
    }

    public Card[] getHand() {
        return this.hand;
    }

    public void setHand(Card[] cards) {
        this.hand = cards;
    }
}
