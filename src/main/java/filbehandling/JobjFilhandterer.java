package filbehandling;

import java.io.*;

public class JobjFilhandterer extends Filhandterer {
    @Override
    public Object henteFraFil(String path) {
        try(FileInputStream fileInput = new FileInputStream(path);
            ObjectInputStream objectInput = new ObjectInputStream(fileInput)){
            Object hentPerson = objectInput.readObject();
            return hentPerson;
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
    public void skrivTilFil(Object person, String path) throws IOException {
        try(FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){
            objectOutput.writeObject(person);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    /*
    @Override
    public void lagreFilLokalt(String toPath, String fromPath) throws IOException {
        Object innholdJobj = henteFraFil(fromPath);
        skrivTilFil(innholdJobj, toPath);
    }*/

}
