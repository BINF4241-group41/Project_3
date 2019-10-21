package chess;


public class Pawn extends Piece {

    private final String name;

    public Pawn(Color color, Square position) {
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "P";
    }

    public String toString(){
		return "[" + color.getColorDescription() + name + "]";
    }

    public Pawn makeCopy(){
        return new Pawn(this.color, this.position);
    }


    public boolean isMovePossible(GameBoard gameBoard, Rank rank, File file) {

        if (rank == null || file == null) {
            return false;
        }

        if (this.color == Color.WHITE) {

            // reached border (should be promoted)
            if (rank.getValue() == 8) {
                return false;
            }

            // diagonal moves
            if (position.getFile() != file) {
                if (Math.abs(file - position.getFile()) != 1 || rank.getValue() - position.getRank().getValue() != 1) {
                    return false;
                } else if (gameBoard.isPositionOccupied(rank, file)) {
                    return true; // eat piece
                }
            }

            // two forward steps (allowed as first move)
            else if (rank.getValue() - position.getRank().getValue() == 2) {
                if (position.getRank.getValue() != 2) {
                    return false;
                }
                else if (!gameBoard.isPositionOccupied(rank, file) && !gameBoard.isPositionOccupied(rank, File.valueOf(file.getValue() - 1))) {
                    return true;
                }
            }

            // one step forward
            else if (rank.getValue() - position.getRank().getValue() == 1) {
                if (!gameBoard.isPositionOccupied(rank, file)) {
                    return true;
                }
            }
        }

        else if (this.color == COLOR.BLACK) {

            // reached border (should be promoted)
            if (rank.getValue() == 1) {
                return false;
            }

            // diagonal moves
            if (position.getFile() != file) {
                if (Math.abs(file - position.getFile()) != 1 || position.getRank().getValue() - rank.getValue() != 1) {
                    return false;
                } else if (gameBoard.isPositionOccupied(rank, file)) {
                    return true; // eat piece
                }
            }

            // two forward steps (allowed as first move)
            else if (position.getRank().getValue() - rank.getValue() == 2) {
                if (position.getRank.getValue() != 7) {
                    return false;
                }
                else if (!gameBoard.isPositionOccupied(rank, file)) {
                    return true;
                }
            }

            // one step forward
            else if (position.getRank().getValue() - rank.getValue() == 1) {
                if (!gameBoard.isPositionOccupied(rank, file)) {
                    return true;
                }
            }
        }

        return false;
    }
}