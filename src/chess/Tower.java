package chess;


public class Tower extends Piece implements ActPiece{
   
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
        return new Tower(this.color, this.position);
    }

    public boolean isMovePossible(Square mov){
        if (mov.getRank() != position.getRank()) return false;
        if (mov.getFile() != position.getFile()) return false;
        return true;
    } 
}
