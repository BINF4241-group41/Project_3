package chess;


public class Knight extends Piece {

    private final String name;

    public Knight(Color color, Square position) {
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "N";
    }
    public Knight makeCopy(){
        return new Knight(this.color, this.position);
    }

    public String toString(){
		return "[" + color.getColorDescription() + name + "]";
    }

    public boolean isMovePossible(Square mov){
        if (position.getFile().getValue() + 2 == mov.getFile().getValue()){
            if (Math.abs(position.getRank().getValue() - mov.getRank().getValue()) == 1) return true;
        }
        if (position.getFile().getValue() + 1 == mov.getFile().getValue()) {
            if (Math.abs(position.getRank().getValue() - mov.getRank().getValue()) == 2) return true;
        }
        if (position.getFile().getValue() - 1 == mov.getFile().getValue()) {
            if (Math.abs(position.getRank().getValue() - mov.getRank().getValue()) == 2) return true;
        }
        if (position.getFile().getValue() - 2 == mov.getFile().getValue()) {
            if (Math.abs(position.getRank().getValue() - mov.getRank().getValue()) == 1)return true;
        }
        return false;
    }
}