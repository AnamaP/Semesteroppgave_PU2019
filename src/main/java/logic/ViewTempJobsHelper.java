package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import classes.Company;

import java.io.*;
import java.util.ArrayList;

import static logic.ViewHelper.chosenRow;
import static logic.ViewHelper.chosenWorkfields;
import static logic.RegTempJobHelper.tempJobsList;

public class ViewTempJobsHelper {

    public static ObservableList<TableTempJobs> viewTempJobs() {
        // Oppretter en tabell
        ObservableList<TableTempJobs> tempJobList = FXCollections.observableArrayList();
        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.TEMPJOB +".csv"));
            String row;

            while ((row = csvreader.readLine()) != null){
                String [] columns = row.split(";");

                if(columns.length > 12){
                    ViewHelper run = new ViewHelper();
                    Company tempJob = run.getTempJobFromList(columns);

                    TableTempJobs tableTempJobs = new TableTempJobs(tempJob);
                    tempJobList.add(tableTempJobs);
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Får ikke lastet inn data");
        }
        catch(IOException e){
            System.err.println("NB! Her må en annen feilmld komme " + e.getCause());
        }
        return tempJobList;
    }

    public static ObservableList<TableTempJobs> showResults(){
        // Oppretter en tabell
        ObservableList<TableTempJobs> tempJobList = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.TEMPJOB +".csv"));
            String row;

            while ((row = csvreader.readLine()) != null){
                String [] columns = row.split(";");

                if(columns.length > 12) {
                    ViewHelper run = new ViewHelper();
                    //Henter ut en tempJob:
                    Company tempJob = run.getTempJobFromList(columns);

                    ArrayList<String> workfields = run.getWorkfields(columns, 12);

                    //Legger arbeidsgiveren i tabellen kun om en av kategoriene "matcher":
                    if (
                            ((workfields.toString().contains("Salg")) && chosenWorkfields.contains("Salg") ||
                                    (workfields.toString().contains("Admin")) && chosenWorkfields.contains("Admin") ||
                                    (workfields.toString().contains("It")) && chosenWorkfields.contains("It") ||
                                    (workfields.toString().contains("Okonomi")) && chosenWorkfields.contains("Okonomi"))
                            && columns[columns.length-1].equals("Ledig")

                    ) {
                        TableTempJobs tableTempJobs = new TableTempJobs(tempJob);
                        tempJobList.add(tableTempJobs);
                    }
                }
            }
            csvreader.close();
        }
        catch(FileNotFoundException e){
            System.err.println("Finner ikke filen du leter etter");
        }
        catch(IOException e){
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return tempJobList;
    }

    public static void deleteChosenTempJob(String key) {
        findTempJob(key);
        tempJobsList.remove(tempJobsList.get(chosenRow));
    }

    public static void saveTempJob(String key){
        findTempJob(key);
        FileChooserHelper.download(tempJobsList.get(chosenRow));

        //TODO : Feilmld til bruker om at vikariat ikke er valgt
    }

    public static String readMoreTitle(String key){
        findTempJob(key);
        return tempJobsList.get(chosenRow).getTempJob().getJobTitle();
    }

    public static String readMoreContent(String key){
        findTempJob(key);
        Company tempJob = tempJobsList.get(chosenRow);
        String out = "";
        out += "Beskrivelse: \n" + tempJob.getTempJob().getDescription() + "\n\n";
        out += "Kvalifikasjoner: \n" + tempJob.getTempJob().getQualif() + "\n\n";
        out += "Antatt årslønn: " + tempJob.getTempJob().getSalary() + "\n\n";
        out += "Varighet: " + tempJob.getTempJob().getDuration() + "\n\n";
        return out;
    }

    public static void findTempJob(String key){
        ViewHelper run = new ViewHelper();
        run.findRow(tempJobsList, key);
    }
}

