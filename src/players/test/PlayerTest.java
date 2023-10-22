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
    public void testHighCard5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.QUEEN);
        hand[2] = new Card(Suit.HEART, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.EIGHT);
        hand[4] = new Card(Suit.CLUB, Value.FOUR);
        Strength expected = new Strength(Rank.HIGH_CARD, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testHighCard6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.KING));
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
        assertEquals(expected, result);
    }

    @Test
    public void testHighCard7() {
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
        assertEquals(expected, result);
    }

    @Test
    public void testPair5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
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
        hand[4] = new Card(Suit.CLUB, Value.TWO);
        Strength expected = new Strength(Rank.PAIR, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testPair6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
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
        hand[4] = new Card(Suit.CLUB, Value.FOUR);
        Strength expected = new Strength(Rank.PAIR, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testPair7() {
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
        assertEquals(expected, result);
    }

    @Test
    public void testTwoPair5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.ACE);
        hand[2] = new Card(Suit.HEART, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.FOUR);
        Strength expected = new Strength(Rank.TWO_PAIR, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testTwoPair6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.DIAMOND, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.ACE);
        hand[2] = new Card(Suit.CLUB, Value.QUEEN);
        hand[3] = new Card(Suit.HEART, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.NINE);
        Strength expected = new Strength(Rank.TWO_PAIR, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testTwoPair7() {
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
        assertEquals(expected, result);
    }

    @Test
    public void testThreeOfAKind5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.DIAMOND, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.QUEEN);
        hand[2] = new Card(Suit.DIAMOND, Value.NINE);
        hand[3] = new Card(Suit.HEART, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.NINE);
        Strength expected = new Strength(Rank.THREE_OF_A_KIND, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testThreeOfAKind6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.FOUR));
        cards.add(new Card(Suit.DIAMOND, Value.NINE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.TWO));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.DIAMOND, Value.NINE);
        hand[2] = new Card(Suit.HEART, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.NINE);
        hand[4] = new Card(Suit.CLUB, Value.FOUR);
        Strength expected = new Strength(Rank.THREE_OF_A_KIND, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testThreeOfAKind7() {
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
        assertEquals(expected, result);
    }
    
    @Test
    public void testStraight5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.SIX));
        cards.add(new Card(Suit.DIAMOND, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.EIGHT));
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
        assertEquals(expected, result);
    }
    
    @Test
    public void testStraight6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.FIVE));
        cards.add(new Card(Suit.CLUB, Value.SIX));
        cards.add(new Card(Suit.DIAMOND, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.EIGHT));
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
        assertEquals(expected, result);
    }

    @Test
    public void testStraight7() {
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
        assertEquals(expected, result);
    }
    
    @Test
    public void testFlush5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.SIX));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.CLUB, Value.TEN);
        hand[2] = new Card(Suit.CLUB, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.SEVEN);
        hand[4] = new Card(Suit.CLUB, Value.SIX);
        Strength expected = new Strength(Rank.FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testFlush6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.SIX));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.CLUB, Value.TEN);
        hand[2] = new Card(Suit.CLUB, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.SEVEN);
        hand[4] = new Card(Suit.CLUB, Value.SIX);
        Strength expected = new Strength(Rank.FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testFlush7() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.SIX));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.CLUB, Value.TEN);
        hand[2] = new Card(Suit.CLUB, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.SEVEN);
        hand[4] = new Card(Suit.CLUB, Value.SIX);
        Strength expected = new Strength(Rank.FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testFullHouse5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.DIAMOND, Value.QUEEN));
        cards.add(new Card(Suit.SPADE, Value.TEN));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.HEART, Value.QUEEN);
        hand[2] = new Card(Suit.DIAMOND, Value.QUEEN);
        hand[3] = new Card(Suit.CLUB, Value.TEN);
        hand[4] = new Card(Suit.SPADE, Value.TEN);
        Strength expected = new Strength(Rank.FULL_HOUSE, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testFullHouse6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.HEART, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.SPADE, Value.TEN));
        cards.add(new Card(Suit.DIAMOND, Value.QUEEN));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.HEART, Value.QUEEN);
        hand[2] = new Card(Suit.DIAMOND, Value.QUEEN);
        hand[3] = new Card(Suit.CLUB, Value.TEN);
        hand[4] = new Card(Suit.SPADE, Value.TEN);
        Strength expected = new Strength(Rank.FULL_HOUSE, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testFullHouse7() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.TEN));
        cards.add(new Card(Suit.DIAMOND, Value.QUEEN));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.HEART, Value.QUEEN);
        hand[2] = new Card(Suit.DIAMOND, Value.QUEEN);
        hand[3] = new Card(Suit.CLUB, Value.TEN);
        hand[4] = new Card(Suit.SPADE, Value.TEN);
        Strength expected = new Strength(Rank.FULL_HOUSE, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testFourOfAKind5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.QUEEN));
        cards.add(new Card(Suit.DIAMOND, Value.QUEEN));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.QUEEN);
        hand[1] = new Card(Suit.HEART, Value.QUEEN);
        hand[2] = new Card(Suit.SPADE, Value.QUEEN);
        hand[3] = new Card(Suit.DIAMOND, Value.QUEEN);
        hand[4] = new Card(Suit.HEART, Value.NINE);
        Strength expected = new Strength(Rank.FOUR_OF_A_KIND, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testFourOfAKind6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.QUEEN));
        cards.add(new Card(Suit.SPADE, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.DIAMOND, Value.QUEEN));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.QUEEN);
        hand[2] = new Card(Suit.HEART, Value.QUEEN);
        hand[3] = new Card(Suit.SPADE, Value.QUEEN);
        hand[4] = new Card(Suit.DIAMOND, Value.QUEEN);
        Strength expected = new Strength(Rank.FOUR_OF_A_KIND, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testFourOfAKind7() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.HEART, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.QUEEN));
        cards.add(new Card(Suit.DIAMOND, Value.QUEEN));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.QUEEN);
        hand[2] = new Card(Suit.HEART, Value.QUEEN);
        hand[3] = new Card(Suit.SPADE, Value.QUEEN);
        hand[4] = new Card(Suit.DIAMOND, Value.QUEEN);
        Strength expected = new Strength(Rank.FOUR_OF_A_KIND, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testStraightFlush5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.JACK));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.JACK);
        hand[1] = new Card(Suit.CLUB, Value.TEN);
        hand[2] = new Card(Suit.CLUB, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.EIGHT);
        hand[4] = new Card(Suit.CLUB, Value.SEVEN);
        Strength expected = new Strength(Rank.STRAIGHT_FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testStraightFlush6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.CLUB, Value.JACK));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.JACK);
        hand[1] = new Card(Suit.CLUB, Value.TEN);
        hand[2] = new Card(Suit.CLUB, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.EIGHT);
        hand[4] = new Card(Suit.CLUB, Value.SEVEN);
        Strength expected = new Strength(Rank.STRAIGHT_FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testStraightFlush7() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.JACK));
        cards.add(new Card(Suit.CLUB, Value.SEVEN));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.EIGHT));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.JACK);
        hand[1] = new Card(Suit.CLUB, Value.TEN);
        hand[2] = new Card(Suit.CLUB, Value.NINE);
        hand[3] = new Card(Suit.CLUB, Value.EIGHT);
        hand[4] = new Card(Suit.CLUB, Value.SEVEN);
        Strength expected = new Strength(Rank.STRAIGHT_FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

    @Test
    public void testRoyalFlush5() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.JACK));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.KING));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.KING);
        hand[2] = new Card(Suit.CLUB, Value.QUEEN);
        hand[3] = new Card(Suit.CLUB, Value.JACK);
        hand[4] = new Card(Suit.CLUB, Value.TEN);
        Strength expected = new Strength(Rank.ROYAL_FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testRoyalFlush6() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.ACE));
        cards.add(new Card(Suit.CLUB, Value.JACK));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.KING));
        cards.add(new Card(Suit.HEART, Value.NINE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.KING);
        hand[2] = new Card(Suit.CLUB, Value.QUEEN);
        hand[3] = new Card(Suit.CLUB, Value.JACK);
        hand[4] = new Card(Suit.CLUB, Value.TEN);
        Strength expected = new Strength(Rank.ROYAL_FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }
    
    @Test
    public void testRoyalFlush7() {
        // setup
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(Suit.CLUB, Value.JACK));
        cards.add(new Card(Suit.CLUB, Value.TEN));
        cards.add(new Card(Suit.CLUB, Value.QUEEN));
        cards.add(new Card(Suit.CLUB, Value.KING));
        cards.add(new Card(Suit.HEART, Value.NINE));
        cards.add(new Card(Suit.SPADE, Value.EIGHT));
        cards.add(new Card(Suit.CLUB, Value.ACE));
        Card[] hand = new Card[5];
        hand[0] = new Card(Suit.CLUB, Value.ACE);
        hand[1] = new Card(Suit.CLUB, Value.KING);
        hand[2] = new Card(Suit.CLUB, Value.QUEEN);
        hand[3] = new Card(Suit.CLUB, Value.JACK);
        hand[4] = new Card(Suit.CLUB, Value.TEN);
        Strength expected = new Strength(Rank.ROYAL_FLUSH, hand);

        // execute
        Strength result = player.checkStrength(cards);

        // test
        assertEquals(expected, result);
    }

}
