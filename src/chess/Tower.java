package chess;


public class Tower extends Piece {
   
    private final String name;
    
    public Tower(Color color, Square position){
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "T";
    }

    public String toString(){
        return "[" + color.getColorDescription() + name + "]";
    }

    public Tower makeCopy() {
        return new Tower (this.color, this.position);
    }

    public boolean isMoveisMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        if (rank != position.getRank()) return false;
        if (file != position.getFile()) return false;
        return true;
    } 
}
