package chess;

public class Queen extends Piece {

    public Queen(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "Q";
    }

    public Queen makeCopy() {
        return new Queen(this.color, this.rank, this.file);
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

        int rankDiff = this.rank.getValue() - rank.getValue();
        int fileDiff = this.file.getValue() - file.getValue();

        if (Math.abs(rankDiff) != Math.abs(fileDiff) && rankDiff != 0 && fileDiff != 0) {
            return false;
        }

        // horizontal movement
        if (rankDiff == 0 && fileDiff != 0) {
            int minFile = Math.min(file.getValue(), this.file.getValue());

            for (int i = minFile + 1; i < Math.max(file.getValue(), this.file.getValue()); ++i) {
                if (gameBoard.isPositionOccupied(rank, File.valueOf(i))) {
                    return false;
                }
            }
            return true;
        }

        // vertical movement
        else if (rankDiff != 0 && fileDiff == 0) {
			
			int minRank = Math.min(rank.getValue(), this.rank.getValue());

            for (int i = minRank + 1; i < Math.max(rank.getValue(), this.rank.getValue()); ++i) {
                if (gameBoard.isPositionOccupied(Rank.valueOf(i), file)) {
                    return false;
                }
            }
            return true;
        }

        // diagonal movement
        else {
            for (int i = 1; i < Math.abs(rankDiff); ++i) {

                if (rankDiff > 0) {
                    if (fileDiff > 0) {
                        if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() + i), File.valueOf(file.getValue() + i))) {
                            return false;
                        }
                    }
                    else {
                        if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() + i), File.valueOf(file.getValue() - i))) {
                            return false;
                        }
                    }
                }

                else {
                    if (fileDiff > 0) {
                        if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() - i), File.valueOf(file.getValue() + i))) {
                            return false;
                        }
                    }
                    else {
                        if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() - i), File.valueOf(file.getValue() - i))) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

}