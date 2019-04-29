package fileHandling;

import java.io.IOException;

public abstract class FileHandler {
    public abstract Object readFromFile(String path);
    public abstract void writeToFile(Object person, String path) throws IOException;
    public abstract void writeToDB(Object object, String path) throws IOException;

    public static String getExtension(String chosenpath) {
        return chosenpath.substring(chosenpath.lastIndexOf("."));
    }
}
