package chess;


public class Square {
	
	private Color color;
	
	// position (might not be implemented)
	
	private Piece piece;
	
	
	public Square(Color c) {
		color = c;
	}
	
	public Square(Color c, Piece p) {
		color = c;
		piece = makeCopy(p);
	}
}