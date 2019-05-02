package org.openjfx.model.fileHandling;

import java.io.*;

/**
 * Konkret klasse som representerer lagring og innlesing til jobj filformat
 */
public class JobjFileHandler extends FileHandler {
// TODO: PathNotFoundException, InvalidPathException og håndtere de andre metodene

    /**
     * Metode som leser fra fil
     */
    @Override
    public Object readFromFile(String path) {
        try(FileInputStream fileInput = new FileInputStream(path+".jobj");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput)){
            Object readObject = objectInput.readObject();
            return readObject;
        }
        catch(IOException e){
            System.err.println("Kunne ikke lese fil. Feilmelding : " + e.getCause());
        }
        catch(ClassNotFoundException e){
            System.err.println("Kunne ikke konvertere objektet");
        }
        return null;
    }

    /**
     * Metode som skriver til fil
     */
    @Override
    public void writeToFile(Object object, String path) throws IOException {
        try(FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){
            objectOutput.writeObject(object);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Metode som skriver til databasen og setter filformatet til csv
     */
    public void writeToDB(Object object, String path) throws IOException {
        FileHandler fileHandler = new CsvFileHandler();
        fileHandler.writeToFile(object, path+".csv");
    }
}
