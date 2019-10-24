package chess;


public class Knight extends Piece {

    public Knight(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "N";
    }

    public Knight makeCopy(){
        return new Knight(this.color, this.rank, this.file);
    }

    public String toString() {
        return color.getColorDescription() + name;
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

        if (this.file.getValue() + 2 == file.getValue() && Math.abs(this.rank.getValue() - rank.getValue()) == 1){
            return true;
        }
        if (this.file.getValue() + 1 == file.getValue() && Math.abs(this.rank.getValue() - rank.getValue()) == 2) {
            return true;
        }
        if (this.file.getValue() - 1 == file.getValue() && Math.abs(this.rank.getValue() - rank.getValue()) == 2) {
            return true;
        }
        if (this.file.getValue() - 2 == file.getValue() && Math.abs(this.rank.getValue() - rank.getValue()) == 1) {
           return true;
        }
        return false;
    }
}