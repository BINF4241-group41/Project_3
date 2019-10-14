package chess;

public enum Rank {
    first(1),
    second(2),
    third(3),
    fourth(4),
    fifth(5),
    sixth(6),
    seventh(7),
    eighth(8),
    out(10);

    private Rank(int value) {
        this.value = value;
    }

    public int getRank(){
        return value;
    }
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
    public boolean sameRank(Rank r){
        if(r==this)return true;
        return false;
    }
    public int distance(Rank r){
        if(this.value-r.value>0)return this.value-r.value;
        return r.value-this.value;
   }
}