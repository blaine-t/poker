package players.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import players.Player;
import poker.Card;
import poker.Rank;
import poker.Strength;
import poker.Suit;
import poker.Table;
import poker.Value;

public class PlayerTest {
    private static Table table;
    private static Player player;

    @Before
    public void startUp() throws Exception {
        table = new Table(player);
        player = new Player(table);
    }
    
    @Test
    public void testHighCard() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.KING));
        cards.add(new Card(Suit.CLUB, Value.TWO));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.ACE);
        hand[1] = new Card(Suit.SPADE, Value.KING);
        hand[2] = new Card(Suit.CLUB, Value.QUEEN);
        hand[3] = new Card(Suit.HEART, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.EIGHT);
        Strength expected = new Strength(Rank.HIGH_CARD, hand);
        
        // execute
        Strength result = player.checkStrength(cards);
        
        // test
        assertEquals(expected,result);
    }

    @Test
    public void testPair() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.KING));
        cards.add(new Card(Suit.CLUB, Value.TWO));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.ACE);
        hand[2] = new Card(Suit.SPADE, Value.KING);
        hand[3] = new Card(Suit.HEART, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.EIGHT);
        Strength expected = new Strength(Rank.PAIR, hand);
        
        // execute
        Strength result = player.checkStrength(cards);
        
        // test
        assertEquals(expected,result);
    }
    
    @Test
    public void testTwoPair() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.KING));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.ACE);
        hand[2] = new Card(Suit.SPADE, Value.KING);
        hand[3] = new Card(Suit.HEART, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.NINE);
        Strength expected = new Strength(Rank.TWO_PAIR, hand);
        
        // execute
        Strength result = player.checkStrength(cards);
        
        // test
        assertEquals(expected,result);
    }
    
    @Test
    public void testThreeOfAKind() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.KING));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.SPADE, Value.KING);
        hand[2] = new Card(Suit.DIAMOND, Value.NINE);
        hand[3] = new Card(Suit.HEART, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.NINE);
        Strength expected = new Strength(Rank.THREE_OF_A_KIND, hand);
        
        // execute
        Strength result = player.checkStrength(cards);
        
        // test
        assertEquals(expected,result);
    }
    
    @Test
    public void testStraight() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.SIX));
        cards.add(new Card(Suit.DIAMOND, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.TEN);
        hand[1] = new Card(Suit.HEART, Value.NINE);
        hand[2] = new Card(Suit.SPADE, Value.EIGHT);
        hand[3] = new Card(Suit.CLUB, Value.SEVEN);
        hand[4] = new Card(Suit.CLUB, Value.SIX);
        Strength expected = new Strength(Rank.STRAIGHT, hand);
        
        // execute
        Strength result = player.checkStrength(cards);
        
        // test
        assertEquals(expected,result);
    }

}
