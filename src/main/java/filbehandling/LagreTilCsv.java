package filbehandling;

/* Lagring til .csv fil som er kompatibelt med regnearksprogrammer som MS Excel
   - denne filtypen lagrer data som tekst, der hvert element er skrevet ut for hver linke i tekstfilen.
   - hver datakolonne separeres med et tegn, f.eks. semikolon. */

import java.io.*;

public class LagreTilCsv extends LagreTilFil {
    //writern - få den til å appende et semikolon etter hvert felt i elementet

    @Override
    public void skrivTilFil(String person, String path) throws IOException {
        PrintWriter writer = null;

        try{
            FileWriter fileWriter = new FileWriter(path, true); //Set true for append mode
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(person);  //New line
            printWriter.close();
        }
        finally{
            if(writer != null){
                writer.close();
            }
        }
    }
}
