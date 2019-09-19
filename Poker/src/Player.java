import java.util.LinkedList;

public class Player {
	private LinkedList<Card> cards;
	private int points;
	public Player(String n) {
		//System.out.println("New player made. Name is " + name);
		cards = new LinkedList<>();
	}
	public void addCard(Card card) {
		this.addCard(card, false);
	}
	public void addCard(Card card, boolean makeRevealable) {
		if(makeRevealable) {
			card.makeRevealable();
		}
		//System.out.print("Adding Card: ");
		//card.show();
		cards.add(card);
	}
	
	public int getNumberCards() {
		System.out.println("You have " + cards.size() + " cards");
		return cards.size();
		
	}
	public boolean hasCard(Card card) {
		return cards.contains(card);
	}
	
	public void clearHand() {
		cards.clear();
	}
	
	public void showCards() {
		System.out.print(" [ ");
		for(int i = 0; i < cards.size(); i++){
			cards.get(i).show();
			if(i < cards.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print(" ] ");
		System.out.println();
	}
	public LinkedList<Card> getCards() {
		return cards;
	}
	public void replaceCard(int index, Card newCard) {
		newCard.makeRevealable();
		cards.set(index, newCard);
		
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void increasePoints(int points) {
		this.points += points;
	}
	
	public void decreasePoints(int points) {
		this.points -= points;
	}
}
