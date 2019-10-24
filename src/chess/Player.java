package chess;

import java.util.ArrayList;


public class Player {
	
	private Color color;
	
	private String name;
	
	private ArrayList<Piece> activePieces = new ArrayList<>(); // active pieces

	private ArrayList<Piece> eatenPieces = new ArrayList<>(); // eaten pieces


	public String getName() {
		return name;
	}

	public Player(Color c, String playerName) {
		color = c;
		name = playerName;
	}

	public void addPiece(Piece p) {
		if (p != null) {
			activePieces.add(p.makeCopy());
		}
	}

	public boolean eatPiece(Rank rank, File file) {

		Piece matchedPiece = null;

		// find piece at this position
		for (Piece p : activePieces) {
			if (p.getFile() == file && p.getRank() == rank) {
				matchedPiece = p;
				break;
			}
		}

		boolean wasRemoved = activePieces.remove(matchedPiece);

		// successfully removed
		if (wasRemoved) {
			eatenPieces.add(matchedPiece);
			matchedPiece.removePiece();
		}
		return wasRemoved;
	}

	public ArrayList<Piece> getActivePieces() {
		ArrayList<Piece> piecesCopies = new ArrayList<>();

		for (Piece p : activePieces) {
			piecesCopies.add(p.makeCopy());
		}

		return piecesCopies;
	}

	public boolean movePiece(Piece p, Rank rank, File file) {

		if (p == null || p.getColor() != this.color || rank == null || file == null) {
			return false;
		}

		Piece matchedPiece = null;

		for (Piece activePiece : activePieces) {
			if (p.getFile() == activePiece.getFile() && p.getRank() == activePiece.getRank()) {
				matchedPiece = activePiece;
				break;
			}
		}

		if (matchedPiece != null) {
			matchedPiece.movePiece(rank, file);
		}

		return (matchedPiece != null);
	}
}