package logikk;

import filbehandling.CsvFilhandterer;
import klasser.Cv;
import klasser.Jobbsoker;

import java.util.ArrayList;

import static logikk.RegSokerHjelper.jobbsokere;

public class MainAppHelper {

    public void loadDatabase(){
        //TODO: Denne skal via "lese fra fil" hente ut hva som ligger i de to csv-filene og legge innholdet inn i de statiske arrayene.

        loadJobbsokerCsv();



    }

    public void loadJobbsokerCsv(){
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

        }
    }
}
