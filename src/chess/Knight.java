package chess;


public class Knight extends Piece {

    private final String name = "N";

    public Knight(Color color, Rank rank, File file) {
        this.color = color;
        this.rank = rank;
        this.file = file;
    }

    public Knight makeCopy(){
        return new Knight(this.color, this.rank, this.file);
    }

    public String toString() {
        return "[" + color.getColorDescription() + name + "]";
    }

    public boolean isMoveAllowed(GameBoard gameBoard, Rank rank, File file) {
        if (this.file.getValue() + 2 == file.getValue()){
            if (Math.abs(this.rank.getValue() - rank.getValue()) == 1) return true;
        }
        if (this.file.getValue() + 1 == file.getValue()) {
            if (Math.abs(this.rank.getValue() - rank.getValue()) == 2) return true;
        }
        if (this.file.getValue() - 1 == file.getValue()) {
            if (Math.abs(this.rank.getValue() - rank.getValue()) == 2) return true;
        }
        if (this.file.getValue() - 2 == file.getValue()) {
            if (Math.abs(this.rank.getValue() - rank.getValue()) == 1)return true;
        }
        return false;
    }
}