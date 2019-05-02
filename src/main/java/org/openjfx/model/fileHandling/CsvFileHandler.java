package org.openjfx.model.fileHandling;

import java.io.*;

/**
 * Konkret klasse som representerer lagring og innlesing til csv filformat
 */
public class CsvFileHandler extends FileHandler {


    /**
     * Metode som leser fra fil
     */
    @Override
    public Object readFromFile(String path) {
        String content = "";

        try(RandomAccessFile readFile = new RandomAccessFile(path+".csv", "r")){
            String row;
            while((row = readFile.readLine()) != null){
                content += row + "\n";
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return content;
    }

    /**
     * Metode som skriver til fil
     */
    @Override
    public void writeToFile(Object object, String path) throws IOException {
        PrintWriter writer = null;

        try{
            FileWriter fileWriter = new FileWriter(path, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(object);
            printWriter.close();
        }
        finally{
            if(writer != null){
                writer.close();
            }
        }
    }

    /**
     * Metode som skriver til databasen og setter filformatet til csv
     */
    @Override
    public void writeToDB(Object object, String path) throws IOException {
        writeToFile(object, path+".csv");
    }
}
