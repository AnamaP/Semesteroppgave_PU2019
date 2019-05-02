package org.openjfx.model.fileHandling;

import java.io.*;

public class JobjFileHandler extends FileHandler {
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

    public void writeToDB(Object object, String path) throws IOException {
        FileHandler fileHandler = new CsvFileHandler();
        fileHandler.writeToFile(object, path+".csv");
    }
}
