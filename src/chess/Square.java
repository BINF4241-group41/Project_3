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