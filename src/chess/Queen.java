package chess;

public class Queen extends Piece {

    private final String name;

    public Queen(Color color, Square position){
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "Q";
    }
    public Queen makeCopy(){
        return new Queen(this.color, this.position);
    }

    public String toString(){
		return "[" + color.getColorDescription() + name + "]";
    }

    public boolean isMovePossible(Square mov){ //bishop+tower
        int y = position.getRank().getValue() - mov.getRank().getValue(); // |x-x1|=|y-y1|
        int x = position.getFile().getValue() - mov.getFile().getValue();
        if (Math.abs(x)==Math.abs(y))return true; //check the peices between
        if(mov.getRank() != position.getRank())return false;
        if(mov.getFile() != position.getFile())return false;
        return true;
    }

}