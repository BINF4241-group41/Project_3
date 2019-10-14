package chess;

public enum Rank {
    first,
    second,
    third,
    fourth,
    fifth,
    sixth,
    seventh,
    eighth,
    out;
	
    public Rank add(int n) {
    	int valore=(this.ordinal() + n);
    	if(valore>7) return  Rank.out;
    	return Rank.values()[valore];
    }
    public Rank sub(int n) {
    	int valore=(this.ordinal() - n);
    	if (valore<0)return Rank.out;
    	return Rank.values()[valore];
    }
}