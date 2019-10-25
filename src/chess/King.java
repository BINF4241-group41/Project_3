package chess;


public class King extends Piece {

    private static final int MAX_MOV = 1;
    private boolean hasBeenMoved = false;

    public King(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
        this.name = "K";
    }

    public King makeCopy() {
        return new King(this.color, this.rank, this.file);
    }

    public String toString() {
        return color.getColorDescription() + name;
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {

        if (rank == null || file == null) {
            return false;
        }

        // origin == destination
        if (this.rank == rank && this.file == file) {
            return false;
        }

        // can't eat piece of same color
        if (gameBoard.isPositionOccupied(rank, file) && gameBoard.getPieceAtPosition(rank, file).getColor() == this.color) {
            return false;
        }

        if (Math.abs(rank.getValue() - this.rank.getValue()) > MAX_MOV) return false;
        if (Math.abs(file.getValue() - this.file.getValue()) > MAX_MOV) return false;

        return true;
    }


    public void movePiece(Rank rank, File file) {
        if (rank != null && file != null) {
            this.rank = rank;
            this.file = file;
            hasBeenMoved = true;
        }
    }

    public boolean canCastle(GameBoard board, Tower tower) {

        if (this.hasBeenMoved || tower.hasBeenMoved() || this.rank != tower.getRank() || this.color != tower.getColor()) {
            return false;
        }

        for (int fileCounter = Math.min(this.file.getValue(), tower.getFile().getValue()) + 1; fileCounter < Math.max(this.file.getValue(), tower.getFile().getValue()) - 1; ++fileCounter) {

            if (board.isPositionOccupied(this.rank, File.valueOf(fileCounter))) {
                return false;
            }
            if (fileCounter == 2) {
                continue; // queenside castling, square bewteen tower and king
            }

            for (int rank = 7; rank >= 0; --rank) { // horizontal (1-8)
                for (int file = 0; file < 8; ++file) { // vertical (a-h)
                    if (board.isPositionOccupied(Rank.valueOf(rank + 1), File.valueOf(file + 1))) {
                        Piece p = board.getPieceAtPosition(Rank.valueOf(rank + 1),  File.valueOf(file + 1));
                        if (p.getColor() != this.color && p.isMoveAllowed(board, this.rank, File.valueOf(fileCounter))) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}