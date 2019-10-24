package chess;

import java.util.ArrayList;
import java.util.Iterator;


public class Game {
	
	private GameBoard gameBoard;
	
	private Player whitePlayer;
	private Player blackPlayer;

	private Player winner;

	private Player nextPlayer; // can play next move


	public String getNextPlayerName() {
		return nextPlayer.getName();
	}

	public String getWinnerName() {
		return (winner != null ? winner.getName() : null);
	}

	public Game(String[] playerNames) {
		
		whitePlayer = new Player(Color.WHITE, (playerNames.length > 0 ? playerNames[0] : "white"));
		blackPlayer = new Player(Color.BLACK, (playerNames.length > 1 ? playerNames[1] : "black"));

		gameBoard = new GameBoard();

		piecesSetup();
		nextPlayer = whitePlayer;
	}


	// set up pieces at start of game
	private void piecesSetup() {

		// set up Pawns
		for (int i = 1; i <= 8; ++i) {
			Pawn whitePawn = new Pawn(Color.WHITE, Rank.valueOf(2), File.valueOf(i));
			gameBoard.setPieceAtPosition(whitePawn.makeCopy(), Rank.valueOf(2), File.valueOf(i));
			whitePlayer.addPiece(whitePawn);
			Pawn blackPawn = new Pawn(Color.BLACK, Rank.valueOf(7), File.valueOf(i));
			gameBoard.setPieceAtPosition(blackPawn.makeCopy(), Rank.valueOf(7), File.valueOf(i));
			blackPlayer.addPiece(blackPawn);
		}

		Tower whiteTower = new Tower(Color.WHITE, Rank.valueOf(1), File.valueOf(1));
		gameBoard.setPieceAtPosition(whiteTower.makeCopy(), Rank.valueOf(1), File.valueOf(1));
		whitePlayer.addPiece(whiteTower);
		whiteTower = new Tower(Color.WHITE, Rank.valueOf(1), File.valueOf(8));
		gameBoard.setPieceAtPosition(whiteTower.makeCopy(), Rank.valueOf(1), File.valueOf(8));
		whitePlayer.addPiece(whiteTower);

		Tower blackTower = new Tower(Color.BLACK, Rank.valueOf(8), File.valueOf(1));
		gameBoard.setPieceAtPosition(blackTower.makeCopy(), Rank.valueOf(8), File.valueOf(1));
		blackPlayer.addPiece(blackTower);
		blackTower = new Tower(Color.BLACK, Rank.valueOf(8), File.valueOf(8));
		gameBoard.setPieceAtPosition(blackTower.makeCopy(), Rank.valueOf(8), File.valueOf(8));
		blackPlayer.addPiece(blackTower);

		Knight whiteKnight = new Knight(Color.WHITE, Rank.valueOf(1), File.valueOf(2));
		gameBoard.setPieceAtPosition(whiteKnight.makeCopy(), Rank.valueOf(1), File.valueOf(2));
		whitePlayer.addPiece(whiteKnight);
		whiteKnight = new Knight(Color.WHITE, Rank.valueOf(1), File.valueOf(7));
		gameBoard.setPieceAtPosition(whiteKnight.makeCopy(), Rank.valueOf(1), File.valueOf(7));
		whitePlayer.addPiece(whiteKnight);

		Knight blackKnight = new Knight(Color.BLACK, Rank.valueOf(8), File.valueOf(2));
		gameBoard.setPieceAtPosition(blackKnight.makeCopy(), Rank.valueOf(8), File.valueOf(2));
		blackPlayer.addPiece(blackKnight);
		blackKnight = new Knight(Color.BLACK, Rank.valueOf(8), File.valueOf(7));
		gameBoard.setPieceAtPosition(blackKnight.makeCopy(), Rank.valueOf(8), File.valueOf(7));
		blackPlayer.addPiece(blackKnight);

		Bishop whiteBishop = new Bishop(Color.WHITE, Rank.valueOf(1), File.valueOf(3));
		gameBoard.setPieceAtPosition(whiteBishop.makeCopy(), Rank.valueOf(1), File.valueOf(3));
		whitePlayer.addPiece(whiteBishop);
		whiteBishop = new Bishop(Color.WHITE, Rank.valueOf(1), File.valueOf(6));
		gameBoard.setPieceAtPosition(whiteBishop.makeCopy(), Rank.valueOf(1), File.valueOf(6));
		whitePlayer.addPiece(whiteBishop);

		Bishop blackBishop = new Bishop(Color.BLACK, Rank.valueOf(8), File.valueOf(3));
		gameBoard.setPieceAtPosition(blackBishop.makeCopy(), Rank.valueOf(8), File.valueOf(3));
		blackPlayer.addPiece(blackBishop);
		blackBishop = new Bishop(Color.BLACK, Rank.valueOf(8), File.valueOf(6));
		gameBoard.setPieceAtPosition(blackBishop.makeCopy(), Rank.valueOf(8), File.valueOf(6));
		blackPlayer.addPiece(blackBishop);

		Queen whiteQueen = new Queen(Color.WHITE, Rank.valueOf(1), File.valueOf(4));
		gameBoard.setPieceAtPosition(whiteQueen.makeCopy(), Rank.valueOf(1), File.valueOf(4));
		whitePlayer.addPiece(whiteQueen);

		Queen blackQueen = new Queen(Color.BLACK, Rank.valueOf(8), File.valueOf(4));
		gameBoard.setPieceAtPosition(blackQueen.makeCopy(), Rank.valueOf(8), File.valueOf(4));
		blackPlayer.addPiece(blackQueen);

		King whiteKing = new King(Color.WHITE, Rank.valueOf(1), File.valueOf(5));
		gameBoard.setPieceAtPosition(whiteKing.makeCopy(), Rank.valueOf(1), File.valueOf(5));
		whitePlayer.addPiece(whiteKing);

		King blackKing = new King(Color.BLACK, Rank.valueOf(8), File.valueOf(5));
		gameBoard.setPieceAtPosition(blackKing.makeCopy(), Rank.valueOf(8), File.valueOf(5));
		blackPlayer.addPiece(blackKing);
	}


	// check if the input string could describe a valid move (excluding special cases)
	public boolean isWellformedMoveDescription(String moveDescription) {

		// first match all normal descriptions, then check if it contains origin rank but not file
		boolean isValidNormal = moveDescription.matches("[TNBQK]?[a-h]?[1-8]?x?[a-h][1-8]") && !moveDescription.matches("[TNBQK]?[1-8]x?[a-h][1-8]");

		if (!isValidNormal) {
			// Promotion
			if (moveDescription.matches("x?[a-h][1-8][TNBQ]")) {
				return true;
			}
			// king-/ queenside castling
			if (moveDescription.equals("0-0") || moveDescription.equals("0-0-0")) {
				return true;
			}
			// check
			if (moveDescription.matches("[TNBQK]?[a-h]?[1-8]?x?[a-h][1-8]+") && !moveDescription.matches("[TNBQK]?[1-8]x?[a-h][1-8]+")) {
				return true;
			}
			return false; // also no special case
		}

		return true;
	}


	// get the piece uniquely identified by the moveDescription (excluding special cases) if it exists
	private Piece identifyPiece(String moveDescription) {

		String pieceString = "P";

		if (moveDescription.matches("[TNBQK].*")) {
			pieceString = moveDescription.substring(0, 1);
		}

		ArrayList<Piece> pieces = nextPlayer.getActivePieces();
		ArrayList<Piece> matchedPieces = new ArrayList<>();

		// match piece type
		for (Piece p : pieces) {
			if (p.getName() != null && p.getName().equals(pieceString)) {
				matchedPieces.add(p);
			}
		}

		// destination
		String inputRank = moveDescription.substring(moveDescription.length() - 1); // last character
		String inputFile = moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1); // 2nd last character

		Square destinationSquare = gameBoard.getSquareAtPosition(Rank.valueOf(Integer.valueOf(inputRank)), File.fromString(inputFile));

		// remove pieces which can't make this move
		Iterator<Piece> iterator = matchedPieces.iterator();

		while (iterator.hasNext()) {
			if (!iterator.next().isMoveAllowed(this.gameBoard, destinationSquare.getRank(), destinationSquare.getFile())) {
				iterator.remove();
			}
		}

		// no pieces could be matched
		if (matchedPieces.isEmpty()) {
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
				originFile = File.fromString(originString);
			}
			else if (originString.length() == 2) {
				originFile = File.fromString(originString.substring(0, originString.length() - 1));
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
		Square destinationSquare = gameBoard.getSquareAtPosition(Rank.valueOf(Integer.parseInt(inputRank)), File.fromString(inputFile));

		// Piece gets eaten by piece who lands there
		if (destinationSquare.getPiece() != null) {
			Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
			otherPlayer.eatPiece(destinationSquare.getRank(), destinationSquare.getFile());
		}

		// Piece gets eaten py Pawn by getting jumped over (only possible if Pawn hasn't moved yet)
		if (piece instanceof Pawn) {
			Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
			if (destinationSquare.getRank().getValue() - piece.getRank().getValue() == 2 && gameBoard.getPieceAtPosition(Rank.valueOf(destinationSquare.getRank().getValue() - 1), destinationSquare.getFile()) != null) {
					gameBoard.setPieceAtPosition(null, Rank.valueOf(destinationSquare.getRank().getValue() - 1), destinationSquare.getFile());
					otherPlayer.eatPiece(Rank.valueOf(destinationSquare.getRank().getValue() - 1), destinationSquare.getFile());
			}
			if (piece.getRank().getValue() - destinationSquare.getRank().getValue() == 2 && gameBoard.getPieceAtPosition(Rank.valueOf(destinationSquare.getRank().getValue() + 1), destinationSquare.getFile()) != null) {
					gameBoard.setPieceAtPosition(null, Rank.valueOf(destinationSquare.getRank().getValue() + 1), destinationSquare.getFile());
					otherPlayer.eatPiece(Rank.valueOf(destinationSquare.getRank().getValue() + 1), destinationSquare.getFile());
			}
		}

		gameBoard.setPieceAtPosition(null, piece.getRank(), piece.getFile());
		nextPlayer.movePiece(piece, destinationSquare.getRank(), destinationSquare.getFile());
		gameBoard.setPieceAtPosition(piece.makeCopy(), destinationSquare.getRank(), destinationSquare.getFile());

		nextPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);

		return true;
	}


	public String toString() {
		return gameBoard.toString();
	}


	// Returns true if nextPlayer is in check situation.
	private boolean isCheck() {

		Rank kingRank = null;
		File kingFile = null;

		ArrayList<Piece> nextPlayerPieces = nextPlayer.getActivePieces();

		// match piece type
		for (Piece p : nextPlayerPieces) {
			if (p.getName() != null && p.getName().equals("K")) {
				kingRank = p.getRank();
				kingFile = p.getFile();
			}
		}

		Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
		ArrayList<Piece> otherPlayerPieces = otherPlayer.getActivePieces();

		// match piece type
		for (Piece p : otherPlayerPieces) {
			if (p.isMoveAllowed(this.gameBoard, kingRank, kingFile)) {
				return true;
			}
		}

		return false;
	}
}