package chess;


public class Pawn extends Piece {

    private final String name;

    public Pawn(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "P";
    }

    public String toString(){
        return color.getColorDescription() + name;
    }

    public Pawn makeCopy(){
        return new Pawn(this.color, this.rank, this.file);
    }


    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {

        if (rank == null || file == null) {
            return false;
        }

        if (this.color == Color.WHITE) {

            // diagonal moves
            if (this.file != file) {
                if (Math.abs(file.getValue() - this.file.getValue()) != 1 || rank.getValue() - this.rank.getValue() != 1) {
                    return false;
                } else if (gameBoard.isPositionOccupied(rank, file)) {
                    return true; // eat piece
                }
            }

            // two forward steps (allowed as first move)
            else if (rank.getValue() - this.rank.getValue() == 2) {
                if (this.rank.getValue() != 2) {
                    return false;
                }
                else if (!gameBoard.isPositionOccupied(rank, file) && !gameBoard.isPositionOccupied(rank, File.valueOf(file.getValue() - 1))) {
                    return true;
                }
            }

            // one step forward
            else if (rank.getValue() - this.rank.getValue() == 1) {
                if (!gameBoard.isPositionOccupied(rank, file)) {
                    return true;
                }
            }
        }

        else if (this.color == Color.BLACK) {

            // diagonal moves
            if (this.file != file) {
                if (Math.abs(file.getValue() - this.file.getValue()) != 1 || this.rank.getValue() - rank.getValue() != 1) {
                    return false;
                } else if (gameBoard.isPositionOccupied(rank, file)) {
                    return true; // eat piece
                }
            }

            // two forward steps (allowed as first move)
            else if (this.rank.getValue() - rank.getValue() == 2) {
                if (this.rank.getValue() != 7) {
                    return false;
                }
                else if (!gameBoard.isPositionOccupied(rank, file)) {
                    return true;
                }
            }

            // one step forward
            else if (this.rank.getValue() - rank.getValue() == 1) {
                if (!gameBoard.isPositionOccupied(rank, file)) {
                    return true;
                }
            }
        }

        return false; // origin == destination
    }
}