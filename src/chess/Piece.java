package chess;


public abstract class Piece {
	
	protected Color color;
	protected Square position;

	public void movePiece(Square position){
        this.position=position;
    }
}