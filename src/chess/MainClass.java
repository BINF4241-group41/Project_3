package chess;


public class MainClass {
	
	public static void main(String args[]) {

	    String[] playerNames = {"Player1", "PLayer2"};

		Game myGame = new Game(playerNames);

		myGame.toString();

		myGame.makeMove("a3");

		return;
	}
}