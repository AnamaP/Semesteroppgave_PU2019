package org.openjfx.model.fileHandling;

import java.io.IOException;

public abstract class FileHandler {
    public abstract Object readFromFile(String path);
    public abstract void writeToFile(Object person, String path) throws IOException;
    public abstract void writeToDB(Object object, String path) throws IOException;

    /**
     * Denne metoden sender med en "chosenpath" som kalles på i FileChooserHelper.
     * Den returnerer en extension som leser det som kommer etter filnavnet.
     * I FileChooserHelper kjøres det en test på hva slags filtype.. (se videre i FileChooserHelper for forklaring)
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
