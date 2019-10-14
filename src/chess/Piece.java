package chess;


public abstract class Piece {
	
	protected Color color;
	protected Square position;

	public void movePiece(Square position){
        this.position=position;
    }
	public abstract boolean isMoveAllowed();
	
	public abstract boolean makeCopy(); // copy of piece -> no reference

	public String toString(){
		return "["+color+name+"]"; //i'm not sure about what return color, Ill check later
	}
}