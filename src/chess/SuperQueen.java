package chess;


public class SuperQueen {

    private Color myColor;
    private Rank myRank;
    private File myFile;
    private String myName;


    public SuperQueen(Color color, Rank rank, File file) {
        this.myColor = color;
        this.myRank = rank;
        this.myFile = file;
        this.myName = "Q";
    }


    public String getMyName() { return this.myName; }

    public Rank getMyRank() { return this.myRank; }

    public File getMyFile() { return this.myFile; }

    public Color getMyColor() { return this.myColor; }


    public void teleportPiece(Rank rank, File file) {
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


    public boolean isTeleportAllowed(GameBoard gameBoard, Rank rank, File file) {

        if (rank == null || file == null) {
            return false;
        }

        // target not free
        if (gameBoard.isPositionOccupied(rank, file)) {
            return false;
        }

        return true;
    }
}
