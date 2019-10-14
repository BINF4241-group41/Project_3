package chess;

public interface ActPiece{

    public boolean isMoveAllowed(Square mov);
    public boolean makeCopy();
    public String toString();
}