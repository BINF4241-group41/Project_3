package chess;


public class Tower extends Piece {
   
    private final String name;
    
    public Tower(Color color, Rank rank, File file){
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "T";
    }

    public String toString(){
        return color.getColorDescription() + name;
    }

    public Tower makeCopy() {
        return new Tower(this.color, this.rank, this.file);
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        if (rank != this.rank && file != this.file) {
            return false;
        }

        // vertical movement
        if (rank != this.rank) {
            int difference = this.rank.getValue() - rank.getValue();

            for (int i = 1; i < Math.abs(difference); ++i) {
                if (difference < 0) {
                    i = -i;
                }
                if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() + i), file)) {
                    return false;
                }
            }
            return true;
        }
        // horizontal movement
        else if (file != this.file) {
            int difference = this.file.getValue() - file.getValue();

            for (int i = 1; i < Math.abs(difference); ++i) {
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
