
public class Card {
	private String name;
	private int value;
	private String suit;
	private boolean revealable;
	public Card (String n, int v, String s) {
		this.setName(n);
		this.setValue(v);
		this.setSuit(s);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void makeRevealable() {
		revealable = true;
	}
	public void show() {
		if(revealable) {
			String symbol = Constant.SYMBOLMAP.get(suit);
			String valueSymbol = Constant.VALUEMAP.get(value);
			System.out.print( valueSymbol + symbol);
		} else {
			System.out.print("Showing Card");
		}
	}
}
