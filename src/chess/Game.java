package chess;


public class Game {
	
	private Square[8][8] gameBoard;
	
	private Player whitePlayer;
	private Player blackPlayer;
	
	
	public Game(String[] playerNames) {
		
		if (playerNames.length < 2) {
			if (playerNames.length < 1) {
				playerNames = {"Default1"};
			}
			playerNames = {playerNames[0], "Default2"}
		}
		
		whitePlayer = new Player(Color.WHITE, playerNames[0]);
		blackPlayer = new Player(Color.BLACK, playerNames[1]);
		
		// initialize gameBoard
	}
	
	public String toString() {
		return "";
	}
}