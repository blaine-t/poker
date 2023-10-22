package poker;

import java.util.ArrayList;

import players.Player;

public class Table {
    private ArrayList<Player> players;
    private Player smallBlind;
    private Player bigBlind;
    private int pot;
    private int currentCall;
    private Deck deck;
    private ArrayList<Card> faceUpCards;

    public Table() {
        this.deck = new Deck();
        this.faceUpCards = new ArrayList<Card>();
        this.players = new ArrayList<Player>();
        deck.shuffle();
    }

    public Table(Player player) {
        this();
        addPlayer(player);
        
    }
    
    protected void addPlayer(Player player) {
        players.add(player);
    }

    protected void deal() {
        for (Player player : players) {
            Card[] hand = { deck.takeTopCard(), deck.takeTopCard() };
            player.setHand(hand);
        }
    }

    protected void placeCards(int cards) {
        // Burn a card
        deck.takeTopCard();
        // Play the other cards
        for (int i = 0; i < cards; i++) {
            faceUpCards.add(deck.takeTopCard());
        }
    }
    
    public ArrayList<Card> getFaceUpCards() {
        return faceUpCards;
    }

    public int getPot() {
        return pot;
    }

    protected void setPot(int pot) {
        this.pot = pot;
    }
}
