package chess;


public enum Color {
	WHITE("W"),
	BLACK("B");

	private final String colorDescription;
	
	private Color (String value) {
	    colorDescription = value;
	}
	
	public String getColorDescription() {
	    return colorDescription;
	}
}