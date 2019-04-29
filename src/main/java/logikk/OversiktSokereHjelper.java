package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasser.Cv;
import klasser.Jobbsoker;
import java.io.*;
import java.util.ArrayList;

import static logikk.OversiktHjelper.chosenRow;
import static logikk.RegSokerHjelper.jobbsokere;

public class OversiktSokereHjelper {

    private static ArrayList<String> valgteKategorier;

    public void setValgteKategorier(ArrayList<String> valgteKategorier) {
        this.valgteKategorier = valgteKategorier;
    }

    public static ObservableList<TabellSokere> visJobbsokere() {

        // Oppretter en tabell
        ObservableList<TabellSokere> jobseekerList = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBBSOKER+".csv"));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 14){
                    ArrayList<String> kategorier = new ArrayList<>();
                    for(int i = 13; i < kolonner.length-1; i++) {
                        kategorier.add(kolonner[i]);
                    }

                    Cv cv = new Cv(kolonner[9],kolonner[10],kolonner[11],kategorier);

                    Jobbsoker tabell = new Jobbsoker(kolonner[0],kolonner[1],kolonner[2],kolonner[3],kolonner[4],
                                                     kolonner[5],kolonner[6],kolonner[7],cv, kolonner[kolonner.length-1]);

                    TabellSokere oversiktSokere = new TabellSokere(tabell);
                    jobseekerList.add(oversiktSokere);
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("visJobbsokere() : Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("visJobbsokere() : Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return jobseekerList;
    }

    public static ObservableList<TabellSokere> visResultat(){
        // Oppretter en tabell
        ObservableList<TabellSokere> showResults = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBBSOKER+".csv"));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 13) {

                    ArrayList<String> kategorier = new ArrayList<>();
                    for (int i = 13; i < kolonner.length -1; i++) {
                        kategorier.add(kolonner[i]);
                    }

                    //Denne henter kun søkerne som passer til alle kategoriene vikariatet spør om (+ evt ekstra kategorier søkeren måtte ha):
                    int antall = 0;
                    for(int i = 0; i < valgteKategorier.size(); i++) {
                        if (kategorier.toString().contains(valgteKategorier.get(i))) {
                            antall++;
                        }
                    }

                    if((antall == valgteKategorier.size()) && (kolonner[kolonner.length-1].equals("Ledig"))){
                        Cv cv = new Cv(kolonner[9], kolonner[10], kolonner[11], kategorier);

                        Jobbsoker tabell = new Jobbsoker(kolonner[0], kolonner[1], kolonner[2], kolonner[3], kolonner[4],
                                kolonner[5], kolonner[6], kolonner[7], cv, kolonner[kolonner.length-1]);

                        TabellSokere oversiktSokere = new TabellSokere(tabell);
                        showResults.add(oversiktSokere);
                    }
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("VisResultat() : Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("VisResultat() : Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return showResults;
    }

    public static void saveJobseeker(String key){
        findJobbsoker(key);
        FileChooserHjelper.lastNed(jobbsokere.get(chosenRow));
        //TODO : Feilmld til bruker om at jobbsoker ikke er valgt
    }

    public static void findJobbsoker(String key){
        OversiktHjelper run = new OversiktHjelper();
        run.findRow(jobbsokere, key);
    }
}
