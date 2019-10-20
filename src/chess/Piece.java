package chess;


public abstract class Piece {
	
	protected Color color;
	protected Square position;

	public void movePiece(Square position){
        this.position=position;
    }

    public File getFile() { return (position ? position.getFile() : null); }

	public Rank getRank() { return (position ? position.getRank() : null); }
}