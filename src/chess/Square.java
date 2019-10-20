package chess;


public class Square {
	
	private final Color color;

	private final Rank rank;
	private final File file;
	
	private Piece piece = null;
	

	public Square(Color color, Rank rank, File file) {

		this.color = color;
		this.rank = rank;
		this.file = file;
	}

	public Piece getPiece() {
		return (this.piece != null ? this.piece.makeCopy() : null);
	}

	public void setPiece(Piece piece) {
		if (piece != null) {
			this.piece = piece.makeCopy();
		}
	}

	public void removePiece() {
		this.piece = null;
	}

	public Rank getRank() {
		return rank;
	}

	public File getFile() {
		return file;
	}

	public Square makeCopy() {
		return new Square(this.color, this.rank, this.file);
	}
}