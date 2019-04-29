package logic;

import classes.Company;
import classes.Cv;
import classes.Jobseeker;
import classes.TempJob;

import java.util.ArrayList;

public class ViewHelper {

    public static int chosenRow;
    public static ArrayList<String> chosenWorkfields;

    public void setValgteKategorier(ArrayList<String> chosenWorkfields) {
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

    public void findRow(ArrayList arrayList, String key){
        for(int i = 0; i < arrayList.size(); i++){
            String [] row = arrayList.get(i).toString().split(";");
            for(int j = 0; j < row.length; j++){
                if(row[j].equals(key)){
                    chosenRow = i;
                }
            }
        }
    }

    public int checkWorkfields(ArrayList arrayList){
        int amount = 0;
        for(int i = 0; i < chosenWorkfields.size(); i++) {
            if (arrayList.toString().contains(chosenWorkfields.get(i))) {
                amount++;
            }
        }
        return amount;
    }

    public Jobseeker getJobseekerFromList(String [] columns){
        ArrayList<String> workfields = getWorkfields(columns, 13);
        Cv cv = new Cv(columns[9], columns[10], columns[11], workfields);

        Jobseeker jobseeker = new Jobseeker(columns[0], columns[1], columns[2], columns[3], columns[4],
                                            columns[5], columns[6], columns[7], cv, columns[columns.length-1]);
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
        for (int i = start; i < columns.length -1; i++) {
            workfields.add(columns[i]);
        }
        return workfields;
    }
}
