package Thread;

import fileHandling.FileHandler;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class ReaderThread<T> implements Callable<ArrayList<T>> {
    String chosenpath;

    public ReaderThread(String chosenpath){
        this.chosenpath = chosenpath;
    }

    public ArrayList<T> call(){
        // MÅ ta inn som parametre filen jeg ønsker å lese fra
        FileHandler fileHandler = null;

        fileHandler.getExtension(chosenpath);

        //return fileHandler.readFromFile(chosenpath);
        return null;
    }
}
