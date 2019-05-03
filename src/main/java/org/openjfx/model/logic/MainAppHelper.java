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
     * Henter ut alle jobbsøkere fra csv-filen og legger dem inn i en ArrayList.
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
     * Henter ut alle jobbutlysningene fra csv-filen og legger dem inn i en ArrayList.
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
     * Tar alt som ligger i en ArrayList og skriver den inn i en csv-fil, overskriver / oppdaterer det som ligger der.
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
