package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasser.Cv;
import klasser.Jobbsoker;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OversiktSokereHjelper {

    private static ArrayList<String> valgteKategorier;

    public OversiktSokereHjelper() {
    }

    public ArrayList<String> getValgteKategorier() {
        return valgteKategorier;
    }

    public void setValgteKategorier(ArrayList<String> valgteKategorier) {
        this.valgteKategorier = valgteKategorier;
    }


    public static ObservableList<TabellSokere> visJobbsokere(String path) {
        // Oppretter en tabell
        ObservableList<TabellSokere> obl = FXCollections.observableArrayList();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){
            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 16){
                    ArrayList<String> kategorier = new ArrayList<>();
                    kategorier.add(kolonner[13]);
                    kategorier.add(kolonner[14]);
                    kategorier.add(kolonner[15]);
                    kategorier.add(kolonner[16]);

                    Cv cv = new Cv(kolonner[9],kolonner[10],kolonner[11],kategorier);

                    Jobbsoker tabell = new Jobbsoker(kolonner[0],kolonner[1],kolonner[2],kolonner[3],kolonner[4],
                                                     kolonner[5],kolonner[6],kolonner[7],cv);

                    //TODO: finne ut av hvordan vi gjør det med lonnskrav[8] og referanse[12] (valgfritt felt, ikke med i konstruktøren)

                    TabellSokere oversiktSokere = new TabellSokere(tabell);
                    obl.add(oversiktSokere);
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


    public static ObservableList<TabellSokere> visResultat(String path){
        // Oppretter en tabell
        ObservableList<TabellSokere> obl = FXCollections.observableArrayList();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){
            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                //Tester om noen av kategoriene "matcher":
                if (
                        (kolonner[13].equals(valgteKategorier.get(0)) && kolonner[13].equals("Salg")) ||
                        (kolonner[14].equals(valgteKategorier.get(1)) && kolonner[14].equals("Service")) ||
                        (kolonner[15].equals(valgteKategorier.get(2)) && kolonner[15].equals("It")) ||
                        (kolonner[16].equals(valgteKategorier.get(3)) && kolonner[16].equals("Okonomi"))
                ) {

                    ArrayList<String> kategorier = new ArrayList<>();
                    kategorier.add(kolonner[13]);
                    kategorier.add(kolonner[14]);
                    kategorier.add(kolonner[15]);
                    kategorier.add(kolonner[16]);

                    Cv cv = new Cv(kolonner[9], kolonner[10], kolonner[11], kategorier);

                    Jobbsoker tabell = new Jobbsoker(kolonner[0], kolonner[1], kolonner[2], kolonner[3], kolonner[4],
                            kolonner[5], kolonner[6], kolonner[7], cv);

                    TabellSokere oversiktSokere = new TabellSokere(tabell);
                    obl.add(oversiktSokere);
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
