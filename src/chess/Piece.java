package chess;


public abstract class Piece {
	
	// color (enum?)
	protected Square position;
	
	
	public void movePiece();
	
	public boolean isMoveAllowed();
}