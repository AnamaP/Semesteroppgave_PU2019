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

import static logic.ViewJobseekerHelper.*;

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
        String key = "";
        if (key == ""){
            AlertHelper.showError("Du må velge en jobbsøker for å kunne redigere!");
        }
        else {
            key = tvJobseekers.getSelectionModel().getSelectedItem().getPhoneNo();
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
    }

    public void btnDeleteJobseeker(ActionEvent event) {
        String message = "";
        if (message == ""){
            AlertHelper.showError("Du må velge en jobbsøker for å kunne slette!");
        }
        else {
            message = tvJobseekers.getSelectionModel().getSelectedItem().getFirstname();
            message += " " + tvJobseekers.getSelectionModel().getSelectedItem().getLastname();
            Alert question = new Alert(Alert.AlertType.CONFIRMATION);
            question.setHeaderText("Er du sikker på at du vil slette : ");
            question.setContentText(message + "?");
            Optional<ButtonType> result = question.showAndWait();

            if (result.get() == ButtonType.OK) {
                // blir sletting gjennomført
                String key = tvJobseekers.getSelectionModel().getSelectedItem().phoneNoProperty().get();

                Boolean deleted = RegJobseekerHelper.deleteChosenJobseeker(key);
                if (deleted) {
                    MainAppHelper run = new MainAppHelper();
                    run.reloadJobbsokerDatabase();
                }
                NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
            } else {
                // avbryter slettingen
            }
        }
}

    public void btnDownloadJobseeker(ActionEvent event) {
       // TODO : bør det håndteres med en exception også her ??
        String key = "";
        if (key == ""){
            AlertHelper.showError("Du har ikke valgt en jobbsøker for nedlasting!");
        }
        else {
            key = tvJobseekers.getSelectionModel().getSelectedItem().phoneNoProperty().get();
            ViewJobseekerHelper.saveJobseeker(key);
        }

    }

    public void btnUploadJobseeker(ActionEvent event){
        FileChooserHelper.upload(Paths.JOBSEEKER);
        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);

    }

    public void btnFindTempJob(ActionEvent event) {
        String workfieldsStr = "";
        if (workfieldsStr == "") {
            AlertHelper.showError("Du må velge en jobbsøker for å finne passende vikariat!");
        } else {
            workfieldsStr = tvJobseekers.getSelectionModel().getSelectedItem().workfieldsProperty().get();
            ArrayList<String> workfields = ViewHelper.stringToList(workfieldsStr);

            ViewHelper chosenWorkfields = new ViewHelper();
            chosenWorkfields.setValgteKategorier(workfields);

            String phoneNo = tvJobseekers.getSelectionModel().getSelectedItem().getPhoneNo();
            findJobseeker(phoneNo);

            NavigationHelper.changePage("/org/openjfx/resultatVikariater.fxml", event);
        }
    }
}
