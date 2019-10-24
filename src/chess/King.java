package chess;


public class King extends Piece {

    private static final int MAX_MOV = 1;
    private boolean checked = false;

    public King(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "K";
    }

    public King makeCopy() {
        return new King(this.color, this.rank, this.file);
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

        if (Math.abs(rank.getValue() - this.rank.getValue()) > MAX_MOV) return false;
        if (Math.abs(file.getValue() - this.file.getValue()) > MAX_MOV) return false;

        return true;
    }


    public void resetCheck(King king) {
        this.checked = false;
    }

    public boolean canICastling(King king) {
        return true;
    }
}