package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasser.Arbeidsgiver;
import klasser.Vikariat;
import java.io.*;
import java.util.ArrayList;

import static logikk.OversiktHjelper.chosenRow;
import static logikk.OversiktHjelper.valgteKategorier;
import static logikk.RegVikariatHjelper.arbeidsgivere;

public class OversiktVikariaterHjelper {

    public static ObservableList<TabellVikariater> visVikariater() {
        // Oppretter en tabell
        ObservableList<TabellVikariater> obl = FXCollections.observableArrayList();
        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.VIKARIAT+".csv"));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 12){
                    OversiktHjelper run = new OversiktHjelper();
                    Arbeidsgiver arbeidsgiver = run.hentVikariatFraListe(kolonner);

                    TabellVikariater test = new TabellVikariater(arbeidsgiver);
                    obl.add(test);
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

    public static ObservableList<TabellVikariater> visResultat(){
        // Oppretter en tabell
        ObservableList<TabellVikariater> obl = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.VIKARIAT+".csv"));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 12) {
                    OversiktHjelper run = new OversiktHjelper();
                    //Henter ut en arbeidsgiver:
                    Arbeidsgiver arbeidsgiver = run.hentVikariatFraListe(kolonner);

                    ArrayList<String> kategorier = run.hentKategorier(kolonner, 12);

                    //Legger arbeidsgiveren i tabellen kun om en av kategoriene "matcher":
                    if (
                            ((kategorier.toString().contains("Salg")) && valgteKategorier.contains("Salg") ||
                                    (kategorier.toString().contains("Admin")) && valgteKategorier.contains("Admin") ||
                                    (kategorier.toString().contains("It")) && valgteKategorier.contains("It") ||
                                    (kategorier.toString().contains("Okonomi")) && valgteKategorier.contains("Okonomi"))
                            && kolonner[kolonner.length-1].equals("Ledig")

                    ) {
                        TabellVikariater test = new TabellVikariater(arbeidsgiver);
                        obl.add(test);
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

    public static void slettValgtVikariat(String key) {
        findArbeidsgiver(key);
        arbeidsgivere.remove(arbeidsgivere.get(chosenRow));
    }

    public static void saveTempJob(String key){
        findArbeidsgiver(key);
        FileChooserHjelper.lastNed(arbeidsgivere.get(chosenRow));

        //TODO : Feilmld til bruker om at vikariat ikke er valgt
    }

    public static String lesMerTittel(String key){
        findArbeidsgiver(key);
        return arbeidsgivere.get(chosenRow).getVikariat().getTittel();
    }

    public static String lesMerInnhold(String key){
        findArbeidsgiver(key);
        Arbeidsgiver arbeidsgiver = arbeidsgivere.get(chosenRow);
        String ut = "";
        ut += "Beskrivelse: \n" + arbeidsgiver.getVikariat().getBeskrivelse() + "\n\n";
        ut += "Kvalifikasjoner: \n" + arbeidsgiver.getVikariat().getKvalifikasjoner() + "\n\n";
        ut += "Antatt årslønn: " + arbeidsgiver.getVikariat().getLonn() + "\n\n";
        ut += "Varighet: " + arbeidsgiver.getVikariat().getVarighet() + "\n\n";
        return ut;
    }

    public static void findArbeidsgiver(String key){
        OversiktHjelper run = new OversiktHjelper();
        run.findRow(arbeidsgivere, key);
    }
}

