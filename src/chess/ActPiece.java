package chess;

public interface ActPiece{

    public boolean isMovePossible(Square mov);
    public ActPiece makeCopy();
    public String toString();
}