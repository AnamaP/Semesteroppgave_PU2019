package org.openjfx.controllers;

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
import org.openjfx.model.logic.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.openjfx.model.logic.AlertHelper.showDeleteAlert;
import static org.openjfx.model.logic.FiltrationHelper.filtrateJobseekerTable;
import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.ViewJobseekersHelper.*;

public class ViewJobseekersController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    TableView<TableJobseekers> tvJobseekers;

    @FXML
    TableColumn<TableJobseekers, String> tcFirstname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                                         tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields, tcStatus;

    /**
     * 1: Om listen står tom vil denne meldingen gis til bruker.
     *
     *  2: setTempJobsTable initialiserer kolonnene.
     *
     *  3: "status" skal kun vises her i view og ikke når man får listen opp som et resultat etter å ha valgt
     *     en jobbutlysning. Siden det kun skal komme opp "ledige" jobbutlysninger uansett.
     *
     *  4: Denne fyller opp tabellen med jobbsøkere.
     *
     *  5: Her setter man ObservablaList inn i filterredData som muliggjør filtrering av data i tabellen.
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
        FilteredList<TableJobseekers> filteredContent = new FilteredList<>(showJobseekers(), p -> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredContent.setPredicate(jobseeker -> {
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
        SortedList<TableJobseekers> sortedContent = new SortedList<>(filteredContent);
        sortedContent.comparatorProperty().bind(tvJobseekers.comparatorProperty());
        tvJobseekers.setItems(sortedContent);
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    @FXML
    private void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/index.fxml", event);
    }

    /**
     * Kjører FileChooserHelper sin upload og reloader siden.
     */
    public void btnUploadJobseeker(ActionEvent event){
        FileChooserHelper.upload(Paths.JOBSEEKER);
        NavigationHelper.changePage("/org/openjfx/view/viewJobseekers.fxml", event);
    }

    /**
     * Finner valgt jobbsøker og kjører downloadJobseeker-metoden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDownloadJobseeker(ActionEvent event) {
        String out = "empty";
        String key = null;
        try{
            key = selectedPhoneNo(tvJobseekers);
            ViewJobseekersHelper.downloadJobseeker(key);
        }
        catch(NullPointerException e){
            out = "Du har ikke valgt en jobbsøker for nedlasting!";
        }
        if(key == null){
            AlertHelper.showError(out);
        }
    }

    /**
     * Henter ut valgt jobbsøker og spør bruker om hen er sikker på at hen vil slette.
     * Utfører slettingen og bruker trykker "ok". Etter sletting oppdaterer programmet csv-filen
     * og reloader siden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDeleteJobseeker(ActionEvent event){
        String message;
        try{
            message = tvJobseekers.getSelectionModel().getSelectedItem().getFirstname();
            message += " " + tvJobseekers.getSelectionModel().getSelectedItem().getLastname();
            Optional<ButtonType> result = showDeleteAlert(message);

            if (result.get() == ButtonType.OK) {
                // blir sletting gjennomført
                String key = selectedPhoneNo(tvJobseekers);
                ViewJobseekersHelper.deleteChosenJobseeker(key);

                MainAppHelper run = new MainAppHelper();
                run.reloadJobseekersDB();

                NavigationHelper.changePage("/org/openjfx/view/viewJobseekers.fxml", event);
            }
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du må velge en jobbsøker for å kunne slette!");
        }
    }

    /**
     * Finner valgt rad og oppretter det en ny "stage" bassert på regJobseeker-FXML'en. Kjører
     * setData()-metoden som setter verdiene til den valgte jobbsøkeren inn i tekstfeltene i FXML'en.
     */
    public void btnEditJobseeker(ActionEvent event) {
        try{
            String key = selectedPhoneNo(tvJobseekers);
            ViewHelper run = new ViewHelper();
            run.findRow(jobseekersList, key, true);

            URL url = getClass().getResource("/org/openjfx/view/regJobseeker.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent parent = null;
            try {
                parent = loader.load();
            } catch (IOException e){
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
     * Setter chosenWorkfields og chosenJobseeker via findRow() bassert på valgt jobbsøker.
     * Finner passende, ledige jobbsøkere.
     */
    public void btnFindTempJob(ActionEvent event) {
        try {
            String workfieldsStr = tvJobseekers.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ViewHelper run = new ViewHelper();
            run.setValgteKategorier(workfieldsStr);

            String key = selectedPhoneNo(tvJobseekers);
            run.findRow(jobseekersList, key, true);

            if(run.isAvailable(jobseekersList, chosenJobseeker)){
                NavigationHelper.changePage("/org/openjfx/view/matchTempJobs.fxml", event);
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
