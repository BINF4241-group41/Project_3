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

    public boolean isMoveAllowed(Square mov) {

        if (Math.abs(mov.getRank().getValue() - position.getRank().getValue()) > MAX_MOV) return false;
        if (Math.abs(mov.getFile().getValue() - position.getFile().getValue()) > MAX_MOV) return false;

        // TODO: Check boundaries.

        return true;
    }
}