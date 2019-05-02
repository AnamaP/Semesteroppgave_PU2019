package org.openjfx.model.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.openjfx.model.dataClasses.Company;
import javafx.scene.control.TableView;
import java.io.*;
import java.util.ArrayList;
import static org.openjfx.model.logic.ViewHelper.chosenWorkfields;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;

public class ViewTempJobsHelper {

    /**
     * Denne oppdateres kontinuelig ettersom brukeren velger en jobbutlysning.
     */
    public static int chosenTempJob;

    /**
     * Denne metoden oppretter en ObservableList tabell, henter ut alle jobbutlysningene som ligger i
     * databasen / csv-filen, gjør dem om til objekter og legger dem inn i listen.
     */
    public static ObservableList<TableTempJobs> viewTempJobs() {
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
        catch(IOException e){ // TODO: Håndteres med egendefinert avvik?
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return tempJobList;
    }

    /**
     * Legger jobbsøkeren i tabellen kun om en av arbeidsområdene til utlysningen "matcher" jobbsøkerens adbeidsområder.
     */
    public static ObservableList<TableTempJobs> showResults(){
        ObservableList<TableTempJobs> tempJobList = FXCollections.observableArrayList();
        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.TEMPJOB +".csv"));
            String row;

            while ((row = csvreader.readLine()) != null){
                String [] columns = row.split(";");

                if(columns.length > 12) {
                    ViewHelper run = new ViewHelper();
                    Company tempJob = run.getTempJobFromList(columns);

                    ArrayList<String> workfields = run.getWorkfields(columns);
                    if (((workfields.toString().contains("Salg")) && chosenWorkfields.contains("Salg") ||
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
        catch(IOException e){ // TODO: Håndteres med egendefinert avvik?
            System.err.println("Klarer ikke å lese fra ønsket fil. Feilmelding : " + e.getCause());
        }
        return tempJobList;
    }

    /**
     * Finner valgt rad og sletter denne fra listen.
     */
    public static void deleteChosenTempJob(String key) {
        ViewHelper run = new ViewHelper();
        run.findRow(tempJobsList, key, false);
        tempJobsList.remove(tempJobsList.get(chosenTempJob));
    }

    /**
     * Finner valgt rad og laster denne ned via download() metoden.
     */
    public static void saveTempJob(String key){
        ViewHelper run = new ViewHelper();
        run.findRow(tempJobsList, key, false);
        FileChooserHelper.download(tempJobsList.get(chosenTempJob));
    }

    /**
     * Henter ut en tittel og formaterer en melding ut til bruker.
     */
    public static void readMore(String key){
        String title = ViewTempJobsHelper.readMoreTitle(key);
        String message = ViewTempJobsHelper.readMoreContent(key);

        AlertHelper.showMoreInfo(title,message);
    }

    /**
     * Finner valgt rad og returnerer radens stillingstittel.
     */
    public static String readMoreTitle(String key){
        ViewHelper run = new ViewHelper();
        run.findRow(tempJobsList, key, false);
        return tempJobsList.get(chosenTempJob).getTempJob().getJobTitle();
    }

    /**
     * Finner valgt rad og returnerer tillegsinformasjon om vikariatet.
     */
    public static String readMoreContent(String key){
        ViewHelper run = new ViewHelper();
        run.findRow(tempJobsList, key, false);
        Company tempJob = tempJobsList.get(chosenTempJob);
        String out = "";
        out += "Beskrivelse: \n" + tempJob.getTempJob().getDescription() + "\n\n";
        out += "Kvalifikasjoner: \n" + tempJob.getTempJob().getQualif() + "\n\n";
        out += "Antatt årslønn: " + tempJob.getTempJob().getSalary() + "\n\n";
        out += "Varighet: " + tempJob.getTempJob().getDuration() + "\n\n";
        return out;
    }

    /**
     * Denne metoden tar inn en tabell og henter ut valgt vikariat sitt tlfnr, bruker som ID.
     */
    public static String selectedPhoneNo(TableView<TableTempJobs> tvTable){
        return tvTable.getSelectionModel().getSelectedItem().getPhoneNo();
    }
}