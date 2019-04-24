package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasser.Cv;
import klasser.Jobbsoker;
import java.io.*;
import java.util.ArrayList;

import static logikk.RegSokerHjelper.jobbsokere;

public class OversiktSokereHjelper {

    private static ArrayList<String> valgteKategorier;

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

                if(kolonner.length > 13){
                    ArrayList<String> kategorier = new ArrayList<>();
                    for(int i = 13; i < kolonner.length-1; i++) {
                        kategorier.add(kolonner[i]);
                    }

                    Cv cv = new Cv(kolonner[9],kolonner[10],kolonner[11],kategorier);

                    Jobbsoker tabell = new Jobbsoker(kolonner[0],kolonner[1],kolonner[2],kolonner[3],kolonner[4],
                                                     kolonner[5],kolonner[6],kolonner[7],cv, kolonner[kolonner.length-1]);

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

            System.out.println("Valgte kategorier: " + valgteKategorier.toString());

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 13) {

                    ArrayList<String> kategorier = new ArrayList<>();
                    for (int i = 13; i < kolonner.length -1; i++) {
                        kategorier.add(kolonner[i]);
                    }

                    System.out.println("Kategorier : " + kategorier.toString());

                    for(int i = 0; i < valgteKategorier.size(); i++){
                        kategorier.equals(valgteKategorier.get(i));
                    }

                    //Denne henter kun de som passer til alle kategoriene vikariatet spør om (+ evt ekstra kategorier søkeren måtte ha):
                    int antall = 0;
                    for(int i = 0; i < valgteKategorier.size(); i++) {
                        if (kategorier.toString().contains(valgteKategorier.get(i))) {
                            antall++;
                        }
                    }
                    if(antall == valgteKategorier.size()){
                        Cv cv = new Cv(kolonner[9], kolonner[10], kolonner[11], kategorier);

                        Jobbsoker tabell = new Jobbsoker(kolonner[0], kolonner[1], kolonner[2], kolonner[3], kolonner[4],
                                kolonner[5], kolonner[6], kolonner[7], cv, kolonner[kolonner.length-1]);

                        TabellSokere oversiktSokere = new TabellSokere(tabell);
                        obl.add(oversiktSokere);
                    }
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

    public static void saveJobseeker(String key){
        for(int i = 0; i < jobbsokere.size(); i++){
            String tlf = jobbsokere.get(i).getTlf();
            if(tlf.equals(key)){
                FileChooserHjelper.lastNed(jobbsokere.get(i));
                break;
            }
        }
        //TODO : Feilmld til bruker om at vikariat ikke er valgt

    }
}
