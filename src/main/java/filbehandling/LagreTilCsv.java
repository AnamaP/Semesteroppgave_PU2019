package filbehandling;

/* Lagring til .csv fil som er kompatibelt med regnearksprogrammer som MS Excel
   - denne filtypen lagrer data som tekst, der hvert element er skrevet ut for hver linke i tekstfilen.
   - hver datakolonne separeres med et tegn, f.eks. semikolon. */


import klasser.Person;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LagreTilCsv extends LagreTilFil {

    @Override
    public void skrivPersonTilFil(List<Person> personer, String path) throws IOException {
        PrintWriter skriv = null;

        try{
            skriv = new PrintWriter(path, "UTF-8");

            for(Person person : personer){
                skriv.println(person);
            }
        }
        finally{
            if(skriv != null){
                skriv.close();
            }
        }





    }
}
