package chess;

public class King extends Piece implements ActPiece{

    private final String name;
    private static final int MAX_MOV=1;

    public King(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="K";
    }
    public boolean makeCopy(){
        return new King(this); //i've to check if it works or not
    }

    public String toString(){
		return "["+color.getColorDescription()+name+"]";
    }
    public boolean isMoveAllowed(Square mov){
      
        if(! position.getRank().sameRank(mov.getRank()))return false;
       if(! position.getFile().sameFile(mov.getFile()))return false;

       if(mov.getRank().distance(position.getRank())>MAX_MOV) return false;
       if(mov.getFile().distance(position.getFile())>MAX_MOV) return false; 
       return true;
    }
}