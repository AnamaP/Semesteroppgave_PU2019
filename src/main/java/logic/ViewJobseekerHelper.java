package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import classes.Jobseeker;
import javafx.scene.control.TableView;

import java.io.*;
import java.util.ArrayList;

import static logic.ViewHelper.chosenWorkfields;
import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ViewTempJobsHelper.*;

public class ViewJobseekerHelper {


    public static int chosenJobseeker;

    public static ObservableList<TableJobseekers> showJobseekers() {

        // Oppretter en tabell
        ObservableList<TableJobseekers> jobseekerList = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBSEEKER +".csv"));
            String row;

            while ((row = csvreader.readLine()) != null){
                String [] columns = row.split(";");

                if(columns.length > 13){
                    ViewHelper run = new ViewHelper();
                    //Henter en jobbsoker fra listen:
                    Jobseeker jobseeker = run.getJobseekerFromList(columns);
                    //Legger søkeren til i tabellen:
                    TableJobseekers viewJobseekers = new TableJobseekers(jobseeker);
                    jobseekerList.add(viewJobseekers);
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("showJobseekers() : Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("showJobseekers() : Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return jobseekerList;
    }

    public static ObservableList<TableJobseekers> showResults(){
        // Oppretter en tabell
        ObservableList<TableJobseekers> showResults = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBSEEKER +".csv"));
            String row;

            while ((row = csvreader.readLine()) != null){
                String [] columns = row.split(";");

                if(columns.length > 13) {
                    ViewHelper run = new ViewHelper();
                    //Henter ut en jobbsøker fra listen:
                    Jobseeker jobseeker = run.getJobseekerFromList(columns);
                    //Henter ut jobbsokerens kategorier:
                    ArrayList<String> workfields = run.getWorkfields(columns,13);
                    //Finner antall felles kategorier
                    int amount = run.checkWorkfields(workfields);

                    //Om søkeren har mange nok kategorier legges den til i tabellen:
                    if((amount == chosenWorkfields.size()) && (columns[columns.length-1].equals("Ledig"))){
                        TableJobseekers viewJobseekers = new TableJobseekers(jobseeker);
                        showResults.add(viewJobseekers);
                    }
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("VisResultat() : Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("VisResultat() : Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return showResults;
    }

    public static void saveJobseeker(String key){
        findJobseeker(key);
        FileChooserHelper.download(jobseekersList.get(chosenJobseeker));
        //TODO : Feilmld til bruker om at jobbsoker ikke er valgt
    }

    public static void findJobseeker(String key){
        ViewHelper run = new ViewHelper();
        run.findRow(jobseekersList, key);
    }

    public static String selectedPhoneNo(TableView<TableJobseekers> tvTable){
        return tvTable.getSelectionModel().getSelectedItem().getPhoneNo();
    }
}
