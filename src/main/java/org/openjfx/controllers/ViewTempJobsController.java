package org.openjfx.controllers;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.openjfx.model.logic.*;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.openjfx.model.logic.AlertHelper.showDeleteAlert;
import static org.openjfx.model.logic.FiltrationHelper.filtrateTempJobTable;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;
import static org.openjfx.model.logic.ViewTempJobsHelper.*;

public class ViewTempJobsController implements Initializable {

    @FXML
    private TextField txtFilterField;

    @FXML
    private TableView<TableTempJobs> tvTempJobs;

    @FXML
    private TableColumn<TableTempJobs, String> tcContactPerson, tcPhoneNo, tcSector, tcCompanyName, tcAddress,
                                               tcIndustry, tcJobTitle, tcJobType, tcWorkfields, tcStatus;

    /**
     * 1: Om listen står tom vil denne meldingen gis til bruker.
     *
     *  2: setTempJobsTable initialiserer kolonnene.
     *
     *  3: "status" skal kun vises her i view og ikke når man får listen opp som et resultat etter å ha valgt
     *     en jobbsøker. Siden det kun skal komme opp "ledige" jobbutlysninger uansett, så vil dette være overflødig. Det å
     *     initialisere denne kolonnen utenfor metoden gjør at vi kan kalle på samme metode i kontrolleren der programmet
     *     viser resultater.
     *
     *  4: Denne fyller opp tabellen med jobbutlysninger.
     *
     *  5: Her setter man ObservablaList inn i filteredContent som muliggjør filtrering av data i tabellen.
     *     Programmet bruker Listener til å fange opp endringer. Hvis det ikke er skrevet noe inn i filteret så skal
     *     all informasjon vises og om noe skrives inn skriver den kun ut de elementene som inneholder dette.
     *
     *  6: Legger sortert og filtrert liste inn i tabellen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1:
        tvTempJobs.setPlaceholder(new Label("Obs! Ingen treff som passer søket ditt!"));

        // 2:
        SetTableHelper run = new SetTableHelper();
        run.setTempJobsTable(tcContactPerson, tcPhoneNo, tcSector, tcCompanyName, tcAddress,
                             tcIndustry, tcJobTitle, tcJobType, tcWorkfields);

        // 3:
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        // 4:
        tvTempJobs.setItems(viewTempJobs());

        // 5:
        FilteredList<TableTempJobs> filteredContent = new FilteredList<>(viewTempJobs(), p -> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredContent.setPredicate(tempJob -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (filtrateTempJobTable(tempJob, lowerCaseFilter) || tempJob.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        // 6:
        SortedList<TableTempJobs> sortedContent = new SortedList<>(filteredContent);
        sortedContent.comparatorProperty().bind(tvTempJobs.comparatorProperty());
        tvTempJobs.setItems(sortedContent);
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
    public void btnUpload(ActionEvent event) {
        FileChooserHelper.upload(Paths.TEMPJOB);
        NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml", event);
    }

    /**
     * Finner valgt jobbutlysning og kjører downloadTempJob-metoden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDownload(ActionEvent event) {
        String out = "empty";
        String key = null;
        try {
            key = selectedPhoneNo(tvTempJobs);
            ViewTempJobsHelper.saveTempJob(key);
        }
        catch (NullPointerException e) {
            out = "Du må velge et jobbutlysning for å lese mer!";
        }
        if(key == null){
            AlertHelper.showError(out);
        }
    }

    /**
     * Ved å trykke på denne knappen får brukeren opp mer informasjon om jobbutlysningen som vi ikke
     * så det nødvendig å presentere til alle tider. Dette gjorde GUI mye mer ryddig.
     * Programmet henter ut hvilken rad bruker har valgt og kjører readMore() metoden.
     * Dette vises til bruker i en allert-box. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnReadMore(ActionEvent event) {
        try {
            String key = selectedPhoneNo(tvTempJobs);
            readMore(key);
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et jobbutlysning for å lese mer!");
        }
    }

    /**
     * Henter ut valgt jobbutlysning og spør bruker om hen er sikker på at hen vil slette.
     * Utfører slettingen og bruker trykker "ok". Etter sletting oppdaterer programmet csv-filen
     * og reloader siden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDeleteChosenTempJob(ActionEvent event) {
        String message;
        try {
            message = tvTempJobs.getSelectionModel().getSelectedItem().getJobTitle();
            Optional<ButtonType> result = showDeleteAlert(message);

            if (result.get() == ButtonType.OK) {
                String key = selectedPhoneNo(tvTempJobs);
                ViewTempJobsHelper.deleteChosenTempJob(key);

                MainAppHelper reload = new MainAppHelper();
                reload.reloadTempJobsDB();

                NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml", event);
            }
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et jobbutlysning for å kunne slette det!");
        }
    }

    /**
     * Finner valgt rad og oppretter det en ny "stage" bassert på regTempJob-FXML'en. Kjører
     * setData()-metoden som setter verdiene til den valgte jobbutlysningen inn i tekstfeltene i FXML'en.
     */
    public void btnEdit(ActionEvent event) throws IOException {
        try {
            String key = selectedPhoneNo(tvTempJobs);
            ViewHelper run = new ViewHelper();
            run.findRow(tempJobsList, key, false);

            URL url = getClass().getResource("/org/openjfx/view/regTempJob.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent parent = loader.load();
            RegTempJobController controller = loader.getController();
            loader.setLocation(url);
            controller.setData(chosenTempJob);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et jobbutlysning for å kunne redigere!");
        }
    }

    /**
     * Setter chosenWorkfields og chosenJobseeker via findRow() bassert på valgt jobbutlysning.
     * Finner passende, ledige jobbsøkere.
     */
    public void btnFindJobseekers(ActionEvent event) {
        try {
            String workfieldsStr = tvTempJobs.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ViewHelper run = new ViewHelper();
            run.setValgteKategorier(workfieldsStr);

            String key = selectedPhoneNo(tvTempJobs);
            run.findRow(tempJobsList, key, false);

            if(run.isAvailable(tempJobsList, chosenTempJob)){
                NavigationHelper.changePage("/org/openjfx/view/matchJobseekers.fxml", event);
            }
            else {
                AlertHelper.showError("Vikariatet er besatt. Veld et ledig vikariat før du går videre.");
            }
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et jobbutlysning for å finne passende jobbsøker!");
        }
    }
}

