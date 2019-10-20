package chess;

public class Pawn extends Piece implements ActPiece{

    private final String name;

    public Pawn(Color color, Square position) {
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

    public boolean isMovePossible(Square mov) {
        if (Math.abs(mov.getFile().getValue() - position.getFile().getValue()) == 1) {
            if (mov.getRank() == position.getRank()) return true;
        }
        
        // TODO: Can eat enemy players pieces diagonally (Rank +/- 1, File + 1).

        return false;
    }
}