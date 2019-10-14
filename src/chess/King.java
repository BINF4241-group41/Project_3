package chess;


public class King extends Piece{
    private final String name;

    public King(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="K";
    }
    public boolean makeCopy(){
        return new King(this); //i've to check if it works or not
    }
}