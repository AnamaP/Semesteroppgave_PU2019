package logikk;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import klasser.Arbeidsgiver;
import klasser.Vikariat;
import java.io.*;
import java.util.ArrayList;

import static logikk.RegVikariatHjelper.arbeidsgivere;

public class OversiktVikariaterHjelper {

    private static ArrayList<String> valgteKategorier;

    public void setValgteKategorier(ArrayList<String> valgteKategorier) {
        this.valgteKategorier = valgteKategorier;
    }

    public static ObservableList<TabellVikariater> visVikariater(String path) {
        // Oppretter en tabell
        ObservableList<TabellVikariater> obl = FXCollections.observableArrayList();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){
            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 11){

                    ArrayList<String> kategorier = new ArrayList<>();
                    for(int i = 12; i < kolonner.length-1; i++) {
                        kategorier.add(kolonner[i]);
                    }

                    Vikariat vikariat = new Vikariat(kolonner[6],kolonner[7],kolonner[8],kolonner[9],
                                                     kolonner[10], kolonner[11], kategorier, kolonner[kolonner.length-1]);

                    Arbeidsgiver tabell = new Arbeidsgiver(kolonner[0], kolonner[1],kolonner[2],
                                                           kolonner[3],kolonner[4], kolonner[5], vikariat);

                    TabellVikariater test = new TabellVikariater(tabell);
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

    public static ObservableList<TabellVikariater> visResultat(String path){
        // Oppretter en tabell
        ObservableList<TabellVikariater> obl = FXCollections.observableArrayList();

        try(RandomAccessFile lesFil = new RandomAccessFile(path, "r")){
            BufferedReader csvreader = new BufferedReader(new FileReader(path));
            String rad;

            System.out.println("Valgte kategorier: " + valgteKategorier.toString());

            while ((rad = csvreader.readLine()) != null){
                String [] kolonner = rad.split(";");

                if(kolonner.length > 11) {

                    ArrayList<String> kategorier = new ArrayList<>();
                    for (int i = 12; i < kolonner.length-1; i++) {
                        kategorier.add(kolonner[i]);
                    }

                    System.out.println("Kategorier : " + kategorier.toString());

                    //Skrivet ut kun en av kategoriene "matcher":
                    if (
                            (kategorier.toString().contains("Salg")) && valgteKategorier.contains("Salg") ||
                                    (kategorier.toString().contains("Admin")) && valgteKategorier.contains("Admin") ||
                                    (kategorier.toString().contains("It")) && valgteKategorier.contains("It") ||
                                    (kategorier.toString().contains("Okonomi")) && valgteKategorier.contains("Okonomi")
                    ) {

                        Vikariat vikariat = new Vikariat(kolonner[6],kolonner[7],kolonner[8],kolonner[9],
                                                         kolonner[10], kolonner[11], kategorier, kolonner[kolonner.length-1]);

                        Arbeidsgiver tabell = new Arbeidsgiver(kolonner[0], kolonner[1],kolonner[2],
                                                               kolonner[3],kolonner[4], kolonner[5], vikariat);

                        TabellVikariater test = new TabellVikariater(tabell);
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

    public static Boolean slettValgtVikariat(String key) {
        int chosenArbeidsgiver = findArbeidsgiver(key);
        if(chosenArbeidsgiver >= 0) {
            arbeidsgivere.remove(chosenArbeidsgiver);
            return true;
        }
        return false;
    }

    public static Boolean redigerValgtVikariat(String key){
        int chosenArbeidsgiver = findArbeidsgiver(key);
        return true;
    }

    public static void saveTempJob(String key){
        int chosenArbeidsgiver = findArbeidsgiver(key);
        if(chosenArbeidsgiver >= 0){
            FileChooserHjelper.lastNed(arbeidsgivere.get(chosenArbeidsgiver));
        }
        //TODO : Feilmld til bruker om at vikariat ikke er valgt
    }

    public static String lesMerTittel(String key){
        int chosenArbeidsgiver = findArbeidsgiver(key);
        Arbeidsgiver arbeidsgiver = arbeidsgivere.get(chosenArbeidsgiver);
        return arbeidsgiver.getVikariat().getTittel();
    }

    public static String lesMerInnhold(String key){
        int chosenArbeidsgiver = findArbeidsgiver(key);
        Arbeidsgiver arbeidsgiver = arbeidsgivere.get(chosenArbeidsgiver);
        String ut = "";
        ut += "Beskrivelse: \n" + arbeidsgiver.getVikariat().getBeskrivelse() + "\n";
        ut += "Kvalifikasjoner: \n" + arbeidsgiver.getVikariat().getKvalifikasjoner() + "\n";
        ut += "Antatt årslønn: \n" + arbeidsgiver.getVikariat().getLonn() + "\n";
        ut += "Varighet: \n" + arbeidsgiver.getVikariat().getVarighet() + "\n";
        return ut;
    }

    public static int findArbeidsgiver(String key){
        for(int i = 0; i < arbeidsgivere.size(); i++){
            String tlf = arbeidsgivere.get(i).getTlf();
            System.out.println(tlf);
            if(tlf.equals(key)){
                return i;
            }
        }
        return -1;
    }
}

