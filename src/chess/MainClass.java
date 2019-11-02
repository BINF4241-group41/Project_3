package chess;

import java.util.Scanner;


public class MainClass {
	
	public static void main(String args[]) {

		String[] playerNames = new String[2];
		Game myGame;

		Scanner myScanner = new Scanner(System.in);

		System.out.println("Enter the name of the white player.");
		playerNames[0] = myScanner.nextLine();

		System.out.println("Enter the name of the black player.");
		playerNames[1] = myScanner.nextLine();

		myGame = Game.getInstance(playerNames);


		System.out.println("\nStart state:");
		System.out.println(myGame.toString());

		while (!myGame.isFinished()) {

			System.out.println("\nEnter player " + myGame.getNextPlayerName() + "'s move.");
			String nextMove = myScanner.nextLine();

			boolean wasSuccessful = myGame.makeMove(nextMove);

			if (!wasSuccessful) {
				System.out.println("Invalid move.");
				continue; // execute again with same player
			}

			System.out.println(myGame.toString());
		}


		System.out.println("\n" + myGame.getWinnerName() + " wins!");
	}
}