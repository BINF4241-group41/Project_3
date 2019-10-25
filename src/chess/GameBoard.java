package chess;


public class GameBoard {

    private Square[][] gameBoard = new Square[8][8]; // rank x file (gameBoard[0][7] == 1h)


    public GameBoard() {

        for (int rankCount = 1; rankCount <= 8; ++rankCount) { // horizontal (1-8)
            for (int fileCount = 1; fileCount <= 8; ++fileCount) { // vertical (a-h)

                Color color;
                color = ((fileCount + rankCount) % 2 == 0) ? Color.BLACK : Color.WHITE;

                Rank rank = Rank.valueOf(rankCount); // 1 -> (Rank) 1, ...
                File file = File.valueOf(fileCount); // 1 -> a, 2 -> b, ...

                gameBoard[rankCount - 1][fileCount - 1] = new Square(color, rank, file);
            }
        }
    }

    public Square getSquareAtPosition(Rank rank, File file) {
        if (rank == null || file == null) {
            return null;
        }
        return gameBoard[rank.getValue() - 1][file.getValue() - 1].makeCopy();
    }

    public boolean isPositionOccupied(Rank rank, File file) {
        if (rank == null || file == null) {
            return false;
        }
        return gameBoard[rank.getValue() - 1][file.getValue() - 1].isOccupied();
    }

    public Piece getPieceAtPosition(Rank rank, File file) {
        if (rank == null || file == null) {
            return null;
        }
        if (!isPositionOccupied(rank, file)) {
            return null;
        }
        return gameBoard[rank.getValue() - 1][file.getValue() - 1].getPiece().makeCopy();
    }

    // p == null -> piece gets removed
    public void setPieceAtPosition(Piece p, Rank rank, File file) {
        if (p != null) {
            p.movePiece(rank, file);
        }
        gameBoard[rank.getValue() - 1][file.getValue() - 1].setPiece(p);
    }

    public GameBoard makeCopy() {
        GameBoard newBoard = new GameBoard();

        for (int rank = 7; rank >= 0; --rank) { // horizontal (1-8)
            for (int file = 0; file < 8; ++file) { // vertical (a-h)
                Piece p = (isPositionOccupied(Rank.valueOf(rank + 1),  File.valueOf(file + 1)) ? this.gameBoard[rank][file].getPiece().makeCopy() : null);
                newBoard.setPieceAtPosition(p, Rank.valueOf(rank + 1), File.valueOf(file + 1));
            }
        }

        return newBoard;
    }

    public String toString() {
        StringBuilder bld = new StringBuilder();
        for (int rank = 7; rank >= 0; --rank) { // horizontal (1-8)
            for (int file = 0; file < 8; ++file) { // vertical (a-h)
                bld.append("[");
                bld.append((gameBoard[rank][file].getPiece() != null ? gameBoard[rank][file].getPiece().toString() : ""));
                bld.append("]");
            }
            bld.append("\n");
        }
        return bld.toString();
    }
}