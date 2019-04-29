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

    public void loadDatabaseFromCsv(){
        loadJobbsokerCsv();
        loadVikariatCsv();
    }

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

    public void reloadJobbsokerDatabase(){

        PrintWriter writer = null;
        try{
            FileWriter fileWriter = new FileWriter(Paths.JOBSEEKER +".csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int a = 0; a < jobseekersList.size(); a++) {
                printWriter.println(jobseekersList.get(a));  //New line
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
            FileWriter fileWriter = new FileWriter(Paths.TEMPJOB +".csv", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(int a = 0; a < tempJobsList.size(); a++) {
                printWriter.println(tempJobsList.get(a));  //New line
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
