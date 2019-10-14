package chess;


public abstract class Piece {
	
	protected Color color;
	protected Square position;
	
	
	public abstract void movePiece();
	
	public abstract boolean isMoveAllowed();
	
	public abstract boolean makeCopy(); // copy of piece -> no reference
	
}