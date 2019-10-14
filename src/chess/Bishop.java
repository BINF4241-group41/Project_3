package chess;

public class Bishop extends Piece implements ActPiece{

    private final String name;

    public Bishop(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="B";
    }
    public boolean makeCopy(){
        return new Bishop(this); //i've to check if it works or not
    }

    public String toString(){
		return "["+color.getColorDescription()+name+"]";
    }
    public boolean isMoveAllowed(Square mov){
        
    }
}