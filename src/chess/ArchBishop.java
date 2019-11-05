package chess;


public class ArchBishop {

    private Color myColor;
    private Rank myRank;
    private File myFile;
    private String myName;


    public ArchBishop(Color color, Rank rank, File file) {
        this.myColor = color;
        this.myRank = rank;
        this.myFile = file;
        this.myName = "B";
    }


    public String getMyName() { return this.myName; }

    public Rank getMyRank() { return this.myRank; }

    public File getMyFile() { return this.myFile; }

    public Color getMyColor() { return this.myColor; }


    public void gallopToPosition(Rank rank, File file) {
        if (rank != null && file != null) {
            this.myRank = rank;
            this.myFile = file;
        }
    }

    public void capturePiece() {
        this.myRank = null;
        this.myFile = null;
    }


    public String stringify() {
        return myColor.getColorDescription() + myName;
    }


    public boolean isGallopAllowed(GameBoard gameBoard, Rank rank, File file) {

        if (rank == null || file == null) {
            return false;
        }

        // origin == destination
        if (this.myRank == rank && this.myFile == file) {
            return false;
        }

        // can't eat piece of same color
        if (gameBoard.isPositionOccupied(rank, file) && gameBoard.getPieceAtPosition(rank, file).getColor() == this.myColor) {
            return false;
        }

        if (validBishopMove(gameBoard, rank, file) || validKnightMove(gameBoard, rank, file)) {
            return true;
        }

        return false;
    }


    // returns true if the move pattern is valid for a bishop
    private boolean validBishopMove(GameBoard gameBoard, Rank rank, File file) {

        int rankDiff = this.myRank.getValue() - rank.getValue();
        int fileDiff = this.myFile.getValue() - file.getValue();

        if (Math.abs(rankDiff) != Math.abs(fileDiff)) {
            return false;
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

        return true;
    }


    // returns true if the move pattern is valid for a knight
    private boolean validKnightMove(GameBoard gameBoard, Rank rank, File file) {

        if (myFile.getValue() + 2 == file.getValue() && Math.abs(myRank.getValue() - rank.getValue()) == 1){
            return true;
        }
        if (myFile.getValue() + 1 == file.getValue() && Math.abs(myRank.getValue() - rank.getValue()) == 2) {
            return true;
        }
        if (myFile.getValue() - 1 == file.getValue() && Math.abs(myRank.getValue() - rank.getValue()) == 2) {
            return true;
        }
        if (myFile.getValue() - 2 == file.getValue() && Math.abs(myRank.getValue() - rank.getValue()) == 1) {
            return true;
        }
        return false;
    }
}
