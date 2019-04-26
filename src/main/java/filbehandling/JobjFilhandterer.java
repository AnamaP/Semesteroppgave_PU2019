package filbehandling;

import java.io.*;

public class JobjFilhandterer extends Filhandterer {
    @Override
    public Object henteFraFil(String path) {
        try(FileInputStream fileInput = new FileInputStream(path+".jobj");
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
    public void skrivTilFil(Object object, String path) throws IOException {
        System.out.println("skrivTilFil jobj: "+path);
        try(FileOutputStream fileOutput = new FileOutputStream(path);
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){
            objectOutput.writeObject(object);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void skrivTilDB(Object object, String path) throws IOException {
        Filhandterer filhandterer = new CsvFilhandterer();
        filhandterer.skrivTilFil(object, path+".csv");
    }
}
