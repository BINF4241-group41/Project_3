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
    // out(10); // pieces shouldn't be able to be outside of the gameboard

    private final int value;
    private static HashMap<Integer, Rank> intMap = new HashMap<>();

    // static initializer
    static {
        for (Rank rank : Rank.values()) {
            intMap.put(rank.value, rank);
        }
    }

    private Rank(int value) {
        this.value = value;
    }

    // get File from int
    public static RankType valueOf(int rankType) {
        return (RankType) map.get(rankType);
    }

    public int getValue(){
        return value;
    }

    // Enum shouldn't contain game logic (separation of concerns).
    /*
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

    public int distance(Rank r){
        if(this.value-r.value>0)return this.value-r.value;
        return r.value-this.value;
   }
   */

    // Can directly compare. -> redundant?
   /*
    public boolean sameRank(Rank r){
        if(r==this)return true;
        return false;
    }
    */
    }