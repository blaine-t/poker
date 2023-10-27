package poker.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import poker.Card;
import poker.Rank;
import poker.Strength;
import poker.Suit;
import poker.Value;

public class StrengthTest {
    private static Strength[] easyS;
    private static Strength[] easySClone;

    @Before
    public void startUp() throws Exception {
        easyS = new Strength[10];
        Card[] easyC = new Card[5];
        easyS[5] = new Strength(Rank.HIGH_CARD, easyC);
        easyS[4] = new Strength(Rank.PAIR, easyC);
        easyS[6] = new Strength(Rank.TWO_PAIR, easyC);
        easyS[1] = new Strength(Rank.THREE_OF_A_KIND, easyC);
        easyS[3] = new Strength(Rank.STRAIGHT, easyC);
        easyS[2] = new Strength(Rank.FLUSH, easyC);
        easyS[9] = new Strength(Rank.FULL_HOUSE, easyC);
        easyS[7] = new Strength(Rank.FOUR_OF_A_KIND, easyC);
        easyS[8] = new Strength(Rank.STRAIGHT_FLUSH, easyC);
        easyS[0] = new Strength(Rank.ROYAL_FLUSH, easyC);
        easySClone = easyS.clone();
        Arrays.sort(easyS);
    }

    @Test
    public void easyHighCard() {
        assertEquals(easySClone[5], easyS[0]);
    }

    @Test
    public void easyPair() {
        assertEquals(easySClone[4], easyS[1]);
    }

    @Test
    public void easyTwoPair() {
        assertEquals(easySClone[6], easyS[2]);
    }

    @Test
    public void easyThreeOfAKind() {
        assertEquals(easySClone[1], easyS[3]);
    }

    @Test
    public void easyStraight() {
        assertEquals(easySClone[3], easyS[4]);
    }

    @Test
    public void easyFlush() {
        assertEquals(easySClone[2], easyS[5]);
    }

    @Test
    public void easyFullHouse() {
        assertEquals(easySClone[9], easyS[6]);
    }

    @Test
    public void easyFourOfAKind() {
        assertEquals(easySClone[7], easyS[7]);
    }

    @Test
    public void easyStraightFlush() {
        assertEquals(easySClone[8], easyS[8]);
    }

    @Test
    public void easyRoyalFlush() {
        assertEquals(easySClone[0], easyS[9]);
    }

    @Test
    public void hardHighCard() {
        Strength hand1 = new Strength(Rank.HIGH_CARD,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.CLUB, Value.EIGHT) });
        Strength hand2 = new Strength(Rank.HIGH_CARD,
                new Card[] { new Card(Suit.SPADE, Value.ACE), new Card(Suit.CLUB, Value.TWO) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardPair() {
        Strength hand1 = new Strength(Rank.PAIR, new Card[] { new Card(Suit.CLUB, Value.ACE),
                new Card(Suit.DIAMOND, Value.ACE), new Card(Suit.HEART, Value.FOUR) });
        Strength hand2 = new Strength(Rank.PAIR, new Card[] { new Card(Suit.SPADE, Value.ACE),
                new Card(Suit.HEART, Value.ACE), new Card(Suit.HEART, Value.THREE) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardTwoPair() {
        Strength hand1 = new Strength(Rank.TWO_PAIR,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.SPADE, Value.THREE), new Card(Suit.DIAMOND, Value.THREE),
                        new Card(Suit.HEART, Value.FOUR) });
        Strength hand2 = new Strength(Rank.TWO_PAIR,
                new Card[] { new Card(Suit.SPADE, Value.ACE), new Card(Suit.HEART, Value.ACE),
                        new Card(Suit.HEART, Value.THREE), new Card(Suit.CLUB, Value.THREE),
                        new Card(Suit.CLUB, Value.TWO) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardThreeOfAKind() {
        Strength hand1 = new Strength(Rank.THREE_OF_A_KIND,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.THREE) });
        Strength hand2 = new Strength(Rank.THREE_OF_A_KIND,
                new Card[] { new Card(Suit.SPADE, Value.THREE), new Card(Suit.DIAMOND, Value.THREE),
                        new Card(Suit.HEART, Value.THREE), new Card(Suit.CLUB, Value.FIVE) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardStraight() {
        Strength hand1 = new Strength(Rank.STRAIGHT,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.KING),
                        new Card(Suit.SPADE, Value.QUEEN), new Card(Suit.DIAMOND, Value.JACK),
                        new Card(Suit.DIAMOND, Value.TEN) });
        Strength hand2 = new Strength(Rank.STRAIGHT,
                new Card[] { new Card(Suit.SPADE, Value.SIX), new Card(Suit.DIAMOND, Value.FIVE),
                        new Card(Suit.HEART, Value.FOUR), new Card(Suit.CLUB, Value.THREE),
                        new Card(Suit.CLUB, Value.TWO) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardFlush() {
        Strength hand1 = new Strength(Rank.FLUSH,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.CLUB, Value.QUEEN), new Card(Suit.CLUB, Value.JACK),
                        new Card(Suit.CLUB, Value.NINE) });
        Strength hand2 = new Strength(Rank.FLUSH,
                new Card[] { new Card(Suit.SPADE, Value.ACE), new Card(Suit.SPADE, Value.KING),
                        new Card(Suit.SPADE, Value.QUEEN), new Card(Suit.SPADE, Value.JACK),
                        new Card(Suit.SPADE, Value.EIGHT) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardFullHouse() {
        Strength hand1 = new Strength(Rank.FULL_HOUSE,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.HEART, Value.ACE), new Card(Suit.CLUB, Value.JACK),
                        new Card(Suit.SPADE, Value.JACK) });
        Strength hand2 = new Strength(Rank.FULL_HOUSE,
                new Card[] { new Card(Suit.SPADE, Value.KING), new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.DIAMOND, Value.KING), new Card(Suit.HEART, Value.JACK),
                        new Card(Suit.DIAMOND, Value.JACK) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardFourOfAKind() {
        Strength hand1 = new Strength(Rank.FOUR_OF_A_KIND,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.HEART, Value.ACE), new Card(Suit.SPADE, Value.ACE),
                        new Card(Suit.SPADE, Value.JACK) });
        Strength hand2 = new Strength(Rank.FOUR_OF_A_KIND,
                new Card[] { new Card(Suit.SPADE, Value.KING), new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.DIAMOND, Value.KING), new Card(Suit.HEART, Value.KING),
                        new Card(Suit.DIAMOND, Value.JACK) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void hardStraightFlush() {
        Strength hand1 = new Strength(Rank.STRAIGHT_FLUSH,
                new Card[] { new Card(Suit.CLUB, Value.KING), new Card(Suit.CLUB, Value.QUEEN),
                        new Card(Suit.CLUB, Value.JACK), new Card(Suit.CLUB, Value.TEN),
                        new Card(Suit.CLUB, Value.NINE) });
        Strength hand2 = new Strength(Rank.STRAIGHT_FLUSH,
                new Card[] { new Card(Suit.CLUB, Value.EIGHT), new Card(Suit.CLUB, Value.SEVEN),
                        new Card(Suit.CLUB, Value.SIX), new Card(Suit.CLUB, Value.FIVE),
                        new Card(Suit.CLUB, Value.FOUR) });
        int expected = 1;
        assertSame(expected, hand1.compareTo(hand2));
    }

    // hardRoyalFlush isn't possible to do because royal flush's always equal each other

    @Test
    public void equalHighCard() {
        Strength hand1 = new Strength(Rank.HIGH_CARD,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.CLUB, Value.EIGHT) });
        Strength hand2 = new Strength(Rank.HIGH_CARD,
                new Card[] { new Card(Suit.SPADE, Value.ACE), new Card(Suit.DIAMOND, Value.EIGHT) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void equalPair() {
        Strength hand1 = new Strength(Rank.PAIR,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.ACE) });
        Strength hand2 = new Strength(Rank.PAIR,
                new Card[] { new Card(Suit.SPADE, Value.ACE), new Card(Suit.HEART, Value.ACE) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void equalTwoPair() {
        Strength hand1 = new Strength(Rank.TWO_PAIR,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.ACE),
                        new Card(Suit.CLUB, Value.KING), new Card(Suit.DIAMOND, Value.KING) });
        Strength hand2 = new Strength(Rank.TWO_PAIR, new Card[] { new Card(Suit.SPADE, Value.ACE),
                new Card(Suit.HEART, Value.ACE), new Card(Suit.SPADE, Value.KING), new Card(Suit.HEART, Value.KING) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }

    // equalThreeOfAKind isn't possible due to their only being 4 cards of the same value

    @Test
    public void equalStraight() {
        Strength hand1 = new Strength(Rank.STRAIGHT,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.DIAMOND, Value.KING),
                        new Card(Suit.SPADE, Value.QUEEN), new Card(Suit.DIAMOND, Value.JACK),
                        new Card(Suit.DIAMOND, Value.TEN) });
        Strength hand2 = new Strength(Rank.STRAIGHT,
                new Card[] { new Card(Suit.DIAMOND, Value.ACE), new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.CLUB, Value.QUEEN), new Card(Suit.CLUB, Value.JACK),
                        new Card(Suit.SPADE, Value.TEN) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void equalFlush() {
        Strength hand1 = new Strength(Rank.FLUSH,
                new Card[] { new Card(Suit.CLUB, Value.ACE), new Card(Suit.CLUB, Value.KING),
                        new Card(Suit.CLUB, Value.QUEEN), new Card(Suit.CLUB, Value.JACK),
                        new Card(Suit.CLUB, Value.NINE) });
        Strength hand2 = new Strength(Rank.FLUSH,
                new Card[] { new Card(Suit.SPADE, Value.ACE), new Card(Suit.SPADE, Value.KING),
                        new Card(Suit.SPADE, Value.QUEEN), new Card(Suit.SPADE, Value.JACK),
                        new Card(Suit.SPADE, Value.NINE) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }

    // equalFullHouse isn't possible due to their only being 4 cards of the same value

    // equalFourOfAKind isn't possible due to their only being 4 cards of the same value

    @Test
    public void equalStraightFlush() {
        Strength hand1 = new Strength(Rank.STRAIGHT_FLUSH,
                new Card[] { new Card(Suit.CLUB, Value.KING), new Card(Suit.CLUB, Value.QUEEN),
                        new Card(Suit.CLUB, Value.JACK), new Card(Suit.CLUB, Value.TEN),
                        new Card(Suit.CLUB, Value.NINE) });
        Strength hand2 = new Strength(Rank.STRAIGHT_FLUSH,
                new Card[] { new Card(Suit.SPADE, Value.KING), new Card(Suit.SPADE, Value.QUEEN),
                        new Card(Suit.SPADE, Value.JACK), new Card(Suit.SPADE, Value.TEN),
                        new Card(Suit.SPADE, Value.NINE) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }

    @Test
    public void equalRoyalFlush() {
        Strength hand1 = new Strength(Rank.STRAIGHT_FLUSH,
                new Card[] { new Card(Suit.CLUB, Value.KING), new Card(Suit.CLUB, Value.QUEEN),
                        new Card(Suit.CLUB, Value.JACK), new Card(Suit.CLUB, Value.TEN),
                        new Card(Suit.CLUB, Value.NINE) });
        Strength hand2 = new Strength(Rank.STRAIGHT_FLUSH,
                new Card[] { new Card(Suit.SPADE, Value.KING), new Card(Suit.SPADE, Value.QUEEN),
                        new Card(Suit.SPADE, Value.JACK), new Card(Suit.SPADE, Value.TEN),
                        new Card(Suit.SPADE, Value.NINE) });
        int expected = 0;
        assertSame(expected, hand1.compareTo(hand2));
    }
}
