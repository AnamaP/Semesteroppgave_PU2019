package org.openjfx.model.fileHandling;

import java.io.IOException;

/**
 * En abstrakt klasse som representerer lagring og innlesing av data til fil
 */
public abstract class FileHandler {
    public abstract Object readFromFile(String path);
    public abstract void writeToFile(Object person, String path) throws IOException;
    public abstract void writeToDB(Object object, String path) throws IOException;

    // TODO: PathNotFoundException, InvalidPathException og håndtere de andre metodene, Nullpointer må erstattes

    /**
     * Denne metoden henter extension fra filen
     */
    public static String getExtension(String chosenpath) throws NullPointerException{
        String extension;
        try {
            extension = chosenpath.substring(chosenpath.lastIndexOf("."));
        }
        catch (NullPointerException e){
            extension = "";
        }
        return extension;
    }
}
