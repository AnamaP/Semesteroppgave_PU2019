package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static logic.AlertHelper.showDeleteAlert;
import static logic.FiltrationHelper.filtrateJobseekerTable;
import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ViewJobseekerHelper.*;

public class ViewJobseekersController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    TableView<TableJobseekers> tvJobseekers;

    @FXML
    TableColumn<TableJobseekers, String> tcFirstname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                                         tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields, tcStatus;

    /**
     * Denne metoden har følgende punkter:
     *  1: Om listen står tom vil denne meldingen gis til bruker. Dette skjer om filtreringen ikke finner noen
     *     matcher eller om man ikke finner noen matcher til en valgt jobbutlysning.
     *
     *  2: setTempJobsTable initialiserer kolonnene.
     *
     *  3: "status" skal kun vises her i view og ikke når man får listen opp som et resultat etter å ha valgt
     *     en jobbutlysning. Siden det kun skal komme opp "ledige" jobbutlysninger uansett, så vil dette være overflødig.
     *     Det å initialisere denne kolonnen utenfor metoden gjør at vi kan kalle på samme metode i kontrolleren
     *     hvor programmet kun viser resultater.
     *
     *  4: Denne fyller opp tabellen med jobbsøkere.
     *
     *  5: Her setter man ObservablaList inn i filterredData som muliggjør sortering og filtrering av data i tabellen.
     *     Programmet bruker Listener til å fange opp endringer. Hvis det ikke er skrevet noe inn i filteret så skal
     *     all informasjon vises og om noe skrives inn skriver den kun ut de elementene som inneholder dette.
     *
     *  6: Legger sortert og filtrert liste inn i tabellen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1:
        tvJobseekers.setPlaceholder(new Label("Obs! Ingen treff som passer søket ditt!"));

        // 2:
        SetTableHelper run = new SetTableHelper();
        run.setJobbseekerTable(tcFirstname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields);

        // 3:
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // 4:
        tvJobseekers.setItems(showJobseekers());

        // 5:
        FilteredList<TableJobseekers> filteredData = new FilteredList<>(showJobseekers(), p -> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(jobseeker -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (filtrateJobseekerTable(jobseeker, lowerCaseFilter) || jobseeker.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        // 6:
        SortedList<TableJobseekers> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvJobseekers.comparatorProperty());
        tvJobseekers.setItems(sortedData);
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    @FXML
    private void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    /**
     * Om denne knappen blir trykket kjører programmet upload-metoden via FilChoserHelper-klassen.
     * JOBSEEKER sier hvor den opplastede jobbsøkeren skal lagres. Å kjøre changePage til
     * siden man er på gjør at siden reloader.
     */
    public void btnUploadJobseeker(ActionEvent event){
        FileChooserHelper.upload(Paths.JOBSEEKER);
        NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
    }

    /**
     * Om denne knappen blir trykket finner hvilken jobbutlysning som bruker har valgt og kjører den
     * igjennom slett-metoden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDownloadJobseeker(ActionEvent event) {
        try{
            String key = selectedPhoneNo(tvJobseekers);
            ViewJobseekerHelper.saveJobseeker(key);
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du har ikke valgt en jobbsøker for nedlasting!");
        }
    }

    /**
     * Om man trykker på denne knappen henter programmet ut den valgte jobbsøkers's navn og
     * spør deg i en alert box om du virkelig ønsker å slette søkeren med denne tittelen.
     * Om bruker trykker "Ok" vil programmet hente ut en nøkkel som gjør at slette-metoden vet hvilken
     * jobbsøker den skal fjerne fra jobbsøker-listen. Etter sletting oppdaterer programmet csv-filen
     * og reloader siden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDeleteJobseeker(ActionEvent event) {
        String message;
        try{
            message = tvJobseekers.getSelectionModel().getSelectedItem().getFirstname();
            message += " " + tvJobseekers.getSelectionModel().getSelectedItem().getLastname();
            Optional<ButtonType> result = showDeleteAlert(message);

            if (result.get() == ButtonType.OK) {
                // blir sletting gjennomført
                String key = selectedPhoneNo(tvJobseekers);
                Boolean deleted = RegJobseekerHelper.deleteChosenJobseeker(key);

                if (deleted) {
                    MainAppHelper run = new MainAppHelper();
                    run.reloadJobseekersDB();
                }
                NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
            }
            else {
                // avbryter slettingen
            }
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du må velge en jobbsøker for å kunne slette!");
        }
    }

    /**
     * Det skjer følgende ting når denne knappen trykkes:
     * Først henter programmet ut tlfnr fra valgt jobbsøker. Dette nummeret brukes for å finne ut av hvilken rad
     * valgt jobbsøker er i listen av søkere, dette skjer i findRow-metoden. Etter dette opprettes det en
     * ny "stage" bassert på regJobseeker-FXML'en. Har kjøres setData()-metoden som setter verdiene til den valgte
     * jobbsøkeren inn i tekstfeltene i FXML'en. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnEditJobseeker(ActionEvent event) {
        try{
            String key = selectedPhoneNo(tvJobseekers);
            ViewHelper run = new ViewHelper();
            run.findRow(jobseekersList, key, true);

            URL url = getClass().getResource("/org/openjfx/regJobseeker.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            RegJobseekerController controller = loader.getController();
            loader.setLocation(url);
            controller.setData();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du må velge en jobbsøker for å kunne redigere!");
        }
    }

    /**
     * Om man trykker på denne knappen henter programmet ut valgte arbeidsområder fra jobbsøkeren
     * og setter dem til chosenWorkfields. findRow()-metoden finner hvilken rad den valgte utlysningen
     * er i listen og setter det nummeret til chosenJobseeker. Dette gjør at man kan filtrere hvilke
     * jobbutøysninger som skal vises i resultater og om det opprettes et arbeidsforhold så får man tak i
     * hvilken søker som ble valgt her via chosenJobseeker. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnFindTempJob(ActionEvent event) {
        try {
            String workfieldsStr = tvJobseekers.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ViewHelper run = new ViewHelper();
            run.setValgteKategorier(workfieldsStr);

            String key = selectedPhoneNo(tvJobseekers);
            run.findRow(jobseekersList, key, true);

            if(run.isAvailable(jobseekersList, chosenJobseeker)){
                NavigationHelper.changePage("/org/openjfx/matchTempJobs.fxml", event);
            }
            else{
                AlertHelper.showError("Jobbsøkeren er i arbeid. Veld en ledig jobbsøker før du går videre.");
            }
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du må velge en jobbsøker for å finne passende vikariat!");
        }
    }
}
