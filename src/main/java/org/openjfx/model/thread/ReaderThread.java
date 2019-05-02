package org.openjfx.model.thread;

import org.openjfx.model.fileHandling.CsvFileHandler;
import org.openjfx.model.fileHandling.FileHandler;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ReaderThread implements Callable<String[]> {
    String chosenpath;

    public ReaderThread(String chosenpath){
        this.chosenpath = chosenpath;
    }

    public String[] call(){
        // MÅ ta inn som paramete filen jeg ønsker å lese fra
        System.out.println("Jeg kjører");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ferdig med å sove");

        //CsvFileHandler reader = new CsvFileHandler();
        //return reader.readFromFile(chosenpath).toString().split(";");

        //return fileHandler.readFromFile(chosenpath);
        return null;
    }
}
