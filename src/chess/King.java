package chess;


public class King extends Piece implements ActPiece{

    private final String name;

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
       
        boolean ok=false;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){

            }

        }
    }
}