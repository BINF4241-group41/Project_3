package chess;


public class Square {
	
	private final Color color;

	// TODO: Implement file, rank in better way

	private final Rank rank; // horizontal row, labeled 1-8, starting from white's side of the board
	private final File file; // vertical column, labeled a-h, starting from left (white's view)
	
	private Piece piece = null;
	

	public Square(Color color, Rank rank, File file) {

		this.color = color;
		this.rank = rank;
		this.file = file;
	}

	public void getPiece() {
		return this.piece.makeCopy();
	}

	public void setPiece(Piece piece) {
		this.piece = piece.makeCopy();
	}

	public void removePieve() {
		this.piece = null;
	}

	public Rank getRank() {
		return rank;
	}

	public File getFile() {
		return file;
	}
}