import java.util.Collections;
import java.util.LinkedList;
public class Deck {
	private LinkedList<Card> cards;
	public Deck() {
		this.cards = new LinkedList<Card>();
		this.fillDeck();
	}
	
	// fills deck with cards
	private void fillDeck() {
		String[] suits = Constant.SUITS;
		int[] values = Constant.VALUES;
		String[] names = Constant.NAMES;
		for(int i = 0; i < suits.length; i ++) {
			for(int j = 0; j < values.length; j ++) {
				Card newCard = new Card(names[j], values[j], suits[i]);
				this.cards.add(newCard);
			}
		}		
	}
	
	// shuffles our list at random
	public void shuffleDeck() {
		Collections.shuffle(cards);
		System.out.println("Shuffled Deck");
	}
	
	// shows element at front of our deck
	public void peek() {
		cards.peek().show();
	}
	
	//deals a card from deck
	public Card deal() {
		Card poppedCard = cards.pop();
		return poppedCard;
	}
	
	//A toString for the deck
	public void show() {
		System.out.print("Deck of Cards with " + cards.size() + " cards in the deck");
	}
	

	public boolean isEmpty() {
		return cards.size() == 0;
	}
	
	
}
