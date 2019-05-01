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
import static logic.FiltrationHelper.filtrateTempJobTable;
import static logic.RegTempJobHelper.tempJobsList;
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

        tvTempJobs.setPlaceholder(new Label("Obs! Ingen treff som passer søket ditt!"));

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
            if (filtrateTempJobTable(tempJob, lowerCaseFilter) || tempJob.getStatus().toLowerCase().contains(lowerCaseFilter)) {
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
        try {
            String key = selectedPhoneNo(tvTempJobs);
            ViewTempJobsHelper.saveTempJob(key);
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å kunne laste ned!");
        }
    }

    public void btnUpload(ActionEvent event) {
        FileChooserHelper.upload(Paths.TEMPJOB);
        NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        try {
            String key = selectedPhoneNo(tvTempJobs);

            String title = ViewTempJobsHelper.readMoreTitle(key);
            String message = ViewTempJobsHelper.readMoreContent(key);

            AlertHelper.showMoreInfo(title, message);
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å lese mer!");
        }
    }

    public void btnEdit(ActionEvent event) throws IOException {
        try {
            String key = selectedPhoneNo(tvTempJobs);
            ViewHelper run = new ViewHelper();
            run.findRow(tempJobsList, key, false);

            // Load FXML
            URL url = getClass().getResource("/org/openjfx/regTempJob.fxml");
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
            AlertHelper.showError("Du må velge et vikariat for å kunne redigere!");
        }
    }

    public void btnDeleteChosenTempJob(ActionEvent event) {
        String message;
        try {
            message = tvTempJobs.getSelectionModel().getSelectedItem().getJobTitle();
            Optional<ButtonType> result = showDeleteAlert(message);

            if (result.get() == ButtonType.OK) {
                // utføres sletting
                String key = selectedPhoneNo(tvTempJobs);
                ViewTempJobsHelper.deleteChosenTempJob(key);

                MainAppHelper reload = new MainAppHelper();
                reload.reloadTempJobsDB();

                NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml", event);
            }
            else {
                // Avbryter sletting..
            }
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å kunne slette det!");
        }
    }

    public void btnFindJobseekers(ActionEvent event) {
        try {
            String workfieldsStr = tvTempJobs.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ViewHelper run = new ViewHelper();
            run.setValgteKategorier(workfieldsStr);

            String key = selectedPhoneNo(tvTempJobs);
            run.findRow(tempJobsList, key, false);

            NavigationHelper.changePage("/org/openjfx/matchJobseekers.fxml", event);
        }
        catch (NullPointerException e) {
            AlertHelper.showError("Du må velge et vikariat for å finne passende jobbsøker!");
        }
    }
}

