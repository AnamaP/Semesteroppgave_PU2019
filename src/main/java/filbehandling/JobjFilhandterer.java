package filbehandling;

import java.io.*;

public class JobjFilhandterer extends Filhandterer {
    @Override
    public String henteFraFil(String path) {
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

    @Override
    public void skrivTilFil(String person, String path) throws IOException {
        try(FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){
            objectOutput.writeObject(person);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void lagreFilLokalt(String toPath, String fromPath) throws IOException {
        String innholdJobj = henteFraFil(fromPath);
        skrivTilFil(innholdJobj, toPath);
    }

}
