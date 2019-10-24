package chess;


public class Tower extends Piece {

    private boolean hasBeenMoved = false;

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

    public void movePiece(Rank rank, File file) {
        if (rank != null && file != null) {
            this.rank = rank;
            this.file = file;
            hasBeenMoved = true;
        }
    }

    public boolean hasBeenMoved() {
        return this.hasBeenMoved;
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {

        if (rank == null || file == null) {
            return false;
        }

        // origin == destination
        if (this.rank == rank && this.file == file) {
            return false;
        }

        // can't eat piece of same color
        if (gameBoard.isPositionOccupied(rank, file) && gameBoard.getPieceAtPosition(rank, file).getColor() == this.color) {
            return false;
        }

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
