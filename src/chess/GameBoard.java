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
            return false;
        }
        return gameBoard[rank.getValue()][file.getValue()].makeCopy();
    }

    public boolean isPositionOccupied(Rank rank, File file) {
        if (rank == null || file == null) {
            return false;
        }
        return gameBoard[rank.getValue()][file.getValue()].isOccupied();
    }

    public Piece getPieceAtPosition(Rank rank, File file) {
        if (rank == null || file == null) {
            return false;
        }
        gameBoard[rank.getValue()][file.getValue()].getPiece();
    }

    // p == null -> piece gets removed
    public boolean setPieceAtPosition(Piece p, Rank rank, File file) {
        if (rank == null || file == null) {
            return false;
        }
        gameBoard[rank.getValue()][file.getValue()].setPiece(p);
    }
}