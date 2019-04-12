package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class OversiktSokereHjelper {

    public static ObservableList<TabellSokere> visJobbsokere(String path) {
        // Oppretter en tabell
        ObservableList<TabellSokere> obl = FXCollections.observableArrayList();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){
            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 12){
                    TabellSokere tabellSokere = new TabellSokere(kolonner[1]+", "+kolonner[0], kolonner[6], kolonner[9], kolonner[10]+", "+kolonner[13]+", "+kolonner[14]);
                    obl.add(tabellSokere);
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return obl;
    }
}
//innhold += kolonner[1] + "," + kolonner[0] + "\t" + kolonner[6] + "\t" + kolonner[9] + "," + kolonner[10] + "\t";
//innhold += kolonner[13] + kolonner[14] + kolonner[15] + kolonner[16];
