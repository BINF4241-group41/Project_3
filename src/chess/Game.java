package chess;


public class Game {
	
	private Square[8][8] gameBoard; // rank x file (gameBoard[0][7] == 1h)
	
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

		for (int rankCount = 1; rankCount <= 8; ++rankCount) { // horizontal (1-8)
			for (int fileCount = 1; fileCount <= 8; ++fileCount) { // vertical (a-h)

				Color color;
				(file + rank) % 2 == 0 ? color = BLACK : color = WHITE;

				Rank rank = Rank.valueof(rankCount); // 1 -> (Rank) 1, ...
				File file = File.valueOf(fileCount); // 1 -> a, 2 -> b, ...

				gameBoard[rankCount - 1][fileCount - 1] = new Square(color, rank, file);
			}
		}

		piecesSetup();
	}


	// set up pieces at start of game
	private void piecesSetup() {
		// set up pieces at start of game
	}


	public boolean isValidMove(String moveDescription) {
		// determine piece
		// check if valid move
	}


	public void makeMove(String moveDescription) {
		if (isValidMove(moveDescription)) {
			// make move
		}
	}


	public String toString() {
		string stringRepresentation;

		for (int rank = 7; rank >= 0; --rank) { // horizontal (1-8)
			for (int file = 0; file < 8; ++file) { // vertical (a-h)
				stringRepresentation += "[";
				stringRepresentation += (gameBoard[rank][file].getPiece() ? gameBoard[rank][file].getPiece().toString() : "");
				stringRepresentation += "]";
			}
			stringRepresentation += "\n";
		}
		return "";
	}
}