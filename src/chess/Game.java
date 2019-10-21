package chess;

import java.util.ArrayList;
import java.util.Iterator;


public class Game {
	
	private GameBoard gameBoard;
	
	private Player whitePlayer;
	private Player blackPlayer;

	private String winnerName = null;

	private Player nextPlayer; // can play next move


	public Player getNextPlayerName() {
		return nextPlayer.getName();
	}

	public String getWinnerName() {
		return winnerName;
	}

	public Game(String[] playerNames) {
		
		whitePlayer = new Player(Color.WHITE, (playerNames.length() > 0 ? playerNames[0] : "white"));
		blackPlayer = new Player(Color.BLACK, (playerNames.length() > 1 ? playerNames[1] : "black"));

		gameBoard = new GameBoard();

		piecesSetup();
		nextPlayer = whitePlayer;
	}


	// set up pieces at start of game
	private void piecesSetup() {

		// set up Pawns
		for (int i = 0; i < 7; ++i) {
			Pawn whitePawn = new Pawn(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(2), File.valueOf(i)));
			gameBoard.setPieceAtPosition(whitePawn.makeCopy(), Rank.valueOf(2),File.valueOf(i));
			whitePlayer.addPiece(whitePawn);
			Pawn blackPawn = new Pawn(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(7), File.valueOf(i)));
			gameBoard.setPieceAtPosition(blackPawn.makeCopy(), Rank.valueOf(7), File.valueOf(i));
			blackPlayer.addPiece(blackPawn);
		}

		Tower whiteTower = new Tower(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(1)));
		gameBoard.setPieceAtPosition(whiteTower.makeCopy(), Rank.valueOf(1), File.valueOf(1));
		whitePlayer.addPiece(whiteTower);
		whiteTower = new Tower(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(8)));
		gameBoard.setPieceAtPosition(whiteTower.makeCopy(), Rank.valueOf(1), File.valueOf(8));
		whitePlayer.addPiece(whiteTower);

		Tower blackTower = new Tower(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(1)));
		gameBoard.setPieceAtPosition(blackTower.makeCopy(), Rank.valueOf(8), File.valueOf(1));
		blackPlayer.addPiece(blackTower);
		blackTower = new Tower(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(8)));
		gameBoard.setPieceAtPosition(blackTower.makeCopy(), Rank.valueOf(8), File.valueOf(8));
		blackPlayer.addPiece(blackTower);

		Knight whiteKnight = new Knight(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(2)));
		gameBoard.setPieceAtPosition(whiteKnight.makeCopy(), Rank.valueOf(1), File.valueOf(2));
		whitePlayer.addPiece(whiteKnight);
		whiteKnight = new Knight(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(7)));
		gameBoard.setPieceAtPosition(whiteKnight.makeCopy(), Rank.valueOf(1), File.valueOf(7));
		whitePlayer.addPiece(whiteKnight);

		Knight blackKnight = new Knight(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(2)));
		gameBoard.setPieceAtPosition(blackKnight.makeCopy(), Rank.valueOf(8), File.valueOf(2));
		blackPlayer.addPiece(blackKnight);
		blackKnight = new Knight(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(7)));
		gameBoard.setPieceAtPosition(blackKnight.makeCopy(), Rank.valueOf(8), File.valueOf(7));
		blackPlayer.addPiece(blackKnight);

		Bishop whiteBishop = new Bishop(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(3)));
		gameBoard.setPieceAtPosition(whiteBishop.makeCopy(), Rank.valueOf(1), File.valueOf(3));
		whitePlayer.addPiece(whiteBishop);
		whiteBishop = new Bishop(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(6)));
		gameBoard.setPieceAtPosition(whiteBishop.makeCopy(), Rank.valueOf(1), File.valueOf(6));
		whitePlayer.addPiece(whiteBishop);

		Bishop blackBishop = new Bishop(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(3)));
		gameBoard.setPieceAtPosition(blackBishop.makeCopy(), Rank.valueOf(8), File.valueOf(3));
		blackPlayer.addPiece(blackBishop);
		blackBishop = new Bishop(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(6)));
		gameBoard.setPieceAtPosition(blackBishop.makeCopy(), Rank.valueOf(8), File.valueOf(6));
		blackPlayer.addPiece(blackBishop);

		Queen whiteQueen = new Queen(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(4)));
		gameBoard.setPieceAtPosition(whiteQueen.makeCopy(), Rank.valueOf(1), File.valueOf(4));
		whitePlayer.addPiece(whiteQueen);

		Queen blackQueen = new Queen(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(4)));
		gameBoard.setPieceAtPosition(blackQueen.makeCopy(), Rank.valueOf(8), File.valueOf(4));
		blackPlayer.addPiece(blackQueen);

		King whiteKing = new King(Color.WHITE, gameBoard.getSquareAtPosition(Rank.valueOf(1), File.valueOf(5)));
		gameBoard.setPieceAtPosition(whiteKing.makeCopy(), Rank.valueOf(1), File.valueOf(5));
		whitePlayer.addPiece(whiteKing);

		King blackKing = new King(Color.BLACK, gameBoard.getSquareAtPosition(Rank.valueOf(8), File.valueOf(5)));
		gameBoard.setPieceAtPosition(blackKing.makeCopy(), Rank.valueOf(8), File.valueOf(5));
		blackPlayer.addPiece(blackKing);
	}


	// check if the input string could describe a valid move (excluding special cases)
	public boolean isWellformedMoveDescription(String moveDescription) {

		// first match all normal descriptions, then check if it contains origin rank but not file
		boolean isValidNormal = moveDescription.matches("[TNBQK]?[a-h]?[1-8]?x?[a-h][1-8]") && !moveDescription.matches("[TNBQK]?[1-8]x?[a-h][1-8]");

		if (!isValidNormal) {
			// check special cases
			return false; // also no special case
		}

		return true;
	}


	// get the piece uniquely identified by the moveDescription (excluding special cases) if it exists
	private Piece identifyPiece(String moveDescription) {


		// Check for obstacles (except when knight).


		String pieceString = "P";

		if (moveDescription.matches("[TNBQK].*")) {
			pieceString = moveDescription.substring(0, 1);
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
		String inputRank = moveDescription.substring(moveDescription.length() - 1); // last character
		String inputFile = moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1); // 2nd last character

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

			int startIndex = (moveDescription.matches("[TNBQK].*") ? 1 : 0); // if first char is piecetype, ignore
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

		String inputRank = moveDescription.substring(moveDescription.length() - 1); // last character
		String inputFile = moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1); // 2nd last character
		Square destinationSquare = gameBoard[Rank.valueOf(inputRank).getValue()][File.valueOf(inputFile).getValue()];

		if (destinationSquare.getPiece() != null) {
			Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
			otherPlayer.eatPiece(destinationSquare.getFile(), destinationSquare.getRank());
		}

		piece.movePiece(destinationSquare);
		gameBoard.setPieceAtPosition(piece.makeCopy(), destinationSquare.getRank(), destinationSquare.getFile());

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