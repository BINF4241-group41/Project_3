package chess;


public abstract class Piece {
	
	protected Color color;
	
	protected Square position;
	
	
	public void movePiece();
	
	public boolean isMoveAllowed();
	
	public boolean makeCopy(); // copy of piece -> no reference
}