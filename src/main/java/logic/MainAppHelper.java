package logic;

import fileHandling.CsvFileHandler;
import classes.Company;
import classes.Cv;
import classes.Jobseeker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static logic.RegJobseekerHelper.jobseekersList;
import static logic.RegTempJobHelper.tempJobsList;

public class MainAppHelper {

    private static MainAppHelper instance;

    public static MainAppHelper getMainAppHelper(){
        if(instance == null){
            instance = new MainAppHelper();
        }
        return instance;
    }

    /**
     * Denne kjører begge load-metodene.
     */
    public void loadDatabaseFromCsv(){
        loadJobbsokerCsv();
        loadVikariatCsv();
    }


    /**
     * Denne metoden henter ut alle jobbsøkere fra csv-filen og legger dem inn i en ArrayList.
     * Dette gjør at man har en "kopi" av listen tilgjengelig som kan forandres, før man samkjører
     * den med csv-filen. Csv-filen fungerer litt som en database / back up.
     */
    public void loadJobbsokerCsv(){
        CsvFileHandler test = new CsvFileHandler();
        String sokereFraDatabase = (String) test.readFromFile(Paths.JOBSEEKER);

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
                    cv.setReference(soker[12]);
                }

                Jobseeker jobseeker = new Jobseeker(soker[0], soker[1], soker[2], soker[3], soker[4],
                        soker[5], soker[6], soker[7], cv, soker[soker.length - 1]);

                // hvis lønnskrav er satt så...
                if(soker[8] != ""){
                    jobseeker.setSalary(soker[8]);
                }

                jobseekersList.add(jobseeker);
            }
        }
    }

    /**
     * Denne metoden henter ut alle jobbutlysningene fra csv-filen og legger dem inn i en ArrayList.
     * Dette gjør at man har en "kopi" av listen tilgjengelig som kan forandres, før man samkjører
     * den med csv-filen. Csv-filen fungerer litt som en database / back up.
     * getTempJobFromList, tar inn en String [] og returnerer et Company.
     */
    public void loadVikariatCsv(){
        CsvFileHandler test = new CsvFileHandler();
        String vikariaterFraDatabase = (String) test.readFromFile(Paths.TEMPJOB);

        String [] rader = vikariaterFraDatabase.split("\n");

        for (int i = 0; i < rader.length; i++) {
            String[] arbeidsgiver = rader[i].split(";");

            if(arbeidsgiver.length > 13) {
                ViewHelper run = new ViewHelper();
                Company nyCompany = run.getTempJobFromList(arbeidsgiver);

                tempJobsList.add(nyCompany);
            }
        }
    }

    public void reloadJobseekersDB(){
        reloadDatabase(Paths.JOBSEEKER +".csv", jobseekersList);
    }

    public void reloadTempJobsDB(){
        reloadDatabase(Paths.TEMPJOB +".csv", tempJobsList);
    }

    /**
     * Denne metoden tar alt som ligger i en ArrayList (enten en liste med jobbsøkere eller jobbutlysninger) og
     * skriver den inn i en csv-fil. Her er append satt til false slik at listen i filen vil bli fullstendig oppdatert.
     * Denne brukes hver gang en rad i en av listene endres eller slettes.
     */
    private void reloadDatabase(String path, ArrayList arrayList){
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(int a = 0; a < arrayList.size(); a++) {
            printWriter.println(arrayList.get(a));
            System.out.println("Liste: "+arrayList.get(a));
        }
        printWriter.close();
    }
}
