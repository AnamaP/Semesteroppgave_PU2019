package org.openjfx.model.fileHandling;

import org.openjfx.model.exceptions.PathNotFoundException;

import java.io.IOException;

/**
 * En abstrakt klasse som representerer lagring og innlesing av data til fil
 */
public abstract class FileHandler {
    public abstract Object readFromFile(String path);
    public abstract void writeToFile(Object person, String path) throws IOException;
    public abstract void writeToDB(Object object, String path) throws IOException;

    /**
     * Denne metoden henter extension fra filen
     */
    public static String getExtension(String chosenpath) throws PathNotFoundException{
        String extension;
        try {
            extension = chosenpath.substring(chosenpath.lastIndexOf("."));
        }
        catch (StringIndexOutOfBoundsException e){
            throw new PathNotFoundException("Finner ingen valgt fil");
        }
        return extension;
    }
}
