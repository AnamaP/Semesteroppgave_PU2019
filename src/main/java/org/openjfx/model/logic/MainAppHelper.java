package org.openjfx.model.logic;

import org.openjfx.model.fileHandling.CsvFileHandler;
import org.openjfx.model.dataClasses.Company;
import org.openjfx.model.dataClasses.Jobseeker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;

public class MainAppHelper {

    /**
     * Denne kjører begge load-metodene.
     */
    public void loadDBFromCsv(){
        loadJobseekerCsv();
        loadTempJobCsv();
    }

    /**
     * Denne metoden henter ut alle jobbsøkere fra csv-filen og legger dem inn i en ArrayList.
     * Dette gjør at man har en "kopi" av listen tilgjengelig som kan forandres, før man samkjører
     * den med csv-filen. Csv-filen fungerer litt som en database / back up. getJobseekerFromList lager
     * en jobseeker utifra en oppgitt String [].
     */
    public void loadJobseekerCsv(){
        CsvFileHandler test = new CsvFileHandler();
        String sokereFraDatabase = (String) test.readFromFile(Paths.JOBSEEKER);

        String [] rader = sokereFraDatabase.split("\n");

        for (int i = 0; i < rader.length; i++) {
            String[] columns = rader[i].split(";");

            if(columns.length > 14) {
                ViewHelper run = new ViewHelper();
                Jobseeker jobseeker = run.getJobseekerFromList(columns);
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
    public void loadTempJobCsv(){
        CsvFileHandler handler = new CsvFileHandler();
        String tempJobsFromDB = (String) handler.readFromFile(Paths.TEMPJOB);

        String [] rader = tempJobsFromDB.split("\n");

        for (int i = 0; i < rader.length; i++) {
            String[] columns = rader[i].split(";");

            if(columns.length > 13) {
                ViewHelper run = new ViewHelper();
                Company nyCompany = run.getTempJobFromList(columns);
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
        }
        printWriter.close();
    }
}
