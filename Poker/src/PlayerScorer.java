import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class PlayerScorer {
	private LinkedList<Card> cards;
	// highest value in quad/triple/pair
	private int importantValue;
	//second highest value in two pair case
	private int importantValue2;
	public PlayerScorer(LinkedList<Card> listCards) {
		cards = listCards;
		if(listCards.size() != 5) {
			System.out.println("Wrong Number of Cards");
		}
	}
	//returns true if flush
	//returns false otherwise;
	public boolean hasFlush() {
		Set<String> suits = new TreeSet<>();
		for(Card card: cards) {
			suits.add(card.getName());
		}
		if(suits.size() == 1) {
			//System.out.println("Has Flush");
			return true;
		}
		//System.out.println("Does Not Have Flush");
		return false;
	}
	
	// returns true if straight. Marks largest value of straight
	// returns false otherwise;
	public boolean hasStraight() {
		boolean[] values = new boolean[15];
		for(Card card: cards) {
			values[card.getValue()] = true;
		}
		int consecutiveCount = 0;
		for(int i = 0; i < 15; i ++) {
			boolean value = values[i];
			if(value) {
				consecutiveCount++;
			} else {
				consecutiveCount = 0;
			}
			if(consecutiveCount == cards.size()) {
				return true;
			}
		}
		return false;
	}
	// returns integer value of largest card in deck not in no-include list
	public int getHighCard(LinkedList<Integer> noList) {
		Card highCard = null;
		for(Card card : cards) {
			if(highCard == null) {
				highCard = card;
			} else {
				if(highCard.getValue() < card.getValue() && !noList.contains(card.getValue())) {
					highCard = card;
				}
			}
		}
		return highCard.getValue();
	}
	// returns true if given card deck has four of a kind. Marks down largest value
	// returns false otherwise
	public boolean hasFourKind() {
		HashMap<Integer, Integer> countMap = new HashMap<>();
		for(Card card : cards) {
			int count = countMap.getOrDefault(card.getValue(), 0) + 1;
			if(count == 4) {
				this.setImportantValue(card.getValue());
				return true;
			}
			countMap.put(card.getValue(), count);
		}
		return false;
	}
	
	// returns true if given card deck has three of a kind. Marks down largest value.
	// returns false otherwise
	public boolean hasThreeKind() {
		HashMap<Integer, Integer> countMap = new HashMap<>();
		for(Card card : cards) {
			int count = countMap.getOrDefault(card.getValue(), 0) + 1;
			if(count == 3) {
				this.setImportantValue(card.getValue());
				return true;
			}
			countMap.put(card.getValue(), count);
		}
		return false;
	}
	// returns true if given card deck has pairs. Marks down largest/second largest value.
	// returns false otherwise
	public boolean hasPair() {
		HashMap<Integer, Integer> countMap = new HashMap<>();
		boolean hasPair = false;
		int val1 = 0;
		int val2 = 0;
		for(Card card : cards) {
			int count = countMap.getOrDefault(card.getValue(), 0) + 1;
			if(count == 2) {
				if(!hasPair) {
					val1 = card.getValue();
				} else {
					val2 = card.getValue();
				}
				importantValue = Math.max(importantValue, card.getValue());
				hasPair = true;
			}
			countMap.put(card.getValue(), count);
		}
		importantValue2 = Math.min(val1,  val2);
		return hasPair;
	}
	// returns true if current card beats player2's cards
	// returns false otherwise;
	public boolean beats(PlayerScorer player2) {
		LinkedList<Integer> l = new LinkedList<>();
		LinkedList<Integer> l2 = new LinkedList<>();
		int highCard = getHighCard(l);
		int player2HighCard = player2.getHighCard(l2);
		
		//Compare flushes
		if(hasFlush() && !player2.hasFlush()) {
			System.out.println("Player 1 Wins with a Flush");
			return true;
		} else if (hasFlush() && player2.hasFlush()) {
			if(highCard > player2HighCard ) { 
				System.out.println("Player 1 and Player 2 Have a flush but Player 1 has a higher card");
				return true;
			} else if (highCard < player2HighCard){
				System.out.println("Player 1 and Player 2 Have a flush but Player 2 has a higher card");
				return false;
			}
		}
		
		//Compare straights
		if(hasStraight() && !player2.hasStraight()) {
			System.out.println("Player 1 Wins with a Straight");
			return true;
		} else if (!hasStraight() && player2.hasStraight()) {
			return false;
		} else if (hasStraight() && player2.hasStraight()) {
			if(highCard > player2HighCard) { 
				System.out.println("Player 1 and Player 2 Have a straight but Player 1 has a higher card");
				System.out.println("Player 1's highest card was:  " + highCard);
				System.out.println("Player 2's highest card was:  " + player2HighCard);
				return true;
			} else {
				System.out.println("Player 1 and Player 2 Have a straight but Player 2 has a higher card");
				System.out.println("Player 1's highest card was:  " + highCard);
				System.out.println("Player 2's highest card was:  " + player2HighCard);
				return false;
			}
		}
		
		//Compare four of a kind
		if(hasFourKind() && !player2.hasFourKind()) {
			return true;
		} else if (!hasFourKind() && player2.hasFourKind()) {
			return false;
		} else if (hasFourKind() && player2.hasFourKind()) {
			if(getImportantValue() > player2.getImportantValue()) { 
				System.out.println("Player 1 and Player 2 Have Four of a Kinds but Player 1 has a higher card");
				System.out.println("Player 1's highest card was:  " + getImportantValue());
				System.out.println("Player 2's highest card was:  " + player2.getImportantValue());
				return true;
			} else if (getImportantValue() < player2.getImportantValue()){
				System.out.println("Player 1 and Player 2 Have Four of a Kinds but Player 2 has a higher card");
				System.out.println("Player 1's highest card was:  " + getImportantValue());
				System.out.println("Player 2's highest card was:  " + player2.getImportantValue());
				return false;
			}
		}
		
		//Compare three of a kind
		if(hasThreeKind() && !player2.hasThreeKind()) {
			return true;
		} else if (!hasThreeKind() && player2.hasThreeKind()) {
			return false;
		} else if (hasThreeKind() && player2.hasThreeKind()) {
			if( getImportantValue() > player2.getImportantValue()) { 
				System.out.println("Player 1 and Player 2 Have Triples but Player 1 has a higher card");
				System.out.println("Player 1's highest card was:  " + getImportantValue());
				System.out.println("Player 2's highest card was:  " + player2.getImportantValue());
				return true;
			} else if (getImportantValue() < player2.getImportantValue()){
				System.out.println("Player 1 and Player 2 Have Triples but Player 2 has a higher card");
				System.out.println("Player 1's highest card was:  " +getImportantValue());
				System.out.println("Player 2's highest card was:  " + player2.getImportantValue());
				return false;
			}
		}
		// Compare pairs
		if(hasPair() && !player2.hasPair()) {
			return true;
		} else if (!hasPair() && player2.hasPair()) {
			return false;
		} else if (hasPair() && player2.hasPair()) {
			if(getImportantValue() > player2.getImportantValue()) {
				System.out.println("Player 1 and Player 2 Have Pairs but Player 1 has a higher card");
				System.out.println("Player 1's highest card was:  " + importantValue);
				System.out.println("Player 2's highest card was:  " + player2.getImportantValue());
				return true;
			} else {
				if(getImportantValue() == player2.getImportantValue()) {
					System.out.println(getImportantValue());
					System.out.println(getImportantValue2());
					if(getImportantValue2() > player2.getImportantValue2()) {
						System.out.println("Player 1 and Player 2 Have Pairs but Player 1 has a higher card");
						System.out.println("Player 1's highest card was:  " + getImportantValue2());
						System.out.println("Player 2's highest card was:  " + player2.getImportantValue2());
						return true;
					} else if (getImportantValue2() < player2.getImportantValue2()) {
						System.out.println("Player 1 and Player 2 Have Pairs but Player 2 has a higher card");
						System.out.println("Player 1's highest card was:  " + getImportantValue2());
						System.out.println("Player 2's highest card was:  " + player2.getImportantValue2());
						return false;
					}
				} else {
					System.out.println("Player 1 and Player 2 Have Pairs but Player 2 has a higher card");
					System.out.println("Player 1's highest card was:  " + getImportantValue());
					System.out.println("Player 2's highest card was:  " + player2.getImportantValue());
					return false;
				}
			}
		}
		System.out.println("Player 1's highest card was:  " + highCard);
		System.out.println("Player 2's highest card was:  " + player2HighCard);
		// Compare 1st/2nd/third highest
		while(highCard == player2HighCard) {
			l.add(highCard);
			l2.add(player2HighCard);
			highCard = getHighCard(l);
			player2HighCard = player2.getHighCard(l2);
		};
		return highCard > player2HighCard;
	}

	public int getImportantValue() {
		return importantValue;
	}

	public void setImportantValue(int importantValue) {
		this.importantValue = importantValue;
	}

	public int getImportantValue2() {
		return importantValue2;
	}

	public void setImportantValue2(int importantValue2) {
		this.importantValue2 = importantValue2;
	}
}
