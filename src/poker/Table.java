package poker;

public class Table {
    private Player players[];
    private int pot;
    private Deck deck;
    private Card[] faceUpCards;

    public Table() {
        this.deck = new Deck();
        deck.shuffle();
    }

    public Card draw() {
        return deck.takeTopCard();
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }
}
