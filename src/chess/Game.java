package chess;

import java.util.ArrayList;
import java.util.Iterator;


public class Game {
	
	private Square[][] gameBoard = new Square[8][8]; // rank x file (gameBoard[0][7] == 1h)
	
	private Player whitePlayer;
	private Player blackPlayer;

	private Player nextPlayer; // can play next move
	
	
	public Game(String[] playerNames) {

		/*
		if (playerNames.length < 2) {
			if (playerNames.length < 1) {
				playerNames = {"Default1"};
			}
			playerNames = {playerNames[0], "Default2"}
		}
		 */
		
		whitePlayer = new Player(Color.WHITE, playerNames[0]);
		blackPlayer = new Player(Color.BLACK, playerNames[1]);

		for (int rankCount = 1; rankCount <= 8; ++rankCount) { // horizontal (1-8)
			for (int fileCount = 1; fileCount <= 8; ++fileCount) { // vertical (a-h)

				Color color;
				color = ((fileCount + rankCount) % 2 == 0) ? Color.BLACK : Color.WHITE;

				Rank rank = Rank.valueOf(rankCount); // 1 -> (Rank) 1, ...
				File file = File.valueOf(fileCount); // 1 -> a, 2 -> b, ...

				gameBoard[rankCount - 1][fileCount - 1] = new Square(color, rank, file);
			}
		}

		piecesSetup();
		nextPlayer = whitePlayer;
	}


	// set up pieces at start of game
	private void piecesSetup() {

		// set up Pawns
		for (int i = 0; i < 7; ++i) {
			Pawn whitePawn = new Pawn(Color.WHITE, gameBoard[1][i].makeCopy());
			gameBoard[1][i].setPiece(whitePawn);
			whitePlayer.addPiece(whitePawn);
			Pawn blackPawn = new Pawn(Color.BLACK, gameBoard[6][i].makeCopy());
			gameBoard[6][i].setPiece(blackPawn);
			blackPlayer.addPiece(blackPawn);
		}

		Tower whiteTower = new Tower(Color.WHITE, gameBoard[0][0].makeCopy());
		gameBoard[0][0].setPiece(whiteTower);
		whitePlayer.addPiece(whiteTower);
		whiteTower = new Tower(Color.WHITE, gameBoard[0][7].makeCopy());
		gameBoard[0][7].setPiece(whiteTower);

		Tower blackTower = new Tower(Color.BLACK, gameBoard[7][0].makeCopy());
		gameBoard[7][0].setPiece(blackTower);
		blackPlayer.addPiece(blackTower);
		blackTower = new Tower(Color.BLACK, gameBoard[7][7].makeCopy());
		gameBoard[7][7].setPiece(blackTower);
		blackPlayer.addPiece(blackTower);

		Knight whiteKnight = new Knight(Color.WHITE, gameBoard[0][1].makeCopy());
		gameBoard[0][1].setPiece(whiteKnight);
		whitePlayer.addPiece(whiteKnight);
		whiteKnight = new Knight(Color.WHITE, gameBoard[0][6].makeCopy());
		gameBoard[0][6].setPiece(whiteKnight);

		Knight blackKnight = new Knight(Color.BLACK, gameBoard[7][1].makeCopy());
		gameBoard[7][1].setPiece(blackKnight);
		blackPlayer.addPiece(blackKnight);
		blackKnight = new Knight(Color.BLACK, gameBoard[7][6].makeCopy());
		gameBoard[7][6].setPiece(blackKnight);
		blackPlayer.addPiece(blackKnight);

		Bishop whiteBishop = new Bishop(Color.WHITE, gameBoard[0][2].makeCopy());
		gameBoard[0][2].setPiece(whiteBishop);
		whitePlayer.addPiece(whiteBishop);
		whiteBishop = new Bishop(Color.WHITE, gameBoard[0][5].makeCopy());
		gameBoard[0][5].setPiece(whiteBishop);
		whitePlayer.addPiece(whiteBishop);

		Bishop blackBishop = new Bishop(Color.BLACK, gameBoard[7][2].makeCopy());
		gameBoard[7][2].setPiece(blackBishop);
		blackPlayer.addPiece(blackBishop);
		blackBishop = new Bishop(Color.BLACK, gameBoard[7][5].makeCopy());
		gameBoard[7][5].setPiece(blackBishop);
		blackPlayer.addPiece(blackBishop);

		Queen whiteQueen = new Queen(Color.WHITE, gameBoard[0][3].makeCopy());
		gameBoard[0][3].setPiece(whiteQueen);
		whitePlayer.addPiece(whiteQueen);

		Queen blackQueen = new Queen(Color.BLACK, gameBoard[7][3].makeCopy());
		gameBoard[7][3].setPiece(blackQueen);
		blackPlayer.addPiece(blackQueen);

		King whiteKing = new King(Color.WHITE, gameBoard[0][4].makeCopy());
		gameBoard[0][4].setPiece(whiteKing);
		whitePlayer.addPiece(whiteKing);

		King blackKing = new King(Color.BLACK, gameBoard[7][4].makeCopy());
		gameBoard[7][4].setPiece(blackKing);
		blackPlayer.addPiece(blackKing);
	}


	// check if the input string could describe a valid move (excluding special cases)
	public boolean isWellformedMoveDescription(String moveDescription) {

		// TODO: Maybe use regex?

		// check length
		if (Character.isUpperCase(moveDescription.charAt(0))) {
			if (moveDescription.contains("x")) {
				if (moveDescription.length() < 4 || 6 < moveDescription.length()) {
					return false;
				}
			}
			else {
				if (moveDescription.length() < 3 || 5 < moveDescription.length()) {
					return false;
				}
			}
		}
		// Pawn
		else {
			if (moveDescription.contains("x")) {
				return (3 <= moveDescription.length() && moveDescription.length() <= 5);
			}
			else {
				return (2 <= moveDescription.length() && moveDescription.length() <= 4);
			}
		}

		if (Character.isUpperCase(moveDescription.charAt(0))) {
			String pieceString = moveDescription.substring(0, 1);
			if (!pieceString.contains("T") && !pieceString.contains("N") && !pieceString.contains("B") && !pieceString.contains("Q") && !pieceString.contains("K")) {
				return false;
			}
		}

		// check for valid destination
		String inputRank = moveDescription.substring(moveDescription.length() - 1); // last char -> String
		String inputFile = moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1); // 2nd last char -> String

		if (File.valueOf(Integer.valueOf(inputFile)) == null || Rank.valueOf(Integer.valueOf(inputRank)) == null) {
			return false;
		}

		int startIndex = (Character.isUpperCase(moveDescription.charAt(0)) ? 1 : 0); // if first char is piecetype, ignore
		int endIndex = (moveDescription.contains("x") ? moveDescription.length() - 3 : moveDescription.length() - 2); // ignore destination and x if set
		String originString = moveDescription.substring(startIndex, endIndex);

		if (originString.length() == 1) {
			if (File.valueOf(Integer.valueOf(originString)) == null) {
				return false;
			}
		}
		else if (originString.length() == 2) {
			if (File.valueOf(Integer.valueOf(originString.substring(0, originString.length() - 1))) == null) {
				return false;
			}
			if (Rank.valueOf(Integer.valueOf(originString.substring(originString.length() - 1))) == null) {
				return false;
			}
		}

		return true;
	}


	// get the piece uniquely identified by the moveDescription (excluding special cases) if it exists
	private Piece identifyPiece(String moveDescription) {


		// Check for obstacles (except when knight).


		String pieceString = "P";

		if (Character.isUpperCase(moveDescription.charAt(0))) {
			pieceString = String.valueOf(moveDescription.charAt(0));
		}

		ArrayList<Piece> pieces = nextPlayer.getActivePieces();
		ArrayList<Piece> matchedPieces = new ArrayList<Piece>();

		// match piece type
		for (Piece p : pieces) {
			if (p.toString() == pieceString) {
				matchedPieces.add(p);
			}
		}

		// destination
		String inputRank = moveDescription.substring(moveDescription.length() - 1, moveDescription.length()); // last char -> String
		String inputFile = moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1); // 2nd last char -> String

		Square destinationSquare = gameBoard[Rank.valueOf(Integer.valueOf(inputRank)).getValue()][File.valueOf(Integer.valueOf(inputFile).getValue()];

		// remove pieces which can't make this move
		Iterator<Piece> iterator = matchedPieces.iterator();

		while (iterator.hasNext()) {
			if (!iterator.next().isMovePossible(destinationSquare)) {
				iterator.remove();
			}
		}

		// no pieces could be matched
		if (matchedPieces.size() < 1) {
			return null;
		}

		if (matchedPieces.size() > 1) {

			File originFile = null;
			Rank originRank = null;

			int startIndex = (Character.isUpperCase(moveDescription.charAt(0)) ? 1 : 0); // if first char is piecetype, ignore
			int endIndex = (moveDescription.contains("x") ? moveDescription.length() - 3 : moveDescription.length() - 2); // ignore destination and x if set

			String originString = moveDescription.substring(startIndex, endIndex);

			// insufficient information -> multiple matches
			if (originString.length() == 0) {
				return null;
			}

			// TODO: Implement special cases.

			else if (originString.length() == 1) {
				originFile = File.valueOf(Integer.valueOf(originString));
			}
			else if (originString.length() == 2) {
				originFile = File.valueOf(Integer.valueOf(originString.substring(0, originString.length() - 1)));
				originRank = Rank.valueOf(Integer.valueOf(originString.substring(originString.length() - 1)));
			}
			else {
				// special cases
				return null;
			}

			iterator = matchedPieces.iterator();

			while (iterator.hasNext()) {
				if (iterator.next().getFile() != originFile) {
					iterator.remove();
				}
				if (originRank != null && iterator.next().getRank() != originRank) {
					iterator.remove();
				}
			}
		}

		if (matchedPieces.size() != 1) {
			return null;
		}

		return matchedPieces.get(0);
	}


	public boolean makeMove(String moveDescription) {

		if (!isWellformedMoveDescription(moveDescription)) {
			return false;
		}

		Piece piece = identifyPiece(moveDescription);

		if (piece == null) {
			return false;
		}

		String inputRank = moveDescription.substring(moveDescription.length() - 1); // last char -> String
		String inputFile = moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1); // 2nd last char -> String
		Square destinationSquare = gameBoard[Rank.valueOf(inputRank).getValue()][File.valueOf(inputFile).getValue()];

		destinationSquare.removePiece();
		piece.movePiece(destinationSquare);

		nextPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);

		return true;
	}


	public String toString() {
		String stringRepresentation = "";

		for (int rank = 7; rank >= 0; --rank) { // horizontal (1-8)
			for (int file = 0; file < 8; ++file) { // vertical (a-h)
				stringRepresentation += "[";
				stringRepresentation += (gameBoard[rank][file].getPiece() != null ? gameBoard[rank][file].getPiece().toString() : "");
				stringRepresentation += "]";
			}
			stringRepresentation += "\n";
		}
		return "";
	}
}