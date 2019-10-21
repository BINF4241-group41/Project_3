package chess;


public class Bishop extends Piece {

    private final String name;

    public Bishop(Color color, Square position){
        this.color = color;
        this.position = (position != null ? position.makeCopy() : null);
        this.name = "B";
    }
    public Bishop makeCopy() {
        return new Bishop(this.color, this.position);
    }

    public String toString(){
        return "[" + color.getColorDescription() + name + "]";
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {

        int rankDiff = position.getRank().getValue() - rank.getValue();
        int fileDiff = position.getFile().getValue() - file.getValue();

        if (Math.abs(x) != Math.abs(y)) {
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

        return false;
    }
}