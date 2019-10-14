package chess;

public class Pawn extends Piece implements ActPiece{

    private final String name;

    public Pawn(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="P";
    }
    public String toString(){
		return "["+color.getColorDescription()+name+"]";
    }
    public boolean makeCopy(){
        return new Pawn(this); //i've to check if it works or not
    }
    public boolean isMoveAllowed(Square mov){
        if(mov.getFile().distance(position.getFile())==1){
            if(mov.getRank().sameRank(position.getRank()))return true;
        }
        return false;
    }
}