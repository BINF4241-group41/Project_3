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

		// set up Pawns
		for (int i = 0; i < 7; ++i) {
			Pawn whitePawn = new Pawn(Color.WHITE, gameBoard[1][i]);
			gameBoard[1][i].setPiece(whitePawn);
			whitePlayer.addPiece(whitePawn);
			Pawn blackPawn = new Pawn(Color.BLACK, gameBoard[6][i]);
			gameBoard[6][i].setPiece(blackPawn);
			blackPlayer.addPiece(blackPawn);
		}

		Tower whiteTower = new Tower(Color.WHITE, gameBoard[0][0]);
		gameBoard[0][0].setPiece(whiteTower);
		whitePlayer.addPiece(whiteTower);
		whiteTower = new Tower(Color.WHITE, gameBoard[0][7]);
		gameBoard[0][7].setPiece(whiteTower);

		Tower blackTower = new Tower(Color.BLACK, gameBoard[7][0]);
		gameBoard[7][0].setPiece(blackTower);
		blackPlayer.addPiece(blackTower);
		blackTower = new Tower(Color.BLACK, gameBoard[7][7]);
		gameBoard[7][7].setPiece(blackTower);
		blackPlayer.addPiece(blackTower);

		Horse whiteHorse = new Horse(Color.WHITE, gameBoard[0][1]);
		gameBoard[0][1].setPiece(whiteHorse);
		whitePlayer.addPiece(whiteHorse);
		whiteHorse = new Horse(Color.WHITE, gameBoard[0][6]);
		gameBoard[0][6].setPiece(whiteHorse);

		Horse blackHorse = new Horse(Color.BLACK, gameBoard[7][1]);
		gameBoard[7][1].setPiece(blackHorse);
		blackPlayer.addPiece(blackHorse);
		blackHorse = new Horse(Color.BLACK, gameBoard[7][6]);
		gameBoard[7][6].setPiece(blackHorse);
		blackPlayer.addPiece(blackHorse);

		Bishop whiteBishop = new Bishop(Color.WHITE, gameBoard[0][2]);
		gameBoard[0][2].setPiece(whiteBishop);
		whitePlayer.addPiece(whiteBishop);
		whiteBishop = new Bishop(Color.WHITE, gameBoard[0][5]);
		gameBoard[0][5].setPiece(whiteBishop);
		whitePlayer.addPiece(whiteBishop);

		Bishop blackBishop = new Bishop(Color.BLACK, gameBoard[7][2]);
		gameBoard[7][2].setPiece(blackBishop);
		blackPlayer.addPiece(blackBishop);
		blackBishop = new Bishop(Color.BLACK, gameBoard[7][5]);
		gameBoard[7][5].setPiece(blackBishop);
		blackPlayer.addPiece(blackBishop);

		Queen whiteQueen = new Queen(Color.WHITE, gameBoard[0][3]);
		gameBoard[0][3].setPiece(whiteQueen);
		whitePlayer.addPiece(whiteQueen);

		Queen blackQueen = new Queen(Color.BLACK, gameBoard[7][3]);
		gameBoard[7][3].setPiece(blackQueen);
		blackPlayer.addPiece(blackQueen);

		King whiteKing = new King(Color.WHITE, gameBoard[0][4]);
		gameBoard[0][4].setPiece(whiteKing);
		whitePlayer.addPiece(whiteKing);
		
		King blackKing = new King(Color.BLACK, gameBoard[7][4]);
		gameBoard[7][4].setPiece(blackKing);
		blackPlayer.addPiece(blackKing);
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