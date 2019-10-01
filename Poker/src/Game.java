import java.util.ArrayList;
import java.util.Scanner;
public class Game {
	@SuppressWarnings("unused")
	private boolean end;
	private static boolean raised;
	private static int raisedAmount;
	private static int pot;
	
	public static void main(String[] args) {
		// 
		Scanner scanner = new Scanner(System.in);
		//int cost for round
		int roundCost = 5;
		//Every hand, we shuffle, deal out cards. 
		//Get a chance to hold or pass on the cards
		System.out.println("Starting Game");
		Game game = new Game();
		System.out.println("Enter a name for player 1 below");
		String name1 = scanner.nextLine();
		System.out.println();
		
		System.out.println("Enter a name for player 2 below");
		String name2 = scanner.nextLine();
		System.out.println();
		
		Player player1 = new Player(name1);
		Player player2 = new Player(name2);
		player1.setPoints(100);
		player2.setPoints(100);;
		System.out.println("Welcome to the game: " + name1 + " and " + name2);
		boolean end = false;
		while(!end) {
			//Welcome messages
			System.out.println("Welcome to a new round. The entry cost is 5 dollars");
			boolean isValidInput = false;
			String input = "";
			while(!isValidInput) {
				System.out.println("Do you want to keep playing. Type Yes to continue, No to stop");
				input = scanner.nextLine().trim();
				if(input.toLowerCase().equals("no")) {
					System.out.println("You have Indicated No! Game has ended!");
					isValidInput = true;
					end = true;
				} else if (!input.toLowerCase().equals("no") && !input.toLowerCase().equals("yes")) {
					System.out.println("Input not valid. Try again");
				} else {
					isValidInput = true;
				}
			}
			if(end) {
				break;
			}
			if(roundCost > player1.getPoints() && roundCost > player2.getPoints()) {
				int winner = player1.getPoints() - player2.getPoints();
				if(winner == 0) {
					System.out.println("neither player has enough to continue. It is a tie");
				} else if (winner > 0) {
					System.out.println("Neither player has enough to continue. But the winner is player 1 by " + Math.abs(winner) + "points");	
				} else {
					System.out.println("Neither player has enough to continue. But the winner is player 2 by " + Math.abs(winner) + " points");
				}
			} else if(roundCost > player1.getPoints()) {
				System.out.println("Game is over. Player 2 wins! Player 1 Does not have enough points");
				break;
			} else if(roundCost > player2.getPoints()) {
				System.out.println("Game is over. Player 1 wins! Player 2 Does not have enough points");
				break;
			}
			player1.decreasePoints(roundCost);
			player2.decreasePoints(roundCost);
			pot = 2 * roundCost;
			System.out.println(name1 + " has " + player1.getPoints() + " points");
			System.out.println(name2 + " has " + player2.getPoints() + " points");
			Deck deck = new Deck();
			deck.shuffleDeck();
			
			//Deal Cards to the two Players
			for(int i = 0; i < 5; i ++) {
				if(deck.isEmpty()) {
					end = true;
					break;
				}
				player1.addCard(deck.deal(), true);
				if(deck.isEmpty()) {
					end = true;
					break;
				}
				player2.addCard(deck.deal(), true);
			}
			
			boolean player1Folds = false;
			boolean player2Folds = false;
			isValidInput = false;
			//TimeUnit.SECONDS.sleep(1);
			while(!isValidInput) {
				System.out.println("Choose who goes first now! Type either 'Player 1' or 'Player 2");
				input = scanner.nextLine();
				if(input.equals("Player 1")) {
					player1Folds = distributeAndFoldSequence(player1, scanner, deck, game, "Player 1 Draw");
					boolean didRaise = false;
					if(!player1Folds) {
						didRaise = raise(scanner, player1);
					}
					System.out.println();
					System.out.println();
					System.out.println();
					player2Folds =  distributeAndFoldSequence(player2, scanner, deck, game, "Player 2 Draw");
					if(!player1Folds && !player2Folds && didRaise) {
						player2Folds = !match(scanner, player2);
					}
					isValidInput = true;
				} else if(input.equals("Player 2")){
					player2Folds = distributeAndFoldSequence(player2, scanner, deck, game, "Player 2 Draw");
					boolean didRaise = false;
					if(!player2Folds) {
						didRaise = raise(scanner, player2);
					}
					System.out.println();
					System.out.println();
					System.out.println();
					player1Folds = distributeAndFoldSequence(player1, scanner, deck, game, "Player 1 Draw");
					if(!player2Folds && !player1Folds && didRaise) {
						player1Folds = !match(scanner, player1);
					}
					isValidInput = true;
				} else {
					System.out.println("Wrong input format. Try again");
				}
			}
			
				
			//Deciding who wins and updating scores
			if(player1Folds && !player2Folds) {
				System.out.println("Player 1 folds. 2 Wins");
				System.out.println("Increasing points by " + pot);
				player2.increasePoints(pot);
			} else if (player2Folds && !player1Folds) {
				System.out.println("Player 2 folds. 1 Wins");
				System.out.println("Increasing points by " + pot);
				player1.increasePoints(pot);
			} else if (!player1Folds && !player2Folds) {
				System.out.println("Neither player folds. Tie breaker");
				PlayerScorer newScorer1 = new PlayerScorer(player1.getCards());
				PlayerScorer newScorer2 = new PlayerScorer(player2.getCards());
				if(newScorer1.beats(newScorer2)) {
					System.out.println("Player 1 wins tie breaker");
					System.out.println("Increasing points by " + pot);
					player2.increasePoints(pot);
					System.out.println("Player 1 now has " + player1.getPoints() + " points");
				} else {
					System.out.println("Player 2 wins tie breaker");
					System.out.println("Increasing points by " + pot);
					player2.increasePoints(pot);
					System.out.println("Player 2 now has " + player2.getPoints() + " points");
				}
			}
			System.out.println();
			System.out.println();
			player1.clearHand();
			player2.clearHand();
		}
	}
	// logic if you want to raise or match
	// returns true if you decide to raise or succeed in matching
	// returns false if you decide not to raise or decide not to match
	private static boolean raise(Scanner scanner, Player player) {
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Want to raise? Type yes or no");
			System.out.println();
			String input = scanner.nextLine().toLowerCase().trim();
			if(input.equals("yes")) {
				System.out.println("How much?");
				System.out.println();
				boolean validRaise = false;
				int amount = 0;
				while(!validRaise) {
					input = scanner.nextLine();
					amount = Integer.parseInt(input);
					if(amount > player.getPoints()) {
						System.out.println("You don't have enough points. You currently have " + player.getPoints() + " points. Please reinput");
					} else {
						validRaise = true;
					}
				}
				player.decreasePoints(amount);
				pot += amount;
				System.out.println("Pot is now " + pot);
				System.out.println("Your score is now " + player.getPoints());
				raisedAmount = amount;
				return true;
			} else if (input.equals("no")) {
				return false;
			} else {
				System.out.println("Wrong input format. Try again");
			}
		}
		return false;
		
	}
	private static boolean match(Scanner scanner, Player player) {
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Want to Match? Type yes or no");
			System.out.println();
			String input = scanner.nextLine().toLowerCase().trim();
			if(input.equals("yes")) {
				if(raisedAmount > player.getPoints()) {
					System.out.println("You don't have enough to match the raise. The game resorts to auto folding");
					return false;
				}
				 player.decreasePoints(raisedAmount);
				 pot += raisedAmount;
				 System.out.println("Pot is now " + pot);
				 System.out.println("Your score is now " + player.getPoints());
				 raisedAmount = 0;
				 return true;
			} else if (input.equals("no")) {
				return false;
			} else {
				System.out.println("Wrong input format. Try again");
			}
		}
		return false;
	}
	// Deals with all parts of draw poker until after your fold
	// returns true if you folded
	// returns false if you did not
	private static boolean distributeAndFoldSequence(Player player1, Scanner scanner, Deck deck, Game game, String msg) {
		System.out.println(msg);
		// Show initial cards
		player1.showCards();
		// Now that you've seen your cards. Choose if you want to replace some
		game.deckManipulationSequence(scanner, player1, deck);
		//Player 1 can fold
		boolean folded = game.askFold(player1, scanner);
		return folded;
		
	}
	// This sequence includes the free reroll and any subsequent paid ones
	public void deckManipulationSequence(Scanner scanner, Player player, Deck deck) {
		boolean validInput = false;
		while(!validInput) {
			try {
				defaultReroll(scanner, deck, player);
				reroll(scanner, player, deck);
				validInput = true;
			}
			catch(Exception e) {
				System.out.println("Something went wrong. Try again");
			}		
		}
	}
	//This is a general reroll. Costs 10 dollars per reroll.
	public void reroll(Scanner scanner, Player player, Deck deck) {
		boolean continueReroll = true;
		while(continueReroll) {
			System.out.println("Want to Reroll again for 10 bucks? Yes or No");
			String input = scanner.nextLine().toLowerCase().trim();
			if(input.equals("yes")) {
				if(player.getPoints() >= 10) {
					player.decreasePoints(10);
					System.out.println("Your new score is: " + player.getPoints());
					player.showCards();
					defaultReroll(scanner, deck, player);
				} else {
					System.out.println("You don't have enough money");
					continueReroll = false;
				}
			} else if (input.equals("no")){
				continueReroll = false;
			} else {
				System.out.println("Wrong input format. Try again");
			}
		}
	}
	// Every player gets one free reroll. Pick which indexes (1-5) you want to reroll.
	// This method executes that default reroll.
	public void defaultReroll(Scanner scanner, Deck deck, Player player) {
		boolean valid = false;
		while(!valid) {
			System.out.println("Choose what cards you want to get rid of. Press enter for none");
			String input = scanner.nextLine();
			if(!input.isEmpty()) {
				try {
					String[] inputList = input.trim().split("\\s*,\\s*");
					ArrayList<Integer> finalIndexes = new ArrayList<>();
					for(String str : inputList) {
						int index = Integer.parseInt(str);
						if(index >= 1 && index < 6) {
							finalIndexes.add(index - 1);
						}
					}		
				
					for(int index : finalIndexes) {
						if(deck.isEmpty()) {
							end = true;
							break;
						}
						player.replaceCard(index, deck.deal());
					}
					valid = true;
				} 
				catch (Exception e) {
					System.out.println("Input format wrong. Try again");
				}
			} else {
				valid = true;
			}
		}
		player.showCards();	
	}
	// ask if you want to fold.
	// returns true if you folded
	// returns false otherwise
	public boolean askFold(Player p1, Scanner scanner) {
		boolean isValid = false;
		while(!isValid) {
			System.out.println("Want to fold?");
			System.out.println();
			String input = scanner.nextLine().toLowerCase().trim();
			if(input.equals("yes")) {
				return true;
			} else if(input.equals("no")) {
				return false;
			} else {
				System.out.println("Wrong input format. Try again");
			}
		}
		return false;
	}
}
