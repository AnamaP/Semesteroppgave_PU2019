package org.openjfx.model.logic;

import org.openjfx.model.dataClasses.Company;
import org.openjfx.model.dataClasses.Cv;
import org.openjfx.model.dataClasses.Jobseeker;
import org.openjfx.model.dataClasses.TempJob;
import java.util.ArrayList;
import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;
import static org.openjfx.model.logic.ViewJobseekersHelper.*;
import static org.openjfx.model.logic.ViewTempJobsHelper.*;

public class ViewHelper{

    /**
     * Oppdateres etter som bruker har valgt en søker / utlysning slik at man kan sortere på arbeidsområder.
     */
    public static ArrayList<String> chosenWorkfields;

    /**
     * Henter inn en liste med arbeidsområder, gjør den om til en ArrayList via stringToList og setter chosenWorkfields.
     */
    public void setValgteKategorier(String workfieldsStr) {
        ArrayList<String> chosenWorkfields = ViewHelper.stringToList(workfieldsStr);
        this.chosenWorkfields = chosenWorkfields;
    }

    /**
     * Tar inn en string og gjør den om til en arrayList ved å dele den opp ved ",".
     */
    private static ArrayList<String> stringToList(final String input) {
        String[] elements = input.split(", ");
        ArrayList<String> result = new ArrayList<>(elements.length);
        for (String item : elements) {
            result.add(String.valueOf(item));
        }
        return result;
    }

    /**
     * Setter valgt rad.
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

    /**
     * Denne tar inn en String [] og gjør den om til en jobbsøker.
     */
    public Jobseeker getJobseekerFromList(String [] columns){
        ArrayList<String> workfields = getWorkfields(columns);
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

    /**
     * Denne tar inn en String [] og gjør den om til en jobbutlysning.
     */
    public Company getTempJobFromList(String [] columns){
        ArrayList<String> workfields = getWorkfields(columns);
        TempJob tempJob = new TempJob(columns[6],columns[7],columns[8],columns[9],
                                         columns[10], columns[11], workfields, columns[columns.length-1]);

        Company company = new Company(columns[0], columns[1],columns[2],
                                                     columns[3],columns[4], columns[5], tempJob);
        return company;
    }

    /**
     * Henter arbeidsområder fra en liste. Kategorier ligger nest sist i uansett type liste.
     */
    public ArrayList getWorkfields(String [] columns){
        ArrayList<String> workfields = new ArrayList<>();
        String[] workfieldsStr = columns[columns.length -2].split(",");
        for (int i = 0; i < workfieldsStr.length; i++) {
            workfields.add(workfieldsStr[i]);
        }
        return workfields;
    }

    /**
     * Denne her tar to lister, sjekker hvor mange elementer de har til felles og returnerer dette.
     */
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

    /**
     * Denne sjekker om søkeren / utlysningen er ledig.
     */
    public boolean isAvailable(ArrayList arrayList, int row){
        String [] check = arrayList.get(row).toString().split(";");
        System.out.println(check[check.length-1]);
        if(check[check.length-1].equals("Ledig")){
            return true;
        }
        return false;
    }

    /**
     * Denne setter status til valgt jobbsøker og valgt jobbutlysning til å bli ansatt / besatt.
     */
    public void employ(){
        tempJobsList.get(chosenTempJob).getTempJob().setStatus("Besatt");
        jobseekersList.get(chosenJobseeker).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadTempJobsDB();
        reload.reloadJobseekersDB();
    }
}
