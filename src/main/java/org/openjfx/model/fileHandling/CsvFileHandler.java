package org.openjfx.model.fileHandling;

import java.io.*;

public class CsvFileHandler extends FileHandler {

    // TODO: Metode for validering

    @Override
    public Object readFromFile(String path) {
        String content = "";

        // TODO: en metode som kaller på validering av filen man forsøker å laste opp (null, tlf) - at formateringen er tilnærmet riktig (int)

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

    @Override
    public void writeToDB(Object object, String path) throws IOException {
        writeToFile(object, path+".csv");
    }
}
