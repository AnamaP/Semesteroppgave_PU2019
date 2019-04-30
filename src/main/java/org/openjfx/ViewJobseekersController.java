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
import static logic.ViewJobseekerHelper.*;
import static logic.ViewTempJobsHelper.findTempJob;

public class ViewJobseekersController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    TableView<TableJobseekers> tvJobseekers;

    @FXML
    TableColumn<TableJobseekers, String> tcFirstname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo, tcEmail, tcAge,
            tcEducation, tcStudy, tcExperience, tcWorkfields, tcStatus;

    // Initialize klassen under gjør at tilhørende metoder automatisk blir kalt etter at fxml filen har blitt hentet.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SetTableHelper run = new SetTableHelper();
        run.setJobbseekerTable(tcFirstname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields);

        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tvJobseekers.setItems(showJobseekers());

        /* Muliggjør sortering og filtrering av data i tabellen*/

        FilteredList<TableJobseekers> filteredData = new FilteredList<>(showJobseekers(), p -> true);

        // bruker Listener til å fange opp endringer og ..
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(jobseeker -> {
            // hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (jobseeker.getFirstname().toLowerCase().contains(lowerCaseFilter) ||
                    jobseeker.getLastname().toLowerCase().contains(lowerCaseFilter) ||
                    jobseeker.getZipcode().toLowerCase().contains(lowerCaseFilter) ||
                    jobseeker.getPostal().toLowerCase().contains(lowerCaseFilter) ||
                    jobseeker.getPhoneNo().toLowerCase().contains(lowerCaseFilter) ||
                    jobseeker.getWorkfields().toLowerCase().contains(lowerCaseFilter) ||
                    jobseeker.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        SortedList<TableJobseekers> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvJobseekers.comparatorProperty());
        tvJobseekers.setItems(sortedData);

    }

    @FXML
    private void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    public void btnEditJobseeker(ActionEvent event) {
        try{
            String key = selectedPhoneNo(tvJobseekers);
            findJobseeker(key);

            // Load FXML
            URL url = getClass().getResource("/org/openjfx/regSoker.fxml");
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
                    run.reloadJobbsokerDatabase();
                }
                NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
            }
            else {
                // avbryter slettingen
            }
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du må velge en jobbsøker for å kunne slette!");
        }
    }

    public void btnDownloadJobseeker(ActionEvent event) {
        try{
            String key = selectedPhoneNo(tvJobseekers);
            ViewJobseekerHelper.saveJobseeker(key);
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du har ikke valgt en jobbsøker for nedlasting!");
        }
    }

    public void btnUploadJobseeker(ActionEvent event){
        FileChooserHelper.upload(Paths.JOBSEEKER);
        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnFindTempJob(ActionEvent event) {
        try {
            String workfieldsStr = tvJobseekers.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ViewHelper run = new ViewHelper();
            run.setValgteKategorier(workfieldsStr);

            String key = selectedPhoneNo(tvJobseekers);
            findTempJob(key);

            NavigationHelper.changePage("/org/openjfx/resultatVikariater.fxml", event);
        }
        catch(NullPointerException e){
            AlertHelper.showError("Du må velge en jobbsøker for å finne passende vikariat!");
        }
    }
}
