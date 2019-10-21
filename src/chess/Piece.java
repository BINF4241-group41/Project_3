package chess;


public abstract class Piece {
	
	protected Color color;
	protected Rank rank;
	protected File file;

	public void movePiece(Rank rank, File file) {
		if (rank != null && file != null) {
			this.rank = rank;
			this.file = file;
		}
    }

	public Rank getRank() { return rank; }

    public File getFile() { return file; }

	public Color getColor() { return this.color; }

	public abstract Piece makeCopy();

	public abstract boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file); // rank, file encode destination; only checks for regular moves (no check, eating, ...)

	public abstract String toString();
}