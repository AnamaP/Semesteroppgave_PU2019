package logikk;

import java.io.*;

public class VisningsHjelper {

    public static String visJobbsokere(String path) {
        String innhold = "Navn: \t Epost: \t Utdanning: \t Kategorier: \n";

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){

            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;
            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");
                innhold += kolonner[1] + "," + kolonner[0] + "\t" + kolonner[6] + "\t" + kolonner[9] + "," + kolonner[10] + "\t";
                innhold += kolonner[13] + kolonner[14] + kolonner[15] + kolonner[16];
                innhold += "\n";
            }
            csvreader.close();
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
