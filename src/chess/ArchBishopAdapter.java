package chess;


public class ArchBishopAdapter extends Piece {

    private ArchBishop archBishop;

    public ArchBishopAdapter(ArchBishop ab) {
        this.archBishop = ab;
    }


    @Override
    public void movePiece(Rank rank, File file) {
        archBishop.gallopToPosition(rank, file);
    }

    @Override
    public void removePiece() {
        archBishop.capturePiece();
    }

    @Override
    public String getName() {
        return archBishop.getMyName();
    }

    @Override
    public Rank getRank() {
        return archBishop.getMyRank();
    }

    @Override
    public File getFile() {
        return archBishop.getMyFile();
    }

    @Override
    public Color getColor() {
        return archBishop.getMyColor();
    }


    public ArchBishopAdapter makeCopy() {
        ArchBishop bishopCopy = new ArchBishop(archBishop.getMyColor(), archBishop.getMyRank(), archBishop.getMyFile());
        return new ArchBishopAdapter(bishopCopy);
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        return archBishop.isGallopAllowed(gameBoard, rank, file);
    }

    public String toString() {
        return archBishop.stringify();
    }
}
