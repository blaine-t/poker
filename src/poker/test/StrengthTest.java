package poker.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import poker.Card;
import poker.Rank;
import poker.Strength;

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
}
