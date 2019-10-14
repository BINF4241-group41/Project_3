package chess;


// vertical column, labeled a-h, starting from left (white's view)
public enum File {
    a,
    b,
    c,
    d,
    e,
    f,
    g,
    h,
    out;

    public File add(int n) {
    	int valore=(this.ordinal() + n);
    	if(valore>7) return  File.out;
    	return File.values()[valore];
    }
    public File sub(int n) {
    	int valore=(this.ordinal() - n);
    	if (valore<0)return File.out;
    	return File.values()[valore];
    }
}