package chess;


public class Player {
	
	private Color color;
	
	private String name;
	
	private Piece[] pieces; // only active pieces?
	
	
	public Player(Color c, String playerName) {
		color = c;
		name = playerName;
	}
	
	public void movePiece(String moveDescription) {
		
	}
}