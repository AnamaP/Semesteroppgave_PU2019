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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static logic.ViewTempJobsHelper.*;

public class ViewTempJobsController implements Initializable {

    @FXML
    private TextField txtFilterField;

    @FXML
    private TableView<TableTempJobs> tvTempJobs;

    @FXML
    private TableColumn<TableTempJobs, String> tcContactPerson, tcPhoneNo, tcSector, tcCompanyName, tcAddress, tcIndustry,
            tcJobTitle, tcJobType, tcWorkfields, tcStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SetTableHelper run = new SetTableHelper();
        run.setTempJobsTable(tcContactPerson, tcPhoneNo, tcSector, tcCompanyName, tcAddress, tcIndustry,
                tcJobTitle, tcJobType, tcWorkfields);

        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tvTempJobs.setItems(viewTempJobs());

        // Muliggjør sortering og filtrering av data i tabellen.
        FilteredList<TableTempJobs> filteredData = new FilteredList<>(viewTempJobs(), p -> true);

        // Bruker Listener til å fange opp endringer.
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(tempJob -> {
            // Hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (tempJob.getContactPerson().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getPhoneNo().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getSector().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getCompanyName().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getAddress().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getWorkfields().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getIndustry().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getJobTitle().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getJobType().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        SortedList<TableTempJobs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvTempJobs.comparatorProperty());
        tvTempJobs.setItems(sortedData);
    }

    @FXML
    private void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    public void btnDownload(ActionEvent event) {
        String key;
        try {
            ViewHelper run = new ViewHelper();
            key = run.selectedPhoneNoTempJobs(tvTempJobs);
            ViewTempJobsHelper.saveTempJob(key);
        } catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å kunne laste ned!");
        }
    }

    public void btnUpload(ActionEvent event) {
        FileChooserHelper.upload(Paths.TEMPJOB);
        NavigationHelper.changePage("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        String key;
        try {
            ViewHelper run = new ViewHelper();
            key = run.selectedPhoneNoTempJobs(tvTempJobs);

            String title = ViewTempJobsHelper.readMoreTitle(key);
            String message = ViewTempJobsHelper.readMoreContent(key);

            AlertHelper.showMoreInfo(title, message);
        } catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å lese mer!");
        }
    }

    public void btnEdit(ActionEvent event) throws IOException {
        String key;
        try {
            ViewHelper run = new ViewHelper();
            key = run.selectedPhoneNoTempJobs(tvTempJobs);
            findTempJob(key);

            // Load FXML
            URL url = getClass().getResource("/org/openjfx/regVikariat.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent parent = loader.load();
            RegTempJobController controller = loader.getController();
            loader.setLocation(url);
            controller.setData(chosenTempJob);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);

            //NavigeringsHjelper.changePage("/org/openjfx/regVikariat.fxml", event);
        } catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å kunne redigere!");
        }
    }

    public void btnDeleteChosenTempJob(ActionEvent event) {
        String message;
        try {
            // TODO: kontrollsjekke at man ikke kan registreres med duplikate tlf nr
            message = tvTempJobs.getSelectionModel().getSelectedItem().getJobTitle();
            Alert question = new Alert(Alert.AlertType.CONFIRMATION);
            question.setHeaderText("Er du sikker på at du vil slette : ");
            question.setContentText(message + "?");
            Optional<ButtonType> result = question.showAndWait();

            if (result.get() == ButtonType.OK) {
                // utføres sletting
                ViewHelper run = new ViewHelper();
                String key = run.selectedPhoneNoTempJobs(tvTempJobs);
                ViewTempJobsHelper.deleteChosenTempJob(key);

                MainAppHelper run1 = new MainAppHelper();
                run1.reloadVikariaterDatabase();

                NavigationHelper.changePage("/org/openjfx/oversiktVikariater.fxml", event);
            } else {
                // Avbryter sletting..
            }
        } catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å kunne slette det!");
        }
    }

    public void btnFindJobseekers(ActionEvent event) {
        String workfieldsStr;
        try {
            workfieldsStr = tvTempJobs.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ArrayList<String> workfields = ViewHelper.stringToList(workfieldsStr);

            ViewHelper chosenWorkfields = new ViewHelper();
            chosenWorkfields.setValgteKategorier(workfields);

            ViewHelper run = new ViewHelper();
            String phoneNo = run.selectedPhoneNoTempJobs(tvTempJobs);
            findTempJob(phoneNo);

            NavigationHelper.changePage("/org/openjfx/resultatSokere.fxml", event);
        } catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å finne passende jobbsøker!");
        }
    }
}
