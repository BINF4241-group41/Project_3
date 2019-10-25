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
            int minRank = Math.min(rank.getValue(), this.rank.getValue());

            for (int i = minRank + 1; i < Math.max(rank.getValue(), this.rank.getValue()); ++i) {
                if (gameBoard.isPositionOccupied(Rank.valueOf(i), file)) {
                    return false;
                }
            }
            return true;
        }
        // horizontal movement
        else if (file != this.file) {
            int minFile = Math.min(file.getValue(), this.file.getValue());

            for (int i = minFile + 1; i < Math.max(file.getValue(), this.file.getValue()); ++i) {
                if (gameBoard.isPositionOccupied(rank, File.valueOf(i))) {
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
