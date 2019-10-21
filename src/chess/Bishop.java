package chess;


public class Bishop extends Piece {

    private final String name;

    public Bishop(Color color, Square position){
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "B";
    }
    public Bishop makeCopy() {
        return new Bishop(this.color, this.position);
    }

    public String toString(){
        return "[" + color.getColorDescription() + name + "]";
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        int y = position.getRank().getValue() - rank.getValue(); // |x-x1|=|y-y1|
        int x = position.getFile().getValue() - file.getValue();
        if (Math.abs(x) == Math.abs(y)) return true; //check the peices between
        return false;
    }
}