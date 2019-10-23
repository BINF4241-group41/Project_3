package chess;


public class Bishop extends Piece {

    public Bishop(Color color, Rank rank, File file){
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "B";
    }

    public Bishop makeCopy() {
        return new Bishop(this.color, this.rank, this.file);
    }

    public String toString(){
        return color.getColorDescription() + name;
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {

        int rankDiff = this.rank.getValue() - rank.getValue();
        int fileDiff = this.file.getValue() - file.getValue();

        if (Math.abs(rankDiff) != Math.abs(fileDiff)) {
            return false;
        }

        if (rankDiff == 0 && fileDiff == 0) {
            return false; // origin == destination
        }

        for (int i = 1; i < Math.abs(rankDiff); ++i) {

            if (rankDiff > 0) {
                if (fileDiff > 0) {
                    if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() + i), File.valueOf(file.getValue() + i))) {
                        return false;
                    }
                } else {
                    if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() + i), File.valueOf(file.getValue() - i))) {
                        return false;
                    }
                }
            } else {
                if (fileDiff > 0) {
                    if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() - i), File.valueOf(file.getValue() + i))) {
                        return false;
                    }
                } else {
                    if (gameBoard.isPositionOccupied(Rank.valueOf(rank.getValue() - i), File.valueOf(file.getValue() - i))) {
                        return false;
                    }
                }
            }
        }

        return false;
    }
}