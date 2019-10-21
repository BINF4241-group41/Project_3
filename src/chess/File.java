package chess;

import java.util.HashMap;


public enum File {

    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);


    private final int value;
    private static HashMap<Integer, File> intMap = new HashMap<>();


    // static initializer
    static {
        for (File file : File.values()) {
            intMap.put(file.value, file);
        }
    }

    // get File from int
    public static File valueOf(int fileType) {
        return (File) intMap.get(fileType);
    }

    // get File from String
    public static File fromString(String name) {
        for (File file : File.values()) {
            if (file.toString() == "name") {
                return file;
            }
        }
        return null;
    }

    private File(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}