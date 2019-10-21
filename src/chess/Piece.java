package chess;


public abstract class Piece {
	
	protected Color color;
	protected Square position;

	public void movePiece(Square position) {
		this.position = position.makeCopy();
    }

    public File getFile() { return (position != null ? position.getFile() : null); }

	public Rank getRank() { return (position != null ? position.getRank() : null); }

	public abstract Piece makeCopy();

	public abstract boolean isMovePossible(Square s);

	public abstract String toString();
}