package filbehandling;

import java.io.*;

public class CsvFilhandterer extends Filhandterer {

    // TODO: Metode for validering

    @Override
    public Object henteFraFil(String path) {
        System.out.println(path);
        String innhold = "";

        // en metode som kaller på validering av filen man forsøker å laste opp (null, tlf) - at formateringen er tilnærmet riktig (int)

        try(RandomAccessFile lesFil = new RandomAccessFile(path+".csv", "r")){
            String rad;
            while((rad = lesFil.readLine()) != null){
                innhold += rad + "\n";
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }

        System.out.println(innhold);
        return innhold;
    }


    @Override
    public void skrivTilFil(Object person, String path) throws IOException {
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

    @Override
    public void skrivTilDB(Object object, String path) throws IOException {
        skrivTilFil(object, path+".csv");
    }
    /*
    @Override
    public void lagreFilLokalt(String toPath, String fromPath) {
        Object databaseFil = henteFraFil(fromPath);
        try {
            skrivTilFil(databaseFil, toPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
