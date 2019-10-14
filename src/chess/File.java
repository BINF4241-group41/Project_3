package chess;

// vertical column, labeled a-h, starting from left (white's view)
public enum File {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8),
    // out(10); // pieces shouldn't be able to be outside of the gameboard

    private final int value;
    private static HashMap<Integer, File> intMap = new HashMap<>();

    // static initializer
    static {
        for (File file : File.values()) {
            intMap.put(file.value, file);
        }
    }

    private File(int value) {
        this.value = value;
    }

    // get File from int
    public static File valueOf(int fileType) {
        return (File) map.get(fileType);
    }

    public int getValue(){
        return value;
    }


    // Enum shouldn't contain game logic (separation of concerns).
    /*
    public File add(int n) { // I'm not sure that they could be useful
    	int valore=(this.ordinal() + n);
    	if(valore>7) return  File.out;
    	return File.values()[valore];
    }
    public File sub(int n) {
    	int valore=(this.ordinal() - n);
    	if (valore<0)return File.out;
    	return File.values()[valore];
    }

    public int distance(File file){
         if(this.value-file.value>0)return this.value-file.value;
         return file.value-this.value;
    }
    */

    // Can directly compare. -> redundant?
    /*
    public boolean sameFile(File r){
        if(r==this)return true;
        return false;
    }
    */

    // Idea for mapping int->File from https://codingexplained.com/coding/java/enum-to-integer-and-integer-to-enum
}