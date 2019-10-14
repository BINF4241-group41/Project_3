package chess;

public class Tower extends Piece implements ActPiece{
   
    private final String name;
    
    public Tower(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="T";
    }
    public String toString(){
		return "["+color.getColorDescription()+name+"]";
    }
    public boolean makeCopy(){
        return new Tower(this); //i've to check if it works or not
    }
    public boolean isMoveAllowed(Square mov){
        if(! mov.getRank().sameRank(position.getRank()))return false;
        if(! mov.getFile().sameFile(position.getFile()))return false;
        //check if there're any pieces between
        return true;
    } 
}
