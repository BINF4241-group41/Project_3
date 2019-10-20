package chess;

import java.util.ArrayList;


public class Player {
	
	private Color color;
	
	private String name;
	
	private ArrayList<Piece> activePieces = new ArrayList<Piece>(); // active pieces

	private ArrayList<Piece> eatenPieces = new ArrayList<Piece>(); // eaten pieces
	
	
	public Player(Color c, String playerName) {
		color = c;
		name = playerName;
	}

	public void addPiece(Piece p) {
		activePieces.add(p);
	}

	public boolean eatPiece(Piece p) {
		boolean wasRemoved = activePieces.remove(p);
		if (wasRemoved) {
			eatenPieces.add(p);
		}
		return wasRemoved;
	}
	
	public void movePiece(Piece p, Rank r, File f) {
		
	}
}