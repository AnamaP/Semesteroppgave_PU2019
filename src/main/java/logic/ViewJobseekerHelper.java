package logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import classes.Jobseeker;
import javafx.scene.control.TableView;
import java.io.*;
import java.util.ArrayList;
import static logic.ViewHelper.chosenWorkfields;
import static logic.RegJobseekerHelper.jobseekersList;

public class ViewJobseekerHelper {

    /**
     * Denne oppdateres kontinuelig ettersom brukeren velger en jobbsøker. Dette gjør at valgt søker kan
     * hentes ut og brukes på hvilken som helst side.
     */
    public static int chosenJobseeker;

    /**
     * Denne metoden oppretter en ObservableList tabell, henter ut alle jobbsokere som ligger i
     * databasen / csv-filen, gjør dem om til objekter og legger dem inn i tabellen. Sender passende
     * feilmelding itl bruker om noe går galt.
     */
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

    /**
     * Likt som metoden over bare at man henter ut arbeidsområder og sjekker om de passer til de som
     * ble satt da man valgte en jobbsøker å sortere etter. Legger jobbsøkeren i tabbellen kun om den
     * inneholder alle arbeidsområdene utlysningen ser etter.
     */
    public static ObservableList<TableJobseekers> showResults(){
        // Oppretter en tabell
        ObservableList<TableJobseekers> showResults = FXCollections.observableArrayList();

        try{
            BufferedReader csvreader = new BufferedReader(new FileReader(Paths.JOBSEEKER +".csv"));
            String row;

            while ((row = csvreader.readLine()) != null){
                String [] columns = row.split(";");

                if(columns.length > 14) {
                    ViewHelper run = new ViewHelper();
                    //Henter ut en jobbsøker fra listen:
                    Jobseeker jobseeker = run.getJobseekerFromList(columns);
                    //Henter ut jobbsokerens kategorier:
                    ArrayList<String> workfields = run.getWorkfields(columns,13);
                    //Finner antall felles kategorier
                    int amount = run.checkWorkfields(workfields);

                    //Om søkeren har mange nok kategorier legges den til i tabellen:
                    System.out.println(columns[columns.length-1]);
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

    /**
     * Denne metoden får inn en "nøkkel" og via findRow() metoden finner man riktig rad og
     * laster denne ned via download() metoden.
     */
    public static void saveJobseeker(String key){
        ViewHelper run = new ViewHelper();
        run.findRow(jobseekersList, key, true);
        FileChooserHelper.download(jobseekersList.get(chosenJobseeker));
    }

    /**
     * Denne metoden tar inn en tabell og henter ut valgt vikariat sitt tlfnr. Dette brukes ofte
     * som "key" for å finne hvilken rad bruker har valgt.
     */
    public static String selectedPhoneNo(TableView<TableJobseekers> tvTable){
        return tvTable.getSelectionModel().getSelectedItem().getPhoneNo();
    }
}