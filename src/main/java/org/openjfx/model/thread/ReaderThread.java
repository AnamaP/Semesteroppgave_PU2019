package org.openjfx.model.thread;

import org.openjfx.model.fileHandling.CsvFileHandler;
import java.util.concurrent.Callable;

public class ReaderThread implements Callable<String[]> {
    String chosenpath;

    public ReaderThread(String chosenpath){
        this.chosenpath = chosenpath;
    }

    public String[] call() throws Exception{
        // MÅ ta inn som paramete filen jeg ønsker å lese fra
        System.out.println("Laster opp...");

        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filen er lastet opp!");

        CsvFileHandler reader = new CsvFileHandler();
        return reader.readFromFile(chosenpath).toString().split(";");
    }
}
