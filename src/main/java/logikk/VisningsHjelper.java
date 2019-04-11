package logikk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class VisningsHjelper {

    public static String viserJobbsokere(String path) {
        String innhold = "Navn: \t Epost: \t Utdanning: \t Kategorier: \n";

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){

            for(int i = 0; i < 1; i++){
                String [] kolonner = lesFil.readLine().split(";");

                innhold += kolonner[1]+","+kolonner[0]+"\t"+kolonner[6]+"\t"+kolonner[9]+","+kolonner[10]+"\t";
                innhold += kolonner[13]+kolonner[14]+kolonner[15]+kolonner[16];
                innhold += "\n";
            }
        }
        catch(FileNotFoundException e){
            System.err.println("Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }

        return innhold;
    }
}
