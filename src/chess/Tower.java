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

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        if (rank != position.getRank() && file != position.getFile()) {
            return false;
        }

        if (rank != position.getRank()) {
            int difference = position.getRank().getValue() - rank.getValue();

            for (int i = 1; i <= Math.abs(difference); ++i) {
                if (difference < 0) {
                    i = -i;
                }
                if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() + i), file)) {
                    return false;
                }
            }
            return true;
        }
        else if (file != position.getFile()) {
            int difference = position.getFile().getValue() - file.getValue();

            for (int i = 1; i <= Math.abs(difference); ++i) {
                if (difference < 0) {
                    i = -i;
                }
                if (gameBoard.isPositionOccupied(rank, File.valueOf(file.getValue() + i))) {
                    return false;
                }
            }
            return true;
        }
        // origin == destination
        else {
            return false;
        }
    } 
}
