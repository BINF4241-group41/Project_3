package chess;

public interface ActPiece{

    public boolean isMovePossible(Square mov);
    public boolean makeCopy();
    public String toString();
}