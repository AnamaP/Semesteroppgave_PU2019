package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasser.Jobbsoker;
import java.io.*;
import java.util.ArrayList;

import static logikk.OversiktHjelper.chosenRow;
import static logikk.OversiktHjelper.valgteKategorier;
import static logikk.RegSokerHjelper.jobbsokere;

public class OversiktSokereHjelper {

    public static ObservableList<TableJobseekers> visJobbsokere() {

        // Oppretter en tabell
        ObservableList<TableJobseekers> jobseekerList = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBBSOKER+".csv"));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 13){
                    OversiktHjelper run = new OversiktHjelper();
                    //Henter en jobbsoker fra listen:
                    Jobbsoker jobbsoker = run.hentSokerFraListe(kolonner);
                    //Legger søkeren til i tabellen:
                    TableJobseekers oversiktSokere = new TableJobseekers(jobbsoker);
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

    public static ObservableList<TableJobseekers> visResultat(){
        // Oppretter en tabell
        ObservableList<TableJobseekers> showResults = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBBSOKER+".csv"));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 13) {
                    OversiktHjelper run = new OversiktHjelper();
                    //Henter ut en jobbsøker fra listen:
                    Jobbsoker jobbsoker = run.hentSokerFraListe(kolonner);
                    //Henter ut jobbsokerens kategorier:
                    ArrayList<String> kategorier = run.hentKategorier(kolonner,13);
                    //Finner antall felles kategorier
                    int antall = run.sjekkKategorier(kategorier);

                    //Om søkeren har mange nok kategorier legges den til i tabellen:
                    if((antall == valgteKategorier.size()) && (kolonner[kolonner.length-1].equals("Ledig"))){
                        TableJobseekers oversiktSokere = new TableJobseekers(jobbsoker);
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
