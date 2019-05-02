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
     * Denne metoden har følgende punkter:
     *  1: Om listen står tom vil denne meldingen gis til bruker. Dette skjer om filtreringen ikke finner noen
     *     matcher eller om man ikke finner noen matcher til en valgt jobbsøker.
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
     *  5: Her setter man ObservablaList inn i filteredData som muliggjør sortering og filtrering av data i tabellen.
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
        FilteredList<TableTempJobs> filteredData = new FilteredList<>(viewTempJobs(), p -> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(tempJob -> {
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
        SortedList<TableTempJobs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvTempJobs.comparatorProperty());
        tvTempJobs.setItems(sortedData);
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    @FXML
    private void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/index.fxml", event);
    }

    /**
     * Om denne knappen blir trykket kjører programmet upload-metoden via FilChoserHelper-klassen.
     * TEMPJOB sier hvor den opplastede jobbutlysningen skal lagres. Å kjøre changePage til
     * siden man er på gjør at siden reloader.
     */
    public void btnUpload(ActionEvent event) {
        FileChooserHelper.upload(Paths.TEMPJOB);
        NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml", event);
    }

    /**
     * Om denne knappen blir trykket finner hvilken jobbutlysning som bruker har valgt og kjører den
     * igjennom slett-metoden. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnDownload(ActionEvent event) {
        try {
            String key = selectedPhoneNo(tvTempJobs);
            ViewTempJobsHelper.saveTempJob(key);
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et jobbutlysning for å kunne laste ned!");
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
     * Om man trykker på denne knappen henter programmet ut den valgte jobbutlysning's tittel og
     * spør deg i en alert box om du virkelig ønsker å slette utlysningen med denne tittelen.
     * Om bruker trykker "Ok" vil programmet hente ut en nøkkel som gjør at slette-metoden vet hvilken
     * utlysningn den skal fjerne fra jobbutlysnings-listen. Etter sletting oppdaterer programmet csv-filen
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
     * Det skjer følgende ting når denne knappen trykkes:
     * Først henter programmet ut tlfnr fra valgt jobbutlysning. Dette nummeret brukes for å finne ut av hvilken rad
     * valgt jobbutlysningen er i listen av utlysninger, dette skjer i findRow-metoden. Etter dette opprettes det en
     * ny "stage" bassert på regTempJob-FXML'en. Har kjøres setData()-metoden som setter verdiene til jobbutlysningen
     * inn i tekstfeltene i FXML'en. Om ingen rad er valgt vil bruker få en meldig om dette.
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
     * Om man trykker på denne knappen henter programmet ut valgte arbeidsområder fra jobbutlysningen
     * og setter dem til chosenWorkfields. findRow()-metoden finner hvilken rad den valgte utlysningen
     * er i listen og setter det nummeret til chosenTempJob. Dette gjør at man kan filtrere hvilke
     * jobbsøkrer som skal vises i resultater og om det opprettes et arbeidsforhold så får man tak i
     * hvilken utlysning som ble valgt her. Om ingen rad er valgt vil bruker få en meldig om dette.
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

