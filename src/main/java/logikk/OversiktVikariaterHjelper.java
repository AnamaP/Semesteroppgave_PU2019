package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class OversiktVikariaterHjelper {

    public static ObservableList<TabellVikariater> visVikariater(String path) {
        // Oppretter en tabell
        ObservableList<TabellVikariater> obl = FXCollections.observableArrayList();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){
            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 15){
                    TabellVikariater tabell = new TabellVikariater(kolonner[0],kolonner[1], kolonner[2],kolonner[3],
                            kolonner[5],kolonner[6],kolonner[7],kolonner[10], kolonner[12]+" "+kolonner[13]
                            +" "+kolonner[14]+" "+kolonner[15]);
                    obl.add(tabell);
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Får ikke lastet inn data");
        }
        catch(IOException e){
            System.err.println("NB! Her må en annen feilmld komme " + e.getCause());
        }
        return obl;
    }
}

