package logic;

import classes.Company;
import classes.Cv;
import classes.Jobseeker;
import classes.TempJob;

import java.util.ArrayList;

import static logic.RegJobseekerHelper.jobseekersList;
import static logic.RegTempJobHelper.tempJobsList;
import static logic.ViewJobseekerHelper.*;
import static logic.ViewTempJobsHelper.*;

public class ViewHelper{

    public static ArrayList<String> chosenWorkfields;

    public void setValgteKategorier(String workfieldsStr) {
        ArrayList<String> chosenWorkfields = ViewHelper.stringToList(workfieldsStr);
        this.chosenWorkfields = chosenWorkfields;
    }

    public static ArrayList<String> stringToList(final String input) {
        String[] elements = input.split(", ");
        ArrayList<String> result = new ArrayList<>(elements.length);
        for (String item : elements) {
            result.add(String.valueOf(item));
        }
        return result;
    }

    /**
     * Metoden finner hvilken rad i hvilken liste basert på et nøkkelelement.
     * Arraylisten inneholder hele tabellen. Den deles inn i rader og det sjekkes om nøkkelen finnes i en av radene.
     * Om den finnes, finner man ut av hvilken type objekt det er og setter den valgte raden inn i enten
     * "chosenTempJob" eller "chosenJobseeker".
     */
    public void findRow(ArrayList arrayList, String key, Boolean isJobseeker){
        for(int i = 0; i < arrayList.size(); i++){
            String [] row = arrayList.get(i).toString().split(";");
            for(int j = 0; j < row.length; j++){
                if(row[j].equals(key)){
                    if(isJobseeker){
                        chosenJobseeker = i;
                    }
                    else {
                        chosenTempJob = i;
                    }
                }
            }
        }
    }

    public Jobseeker getJobseekerFromList(String [] columns){
        ArrayList<String> workfields = getWorkfields(columns, 13);
        Cv cv = new Cv(columns[9], columns[10], columns[11], workfields);

        // hvis referanse er satt så...
        if (columns[12] != "") {
            cv.setReference(columns[12]);
        }

        Jobseeker jobseeker = new Jobseeker(columns[0], columns[1], columns[2], columns[3], columns[4],
                                            columns[5], columns[6], columns[7], cv, columns[columns.length-1]);

        // hvis lønnskrav er satt så...
        if (columns[8] != "") {
            jobseeker.setSalary(columns[8]);
        }

        return jobseeker;
    }

    public Company getTempJobFromList(String [] columns){
        ArrayList<String> workfields = getWorkfields(columns, 12);
        TempJob tempJob = new TempJob(columns[6],columns[7],columns[8],columns[9],
                                         columns[10], columns[11], workfields, columns[columns.length-1]);

        Company company = new Company(columns[0], columns[1],columns[2],
                                                     columns[3],columns[4], columns[5], tempJob);
        return company;
    }

    public ArrayList getWorkfields(String [] columns, int start){
        ArrayList<String> workfields = new ArrayList<>();
        String[] workfieldsStr = columns[start].split(",");
        for (int i = 0; i < workfieldsStr.length; i++) {
            workfields.add(workfieldsStr[i]);
        }
        return workfields;
    }

    public int checkWorkfields(ArrayList arrayList){
        int amount = 0;
        for(int i = 0; i < chosenWorkfields.size(); i++) {
            if (arrayList.toString().contains(chosenWorkfields.get(i))) {
                amount++;
            }
        }
        System.out.println(amount);
        return amount;
    }

    public void employ(){
        tempJobsList.get(chosenTempJob).getTempJob().setStatus("Besatt");
        jobseekersList.get(chosenJobseeker).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadTempJobsDB();
        reload.reloadJobseekersDB();
    }

    public void showMore(String key){
        String title = ViewTempJobsHelper.readMoreTitle(key);
        String message = ViewTempJobsHelper.readMoreContent(key);

        AlertHelper.showMoreInfo(title,message);
    }
}
