import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

public class GameTests {
	@Test
	public void testPair() {
		
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Two", 2, "Diamonds"));
		cards.add(new Card("Three", 3, "Spades"));
		cards.add(new Card("One", 1, "Spades"));
		cards.add(new Card("Four", 4, "Spades"));
		PlayerScorer scorer = new PlayerScorer(cards);
		assertTrue(scorer.hasPair());
		assertFalse(scorer.hasThreeKind());
	}
	
	@Test
	public void testTriple() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Two", 2, "Diamonds"));
		cards.add(new Card("Three", 3, "Spades"));
		cards.add(new Card("One", 1, "Spades"));
		cards.add(new Card("One", 1, "Clubs"));
		PlayerScorer scorer = new PlayerScorer(cards);
		assertTrue(scorer.hasThreeKind());
		assertFalse(scorer.hasFourKind());
	}
	
	@Test
	public void testStraight() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Two", 2, "Diamonds"));
		cards.add(new Card("Three", 3, "Spades"));
		cards.add(new Card("Four", 4, "Spades"));
		cards.add(new Card("Five", 5, "Clubs"));
		PlayerScorer scorer = new PlayerScorer(cards);
		assertTrue(scorer.hasStraight());
	}
	
	//Compare straights to non straights
	@Test
	public void compareStraights() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Two", 2, "Diamonds"));
		cards.add(new Card("Three", 3, "Spades"));
		cards.add(new Card("Four", 4, "Spades"));
		cards.add(new Card("Five", 5, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("Two", 2, "Diamonds"));
		cards2.add(new Card("Three", 3, "Diamonds"));
		cards2.add(new Card("Four", 4, "Spades"));
		cards2.add(new Card("Five", 5, "Spades"));
		cards2.add(new Card("Six", 6, "Clubs"));
		
		LinkedList<Card> cards3 = new LinkedList<>();
		cards3.add(new Card("Two", 2, "Diamonds"));
		cards3.add(new Card("Two", 2, "Clubs"));
		cards3.add(new Card("Two", 2, "Spades"));
		cards3.add(new Card("Five", 5, "Spades"));
		cards3.add(new Card("Six", 6, "Clubs"));
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		PlayerScorer scorer3 = new PlayerScorer(cards3);
		assertFalse(scorer.beats(scorer2));
		assertTrue(scorer.beats(scorer3));
	}
	
	@Test
	public void compareTopEquals() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Three", 3, "Diamonds"));
		cards.add(new Card("Five", 5, "Spades"));
		cards.add(new Card("Seven", 7, "Spades"));
		cards.add(new Card("Nine", 9, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("Two", 2, "Diamonds"));
		cards2.add(new Card("Four", 4, "Diamonds"));
		cards2.add(new Card("Six",6 , "Spades"));
		cards2.add(new Card("Eight", 8, "Spades"));
		cards2.add(new Card("Nine", 9, "Clubs"));
		
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		assertFalse(scorer.beats(scorer2));
	}
	
	@Test
	public void compareTopPairEquals() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Five", 5, "Spades"));
		cards.add(new Card("Five", 5, "Spades"));
		cards.add(new Card("Nine", 9, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("Two", 2, "Diamonds"));
		cards2.add(new Card("Two", 2, "Diamonds"));
		cards2.add(new Card("Five",5 , "Spades"));
		cards2.add(new Card("Five", 5, "Spades"));
		cards2.add(new Card("Nine", 9, "Clubs"));
		
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		assertFalse(scorer.beats(scorer2));
	}
	
	@Test
	public void compareAllPairsEqual() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("Five", 5, "Spades"));
		cards.add(new Card("Five", 5, "Spades"));
		cards.add(new Card("Nine", 9, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("One", 1, "Diamonds"));
		cards2.add(new Card("One", 1, "Diamonds"));
		cards2.add(new Card("Five",5 , "Spades"));
		cards2.add(new Card("Five", 5, "Spades"));
		cards2.add(new Card("Ten", 10, "Clubs"));
		
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		assertFalse(scorer.beats(scorer2));
	}
	
	@Test
	public void compareTriplesEqual() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("One", 1, "Clubs"));
		cards.add(new Card("One", 1, "Spades"));
		cards.add(new Card("Five", 5, "Spades"));
		cards.add(new Card("Ten", 10, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("One", 1, "Diamonds"));
		cards2.add(new Card("One", 1, "Clubs"));
		cards2.add(new Card("One",1 , "Spades"));
		cards2.add(new Card("Five", 5, "Spades"));
		cards2.add(new Card("Nine", 9, "Clubs"));
		
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		assertTrue(scorer.beats(scorer2));
	}
	
	@Test
	public void compareTriplesThanPairs() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("One", 1, "Clubs"));
		cards.add(new Card("One", 1, "Spades"));
		cards.add(new Card("Two", 2, "Spades"));
		cards.add(new Card("Two", 2, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("One", 1, "Diamonds"));
		cards2.add(new Card("One", 1, "Clubs"));
		cards2.add(new Card("One",1 , "Spades"));
		cards2.add(new Card("Three", 3, "Spades"));
		cards2.add(new Card("Three", 3, "Clubs"));
		
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		assertFalse(scorer.beats(scorer2));
	}
	
	@Test
	public void compareQuadsEqual() {
		LinkedList<Card> cards = new LinkedList<>();
		cards.add(new Card("One", 1, "Diamonds"));
		cards.add(new Card("One", 1, "Clubs"));
		cards.add(new Card("One", 1, "Spades"));
		cards.add(new Card("One", 1, "Hearts"));
		cards.add(new Card("Ten", 10, "Clubs"));
		
		
		LinkedList<Card> cards2 = new LinkedList<>();
		cards2.add(new Card("One", 1, "Diamonds"));
		cards2.add(new Card("One", 1, "Clubs"));
		cards2.add(new Card("One",1 , "Spades"));
		cards2.add(new Card("One", 1, "Hearts"));
		cards2.add(new Card("Nine", 6, "Clubs"));
		
		
		PlayerScorer scorer = new PlayerScorer(cards);
		PlayerScorer scorer2 = new PlayerScorer(cards2);
		assertTrue(scorer.beats(scorer2));
	}
	
	
	
}
