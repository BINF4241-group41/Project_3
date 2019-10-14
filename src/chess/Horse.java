package chess;

public class Horse extends Piece implements ActPiece{

    private final String name;

    public Horse(Color color, Square position){
        this.color=color;
        this.position=position;
        this.name="H";
    }
    public boolean makeCopy(){
        return new Horse(this); //i've to check if it works or not
    }

    public String toString(){
		return "["+color.getColorDescription()+name+"]";
    }
    public boolean isMoveAllowed(Square mov){
        if(position.getFile().getValue()+2==mov.getFile().getValue()){
            if(position.getRank().getValue()+1==mov.getRank.getValue()||position.getRank().getValue()-1==mov.getRank.getValue())return true;
        }
        if(position.getFile().getValue()+1==mov.getFile().getValue()){
            if(position.getRank().getValue()+2==mov.getRank.getValue()||position.getRank().getValue()-2==mov.getRank.getValue())return true;
        }
        if(position.getFile().getValue()-1==mov.getFile().getValue()){
            if(position.getRank().getValue()+2==mov.getRank.getValue()||position.getRank().getValue()-2==mov.getRank.getValue())return true;
        }
        if(position.getFile().getValue()-2==mov.getFile().getValue()){
            if(position.getRank().getValue()+1==mov.getRank.getValue()||position.getRank().getValue()-1==mov.getRank.getValue())return true;
        }
    return false;
    }
}