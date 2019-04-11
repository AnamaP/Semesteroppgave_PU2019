package filbehandling;

import java.io.*;

public class CsvFilhandterer extends Filhandterer {
    @Override
    public String henteFraFil(String path) {
        String innhold = "";

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){

            // leser kun nå de 30 første linjene, OBS: bør settes til noe annet
            for(int i = 0; i < lesFil.length(); i++){
                innhold += lesFil.readLine();
                innhold += "\n";
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

    @Override
    public void lagreFilLokalt(String toPath, String fromPath) {
        String databaseFil = henteFraFil(fromPath);

        try {
            skrivTilFil(databaseFil, toPath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
