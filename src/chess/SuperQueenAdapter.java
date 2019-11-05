package chess;


public class SuperQueenAdapter extends Piece {

    private SuperQueen superQueen;

    public SuperQueenAdapter(SuperQueen sq) {
        this.superQueen = sq;
    }


    @Override
    public void movePiece(Rank rank, File file) {
        superQueen.teleportPiece(rank, file);
    }

    @Override
    public void removePiece() {
        superQueen.capturePiece();
    }

    @Override
    public String getName() {
        return superQueen.getMyName();
    }

    @Override
    public Rank getRank() {
        return superQueen.getMyRank();
    }

    @Override
    public File getFile() {
        return superQueen.getMyFile();
    }

    @Override
    public Color getColor() {
        return superQueen.getMyColor();
    }


    public SuperQueenAdapter makeCopy() {
        SuperQueen queenCopy = new SuperQueen(superQueen.getMyColor(), superQueen.getMyRank(), superQueen.getMyFile());
        return new SuperQueenAdapter(queenCopy);
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        return superQueen.isTeleportAllowed(gameBoard, rank, file);
    }

    public String toString() {
        return superQueen.stringify();
    }
}
