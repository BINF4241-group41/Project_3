package chess;


public abstract class Piece {
	
	Color color;
	
	protected Square position;
	
	
	public void movePiece();
	
	public boolean isMoveAllowed();
}