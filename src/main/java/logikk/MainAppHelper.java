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
        CsvFilhandterer test = new CsvFilhandterer();
        String sokereFraDatabase = (String) test.henteFraFil(Paths.JOBBSOKER);

        String [] rader = sokereFraDatabase.split("\n");

        for (int i = 0; i < rader.length; i++) {
            String[] soker = rader[i].split(";");

            if(soker.length > 14) {
                ArrayList<String> kategorier = new ArrayList<>();
                for (int k = 13; k < soker.length - 1; k++) {
                    kategorier.add(soker[k]);
                }

                Cv cv = new Cv(soker[9], soker[10], soker[11], kategorier);

                // hvis referanse er satt så...
                if(soker[12] != ""){
                    cv.setReferanse(soker[12]);
                }

                Jobbsoker jobbsoker = new Jobbsoker(soker[0], soker[1], soker[2], soker[3], soker[4],
                        soker[5], soker[6], soker[7], cv, soker[soker.length - 1]);

                // hvis lønnskrav er satt så...
                if(soker[8] != ""){
                    jobbsoker.setLonnskrav(soker[8]);
                }

                jobbsokere.add(jobbsoker);
            }
        }
    }

    public void loadVikariatCsv(){
        CsvFilhandterer test = new CsvFilhandterer();
        String vikariaterFraDatabase = (String) test.henteFraFil(Paths.VIKARIAT);

        String [] rader = vikariaterFraDatabase.split("\n");

        for (int i = 0; i < rader.length; i++) {
            String[] vikariat = rader[i].split(";");

            if(vikariat.length > 13) {
                ArrayList<String> kategorier = new ArrayList<>();
                for (int k = 12; k < vikariat.length - 1; k++) {
                    kategorier.add(vikariat[k]);
                }

                Vikariat nyttVikariat = new Vikariat(vikariat[6], vikariat[7], vikariat[8], vikariat[9], vikariat[10], vikariat[11], kategorier, vikariat[vikariat.length - 1]);

                Arbeidsgiver nyArbeidsgiver = new Arbeidsgiver(vikariat[0], vikariat[1], vikariat[2], vikariat[3], vikariat[4],
                        vikariat[5], nyttVikariat);

                arbeidsgivere.add(nyArbeidsgiver);
            }
        }
    }

    public void reloadJobbsokerDatabase(){

        PrintWriter writer = null;
        try{
            FileWriter fileWriter = new FileWriter(Paths.JOBBSOKER+".csv", false);
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
            FileWriter fileWriter = new FileWriter(Paths.VIKARIAT+".csv", false);
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
