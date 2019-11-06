package chess;

import java.util.ArrayList;
import java.util.Iterator;


public class Game {
	
	private GameBoard gameBoard;
	
	private Player whitePlayer;
	private Player blackPlayer;
	private static Game game= null;
	private Player winner = null;
	private CollectionPiece iterator=null;
	private Player nextPlayer; // can play next move

	private Pawn pawnEligibleForEnPassant = null;

	public static Game getInstance(String[] playerNames) {
		if(game==null) game=new Game(playerNames);
		return game;
	}

	public boolean isFinished() {
		return (winner != null);
	}

	public String getNextPlayerName() {
		return nextPlayer.getName();
	}

	public String getWinnerName() {
		return (winner != null ? winner.getName() : null);
	}

	private Game(String[] playerNames) {
		
		whitePlayer = new Player(Color.WHITE, (playerNames.length > 0 ? playerNames[0] : "white"));
		blackPlayer = new Player(Color.BLACK, (playerNames.length > 1 ? playerNames[1] : "black"));

		gameBoard = new GameBoard();
		iterator=new CollectionPiece();
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

		//Bishop whiteBishop = new Bishop(Color.WHITE, Rank.valueOf(1), File.valueOf(3));
		ArchBishop whiteArchBishop = new ArchBishop(Color.WHITE, Rank.valueOf(1), File.valueOf(3));
		ArchBishopAdapter whiteBishop = new ArchBishopAdapter(whiteArchBishop);
		gameBoard.setPieceAtPosition(whiteBishop.makeCopy(), Rank.valueOf(1), File.valueOf(3));
		whitePlayer.addPiece(whiteBishop);
		//whiteBishop = new Bishop(Color.WHITE, Rank.valueOf(1), File.valueOf(6));
		whiteArchBishop = new ArchBishop(Color.WHITE, Rank.valueOf(1), File.valueOf(6));
		whiteBishop = new ArchBishopAdapter(whiteArchBishop);
		gameBoard.setPieceAtPosition(whiteBishop.makeCopy(), Rank.valueOf(1), File.valueOf(6));
		whitePlayer.addPiece(whiteBishop);

		//Bishop blackBishop = new Bishop(Color.BLACK, Rank.valueOf(8), File.valueOf(3));
		ArchBishop blackArchBishop = new ArchBishop(Color.BLACK, Rank.valueOf(8), File.valueOf(3));
		ArchBishopAdapter blackBishop = new ArchBishopAdapter(blackArchBishop);
		gameBoard.setPieceAtPosition(blackBishop.makeCopy(), Rank.valueOf(8), File.valueOf(3));
		blackPlayer.addPiece(blackBishop);
		//blackBishop = new Bishop(Color.BLACK, Rank.valueOf(8), File.valueOf(6));
		blackArchBishop = new ArchBishop(Color.BLACK, Rank.valueOf(8), File.valueOf(6));
		blackBishop = new ArchBishopAdapter(blackArchBishop);
		gameBoard.setPieceAtPosition(blackBishop.makeCopy(), Rank.valueOf(8), File.valueOf(6));
		blackPlayer.addPiece(blackBishop);

		//Queen whiteQueen = new Queen(Color.WHITE, Rank.valueOf(1), File.valueOf(4)); // old code
		SuperQueen whiteSuperQueen = new SuperQueen(Color.WHITE, Rank.valueOf(1), File.valueOf(4));
		SuperQueenAdapter whiteQueen = new SuperQueenAdapter(whiteSuperQueen);
		gameBoard.setPieceAtPosition(whiteQueen.makeCopy(), Rank.valueOf(1), File.valueOf(4));
		whitePlayer.addPiece(whiteQueen);

		//Queen blackQueen = new Queen(Color.BLACK, Rank.valueOf(8), File.valueOf(4));
		SuperQueen blackSuperQueen = new SuperQueen(Color.BLACK, Rank.valueOf(8), File.valueOf(4));
		SuperQueenAdapter blackQueen = new SuperQueenAdapter(blackSuperQueen);
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
			if (moveDescription.matches("[a-h]?[27]?x?[a-h][18][TNBQ]") && !moveDescription.matches("[18]x?[a-h][18][TNBQ]")) {
				return true;
			}
			// king-/ queenside castling
			if (moveDescription.equals("0-0") || moveDescription.equals("0-0-0")) {
				return true;
			}
			// check
			if (moveDescription.matches("[TNBQK]?[a-h]?[1-8]?x?[a-h][1-8][+]") && !moveDescription.matches("[TNBQK]?[1-8]x?[a-h][1-8][+]")) {
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
		Rank inputRank = Rank.valueOf(Integer.valueOf(moveDescription.substring(moveDescription.length() - 1))); // last character
		File inputFile = File.fromString(moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1)); // 2nd last character

		if (pawnEligibleForEnPassant != null && pieceString == "P") {
			if (pawnEligibleForEnPassant.getRank() == Rank.valueOf(5)) {
				gameBoard.setPieceAtPosition(pawnEligibleForEnPassant, Rank.valueOf(pawnEligibleForEnPassant.getRank().getValue() + 1), pawnEligibleForEnPassant.getFile());
			}
			else if (pawnEligibleForEnPassant.getRank() == Rank.valueOf(4)) {
				gameBoard.setPieceAtPosition(pawnEligibleForEnPassant, Rank.valueOf(pawnEligibleForEnPassant.getRank().getValue() - 1), pawnEligibleForEnPassant.getFile());
			}
		}

		// remove pieces which can't make this move
		//Iterator<Piece> iterator = matchedPieces.iterator();
		iterator.get(matchedPieces);

		while (iterator.hasNext()) {
			if (!iterator.next().isMoveAllowed(this.gameBoard, inputRank, inputFile)) {
				iterator.remove();
			}
		}

		if (pawnEligibleForEnPassant != null && pieceString == "P") {
			if (pawnEligibleForEnPassant.getRank() == Rank.valueOf(6)) {
				gameBoard.setPieceAtPosition(pawnEligibleForEnPassant, Rank.valueOf(pawnEligibleForEnPassant.getRank().getValue() -1), pawnEligibleForEnPassant.getFile());
			}
			else if (pawnEligibleForEnPassant.getRank() == Rank.valueOf(3)) {
				gameBoard.setPieceAtPosition(pawnEligibleForEnPassant, Rank.valueOf(pawnEligibleForEnPassant.getRank().getValue() + 1), pawnEligibleForEnPassant.getFile());
			}
		}

		// no pieces could be matched
		if (matchedPieces.isEmpty()) {
			return null;
		}

		if (matchedPieces.size() > 1) {

			if (moveDescription.contains("x")) {
				iterator.get(matchedPieces);

				while (iterator.hasNext()) {
					Piece nextPiece = iterator.next();
					if (gameBoard.isPositionOccupied(inputRank, inputFile)) {
						continue;
					}
					else if (isEnPassant(nextPiece)) {
						continue;
					}
					else {
						iterator.remove();
					}
				}
			}

			File originFile = null;
			Rank originRank = null;

			int startIndex = (moveDescription.matches("[TNBQK].*") ? 1 : 0); // if first char is piecetype, ignore
			int endIndex = (moveDescription.contains("x") ? moveDescription.length() - 3 : moveDescription.length() - 2); // ignore destination and x if set

			String originString = moveDescription.substring(startIndex, endIndex);

			// insufficient information -> multiple matches
			if (originString.length() == 0) {
				return null;
			}
			else if (originString.length() == 1) {
				originFile = File.fromString(originString);
			}
			else if (originString.length() == 2) {
				originFile = File.fromString(originString.substring(0, originString.length() - 1));
				originRank = Rank.valueOf(Integer.valueOf(originString.substring(originString.length() - 1)));
			}

			iterator.get(matchedPieces);

			while (iterator.hasNext()) {
				Piece nextPiece = iterator.next();
				if (nextPiece.getFile() != originFile) {
					iterator.remove();
				}
				if (originRank != null && nextPiece.getRank() != originRank) {
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

		boolean isPromotion = moveDescription.matches(".*[TNBQ][+]?");
		boolean isCheck = moveDescription.matches(".*[+]");
		
		String promotionString = "";

		if (isPromotion || isCheck) {
			if (isCheck) {
				moveDescription = moveDescription.substring(0, moveDescription.length() - 1);
			}
			if (isPromotion) {
				promotionString = moveDescription.substring(moveDescription.length() - 1);
				moveDescription = moveDescription.substring(0, moveDescription.length() - 1);
			}
		}

		// castling
		if (moveDescription.contains("0-0")) {

			if (isCheck(gameBoard, nextPlayer)) {
				return false;
			}

			ArrayList<Piece> pieces = nextPlayer.getActivePieces();
			King king = null;
			Tower tower = null;

			for (Piece p : pieces) {
				if (p.getName().equals("K")) {
					king = (King) p;
				}
				else if (p.getName().equals("T") && p.getFile() == File.valueOf(8) && moveDescription.equals("0-0")) {
					tower = (Tower) p; // kingside
				}
				else if (p.getName().equals("T") && p.getFile() == File.valueOf(1) && moveDescription.equals("0-0-0")) {
					tower = (Tower) p; // queenside
				}
			}

			if (king != null && tower != null && !isCheck(this.gameBoard, this.nextPlayer)) {
				if (king.canCastle(gameBoard.makeCopy(), tower)) {

					if (moveDescription.equals("0-0")) { // kingside
						gameBoard.setPieceAtPosition(null, king.getRank(), File.valueOf(5));
						gameBoard.setPieceAtPosition(null, tower.getRank(), File.valueOf(8));
						nextPlayer.movePiece(king, king.getRank(), File.valueOf(7));
						nextPlayer.movePiece(tower, tower.getRank(), File.valueOf(6));
						gameBoard.setPieceAtPosition(king.makeCopy(), king.getRank(), File.valueOf(7));
						gameBoard.setPieceAtPosition(tower.makeCopy(), tower.getRank(), File.valueOf(6));
					}
					else if (moveDescription.equals("0-0-0")) { // queenside
						gameBoard.setPieceAtPosition(null, king.getRank(), File.valueOf(5));
						gameBoard.setPieceAtPosition(null, tower.getRank(), File.valueOf(1));
						nextPlayer.movePiece(king, king.getRank(), File.valueOf(3));
						nextPlayer.movePiece(tower, tower.getRank(), File.valueOf(4));
						gameBoard.setPieceAtPosition(king.makeCopy(), king.getRank(), File.valueOf(3));
						gameBoard.setPieceAtPosition(tower.makeCopy(), tower.getRank(), File.valueOf(4));
					}
				}
				else {
					return false;
				}
			}

			nextPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);

			// check if next player has lost
			if (isCheckmate(nextPlayer)) {
				this.winner = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
			}

			pawnEligibleForEnPassant = null;
			return true;
		}

		Piece piece = identifyPiece(moveDescription);

		if (piece == null) {
			return false;
		}

		Rank inputRank = Rank.valueOf(Integer.parseInt(moveDescription.substring(moveDescription.length() - 1))); // last character
		File inputFile = File.fromString(moveDescription.substring(moveDescription.length() - 2, moveDescription.length() - 1)); // 2nd last character
		
		// move would result in check for nextPlayer -> other player can capture king
		if (wouldResultInSelfCheck(gameBoard, piece, inputRank, inputFile)) {
			return false;
		}
		
		// check if move would result in check
		if (isPromotion) {
			
			Piece newPiece = null;
			if (promotionString.equals("Q")) {
				newPiece = new Queen(piece.getColor(), inputRank, inputFile);
			}
			else if (promotionString.equals("T")) {
				newPiece = new Tower(piece.getColor(), inputRank, inputFile);
			} 
			else if (promotionString.equals("N")) {
				newPiece = new Knight(piece.getColor(), inputRank, inputFile);
			}
			else if (promotionString.equals("B")) {
				newPiece = new Bishop(piece.getColor(), inputRank, inputFile);
			}
			
			GameBoard boardCopy = this.gameBoard.makeCopy();
			boardCopy.setPieceAtPosition(newPiece.makeCopy(), inputRank, inputFile);
			
			if (wouldResultInCheck(boardCopy, piece, inputRank, inputFile) && !isCheck) {
				return false;
			}
			if (!wouldResultInCheck(boardCopy, piece, inputRank, inputFile) && isCheck) {
				return false;
			}
		}
		else {
			if (wouldResultInCheck(gameBoard, piece, inputRank, inputFile) && !isCheck) {
				return false;
			}
			if (!wouldResultInCheck(gameBoard, piece, inputRank, inputFile) && isCheck) {
				return false;
			}
		}

		// Piece gets eaten by piece who lands there.
		if (gameBoard.getPieceAtPosition(inputRank, inputFile) != null) {
			if (!moveDescription.contains("x")) { // capture wasn't indicated
				return false;
			}

			Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
			otherPlayer.eatPiece(inputRank, inputFile);
			gameBoard.setPieceAtPosition(null, inputRank, inputFile); // explicitly remove it
		}
		
		// Piece gets eaten en passant.
		else if (isEnPassant(piece)) {
			if (!moveDescription.contains("x")) {
				return false;
			}

			Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
			otherPlayer.eatPiece(pawnEligibleForEnPassant.getRank(), pawnEligibleForEnPassant.getFile());
			gameBoard.setPieceAtPosition(null, pawnEligibleForEnPassant.getRank(), pawnEligibleForEnPassant.getFile()); // explicitly remove it
		}

		// Promotion
		if (isPromotion) {
			executePromotion(promotionString, piece, inputRank, inputFile);
		}

		else {
			gameBoard.setPieceAtPosition(null, piece.getRank(), piece.getFile());
			nextPlayer.movePiece(piece, inputRank, inputFile);
			gameBoard.setPieceAtPosition(piece.makeCopy(), inputRank, inputFile);
		}
		
		if (piece.getName() == "P" && Math.abs(piece.getRank().getValue() - inputRank.getValue()) == 2) {
			this.pawnEligibleForEnPassant = (Pawn) piece;
		}
		else {
			this.pawnEligibleForEnPassant = null;
		}
		
		nextPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);

		// check if next player has lost
		if (isCheckmate(nextPlayer)) {
			this.winner = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
		}

		return true;
	}


	private boolean isEnPassant(Piece p) {
		if (pawnEligibleForEnPassant == null || p == null || p.getName() != "P") {
			return false;
		}
		return (p.getRank() == pawnEligibleForEnPassant.getRank() || Math.abs(pawnEligibleForEnPassant.getRank().getValue() - p.getRank().getValue()) == 1);
	}


	// For pawn p, promote.
	private void executePromotion(String promotionPieceName, Piece piece, Rank rank, File file) {

		Piece newPiece = null;

		if (promotionPieceName.equals("Q")) {
			newPiece = new Queen(piece.getColor(), rank, file);
		}
		else if (promotionPieceName.equals("T")) {
			newPiece = new Tower(piece.getColor(), rank, file);
		} 
		else if (promotionPieceName.equals("N")) {
			newPiece = new Knight(piece.getColor(), rank, file);
		}
		else if (promotionPieceName.equals("B")) {
			newPiece = new Bishop(piece.getColor(), rank, file);
		}
		else {
			return; // error?
		}

		this.nextPlayer.removePiece(piece);
		this.gameBoard.setPieceAtPosition(null, rank, file);
		this.nextPlayer.addPiece(newPiece);
		this.gameBoard.setPieceAtPosition(newPiece, rank, file);
	}
	

	public String toString() {
		return gameBoard.toString();
	}


	// Returns true if executing this move for nextPlayer would result in a checkmate for himself.
	private boolean wouldResultInSelfCheck(GameBoard gameBoard, Piece piece, Rank rank, File file) {
		if (piece == null) {
			return false;
		}

		GameBoard boardCopy = gameBoard.makeCopy();
		boardCopy.setPieceAtPosition(null, piece.getRank(), piece.getFile());
		boardCopy.setPieceAtPosition(piece.makeCopy(), rank, file);
		
		// nextPlayer is checked
		if (isCheck(boardCopy, nextPlayer)) {
			return true;
		}
		return false;
	}
	
	
	// Returns true if executing this move would set other player in check.
	private boolean wouldResultInCheck(GameBoard gameBoard, Piece piece, Rank rank, File file) {
		
		gameBoard = gameBoard.makeCopy();
		
		if (isEnPassant(piece)) {
			gameBoard.setPieceAtPosition(null, pawnEligibleForEnPassant.getRank(), pawnEligibleForEnPassant.getFile()); // explicitly remove it
		}

		else {
			gameBoard.setPieceAtPosition(null, piece.getRank(), piece.getFile());
			gameBoard.setPieceAtPosition(piece.makeCopy(), rank, file);
		}
		
		Player otherPlayer = (nextPlayer == whitePlayer ? blackPlayer : whitePlayer);
		return isCheck(gameBoard, otherPlayer);
	}


	// Returns true if Player p is in checkmate situation (can still move) -> p has lost.
	private boolean isCheckmate(Player player) {
		if (!isCheck(this.gameBoard, player)) {
			return false;
		}

		ArrayList<Piece> playerPieces = player.getActivePieces();

		// match piece type
		for (Piece p : playerPieces) {
			
			for (int rankCount = 1; rankCount <= 8; ++rankCount) { // horizontal (1-8)
				for (int fileCount = 1; fileCount <= 8; ++fileCount) { // vertical (a-h)
					
					if (p.isMoveAllowed(gameBoard, Rank.valueOf(rankCount), File.valueOf(fileCount))) {
						GameBoard boardCopy = this.gameBoard.makeCopy();
						boardCopy.setPieceAtPosition(p.makeCopy(), Rank.valueOf(rankCount), File.valueOf(fileCount));
						
						if (!isCheck(boardCopy, player)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

	// Returns true if Player player is in check situation for specific board arrangement.
	private boolean isCheck(GameBoard board, Player player) {

		Rank kingRank = null;
		File kingFile = null;

		ArrayList<Piece> playerPieces = new ArrayList<Piece>();
		ArrayList<Piece> otherPlayerPieces = new ArrayList<Piece>();

		for (int rank = 8; rank >= 1; --rank) {
			for (int file = 1; file <= 8; ++file) {
				if (board.isPositionOccupied(Rank.valueOf(rank), File.valueOf(file))) {
					Piece p = board.getPieceAtPosition(Rank.valueOf(rank), File.valueOf(file));
					if (p.getColor() == player.getColor()) {
						playerPieces.add(p);
					}
					else {
						otherPlayerPieces.add(p);
					}
				}
			}
		}

		// match piece type
		for (Piece p : playerPieces) {
			if (p.getName() != null && p.getName().equals("K")) {
				kingRank = p.getRank();
				kingFile = p.getFile();
			}
		}
		
		// match piece type
		for (Piece p : otherPlayerPieces) {
			if (p.isMoveAllowed(board, kingRank, kingFile)) {
				return true;
			}
		}

		return false;
	}
}