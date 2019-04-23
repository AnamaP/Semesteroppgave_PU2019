package logikk;

import filbehandling.CsvFilhandterer;
import klasser.Arbeidsgiver;
import klasser.Cv;
import klasser.Jobbsoker;
import klasser.Vikariat;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static logikk.RegSokerHjelper.jobbsokere;
import static logikk.RegVikariatHjelper.arbeidsgivere;

public class MainAppHelper {

    public void loadDatabaseFromCsv(){
        loadJobbsokerCsv();
        loadVikariatCsv();

    }

    public void loadJobbsokerCsv(){
        System.out.println("SOKERE: _______________________________________________");
        CsvFilhandterer test = new CsvFilhandterer();
        String sokereFraDatabase = (String) test.henteFraFil(Paths.JOBBSOKER_CSV);

        String [] rader = sokereFraDatabase.split("\n");

        for(int i = 0; i < rader.length; i++){
            String [] soker = rader[i].split(";");

            ArrayList<String> kategorier = new ArrayList<>();
            for (int k = 13; k < soker.length; k++){
                kategorier.add(soker[i]);
            }

            Cv cv = new Cv(soker[9], soker[10], soker[11], kategorier);

            Jobbsoker jobbsoker = new Jobbsoker(soker[0], soker[1], soker[2], soker[3], soker[4],
                    soker[5], soker[6], soker[7], cv);

            jobbsokere.add(jobbsoker);
            System.out.println(jobbsoker.toString());
        }
    }

    public void loadVikariatCsv(){
        System.out.println("ARBEIDSGIVERE: _______________________________________________");
        CsvFilhandterer test = new CsvFilhandterer();
        String vikariaterFraDatabase = (String) test.henteFraFil(Paths.VIKARIAT_CSV);

        String [] rader = vikariaterFraDatabase.split("\n");

        for(int i = 0; i < rader.length; i++){
            String [] vikariat = rader[i].split(";");

            ArrayList<String> kategorier = new ArrayList<>();
            for (int k = 11; k < vikariat.length; k++){
                kategorier.add(vikariat[i]);
            }

            Vikariat nyttVikariat = new Vikariat(vikariat[6],vikariat[7],vikariat[8],vikariat[9],vikariat[10], kategorier);

            Arbeidsgiver nyArbeidsgiver = new Arbeidsgiver(vikariat[0], vikariat[1],vikariat[2], vikariat[3],vikariat[4],
                    vikariat[5], nyttVikariat);

            arbeidsgivere.add(nyArbeidsgiver);
            System.out.println("Ny: "+ nyArbeidsgiver.toString());
        }
    }

    public void reloadJobbsokerDatabase(){

        PrintWriter writer = null;
        try{
            FileWriter fileWriter = new FileWriter(Paths.JOBBSOKER_CSV, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int a = 0; a < jobbsokere.size(); a++) {
                printWriter.println(jobbsokere.get(a));  //New line
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(writer != null){
                writer.close();
            }
        }
    }

    public void reloadVikariaterDatabase(){

        PrintWriter writer = null;
        try{
            FileWriter fileWriter = new FileWriter(Paths.VIKARIAT_CSV, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int a = 0; a < arbeidsgivere.size(); a++) {
                printWriter.println(arbeidsgivere.get(a));  //New line
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(writer != null){
                writer.close();
            }
        }
    }
}
