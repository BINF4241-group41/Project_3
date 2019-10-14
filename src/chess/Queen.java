package chess;

public class Queen extends Piece implements ActPiece{

    private final String name;

    public Queen(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="Q";
    }
    public boolean makeCopy(){
        return new Queen(this); //i've to check if it works or not
    }

    public String toString(){
		return "["+color.getColorDescription()+name+"]";
    }

    public boolean isMoveAllowed(Square mov){
        int diff=position.getFile().distance(mov.getFile());
        
    }

}