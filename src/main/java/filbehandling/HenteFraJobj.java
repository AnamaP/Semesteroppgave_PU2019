package filbehandling;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class HenteFraJobj extends HenteFraFil{ // kan senere endre til "Jobbsoker" istedet for void for å kunne bruke objektet

    public String henteFraFil(String path){ // lag en egen exception som kastes ut av denne metoden og gir beskjed til bruker, med en try/catch i controlleren
        String melding = "Person ikke hentet.";
        try(FileInputStream fileInput = new FileInputStream(path);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput)){
            Object hentPerson = objectInput.readObject();
            System.out.println("Her er informasjon om jobbsøker fra .jobj fil:\n"+ hentPerson);
            melding = hentPerson.toString();
        }
        catch(IOException e){
            System.err.println("Kunne ikke lese fil. Feilmelding : " + e.getCause());
        }
        catch(ClassNotFoundException e){
            System.err.println("Kunne ikke konvertere objektet");
        }

        return melding;
    }
}
