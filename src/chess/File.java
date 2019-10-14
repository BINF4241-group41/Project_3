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
    out(10);

    private final int value;

    private File(int value) {
        this.value = value;
    }
    public int getValue(){
        retunr value;
    }

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
    public boolean sameFile(File r){
        if(r==this)return true;
        return false;
    }
    public int distance(File file){
         if(this.value-file.value>0)return this.value-file.value;
         return file.value-this.value;
    }
}