package chess;

public enum Rank {
    first(1),
    second(2),
    third(3),
    fourth(4),
    fifth(5),
    sixth(6),
    seventh(7),
    eighth(8);

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
    public static Rank valueOf(int rankType) {
        return (Rank) map.get(rankType);
    }

    public int getValue(){
        return value;
    }
}