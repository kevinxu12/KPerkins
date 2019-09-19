import java.util.HashMap;

public final class Constant {
	private Constant() {
		
	}
	public static final String[] SUITS = {"Spades", "Hearts", "Clubs", "Diamonds"};
	public static final int[] VALUES = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
	public static final String[] NAMES = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	public static final HashMap<String, String> SYMBOLMAP = Constant.createSymbolMap();
	public static final HashMap<Integer, String> VALUEMAP = Constant.createValueMap();
	private static HashMap<String, String> createSymbolMap() {
		HashMap<String, String> symbolMap = new HashMap<>();
		symbolMap.put("Spades", "♠");
		symbolMap.put("Hearts", "♥");
		symbolMap.put("Clubs", "♣");
		symbolMap.put("Diamonds", "♦");
		return symbolMap;
	}
	private static HashMap<Integer, String> createValueMap() {
		HashMap<Integer, String> valueMap = new HashMap<>();
		valueMap.put(1, "1");
		valueMap.put(2, "2");
		valueMap.put(3, "3");
		valueMap.put(4, "4");
		valueMap.put(5, "5");
		valueMap.put(6, "6");
		valueMap.put(7, "7");
		valueMap.put(8, "8");
		valueMap.put(9, "9");
		valueMap.put(10, "10");
		valueMap.put(11, "Jack");
		valueMap.put(12, "Queen");
		valueMap.put(13, "King");
		valueMap.put(14, "Ace");
		return valueMap;
	}
} 
