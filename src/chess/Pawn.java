package chess;


public class Pawn extends Piece implements ActPiece{

    private final String name;

    public Pawn(Color color, Square position) {
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "P";
    }

    public String toString(){
		return "[" + color.getColorDescription() + name + "]";
    }

    public Pawn makeCopy(){
        return new Pawn(this.color, this.position);
    }

    public boolean isMovePossible(Square mov) {
        if (Math.abs(mov.getFile().getValue() - position.getFile().getValue()) == 1) {
            if (mov.getRank() == position.getRank()) return true;
        }
        
        // TODO: Can eat enemy players pieces diagonally (Rank +/- 1, File + 1).

        return false;
    }
}