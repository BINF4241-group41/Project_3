package chess;


public class King extends Piece implements ActPiece{

    private final String name;
    private static final int MAX_MOV=1;

    public King(Color color, Square position){
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "K";
    }

    public King makeCopy() {
        return new King(this.color, this.position);
    }

    public String toString(){
		return "[" + color.getColorDescription() + name + "]";
    }

    public boolean isMovePossible(Square mov) {

        if (Math.abs(mov.getRank().getValue() - position.getRank().getValue()) > MAX_MOV) return false;
        if (Math.abs(mov.getFile().getValue() - position.getFile().getValue()) > MAX_MOV) return false;

        // TODO: Check boundaries.
        //reply TODO: you don't need to check the boundaries cause the input is limited

        return true;
    }
}