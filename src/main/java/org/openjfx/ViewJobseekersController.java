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
import logikk.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static logikk.OversiktSokereHjelper.*;

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

        // Initierer kolonnene
        tcFirstname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        tcLastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        tcAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        tcZipcode.setCellValueFactory(cellData -> cellData.getValue().zipcodeProperty());
        tcPostal.setCellValueFactory(cellData -> cellData.getValue().postalProperty());
        tcPhoneNo.setCellValueFactory(cellData -> cellData.getValue().phoneNoProperty());
        tcEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        tcAge.setCellValueFactory(cellData -> cellData.getValue().ageProperty());
        tcEducation.setCellValueFactory(cellData -> cellData.getValue().educationProperty());
        tcStudy.setCellValueFactory(cellData -> cellData.getValue().studyProperty());
        tcExperience.setCellValueFactory(cellData -> cellData.getValue().experienceProperty());
        tcWorkfields.setCellValueFactory(cellData -> cellData.getValue().workfieldsProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tvJobseekers.setItems(visJobbsokere());


        /* Muliggjør sortering og filtrering av data i tabellen*/

        FilteredList<TableJobseekers> filteredData = new FilteredList<>(visJobbsokere(), p -> true);

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
        NavigationHelper.changePange("/org/openjfx/index.fxml", event);
    }

    public void btnEditJobseeker(ActionEvent event) {
        String key = tvJobseekers.getSelectionModel().getSelectedItem().getPhoneNo();
        findJobbsoker(key);

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

    public void btnDeleteJobseeker(ActionEvent event) {
        String message = tvJobseekers.getSelectionModel().getSelectedItem().getFirstname();
        message += " "+ tvJobseekers.getSelectionModel().getSelectedItem().getLastname();
        Alert question = new Alert(Alert.AlertType.CONFIRMATION);
        question.setHeaderText("Er du sikker på at du vil slette : ");
        question.setContentText(message +"?");
        Optional<ButtonType> result = question.showAndWait();

        if (result.get() == ButtonType.OK) {
            // blir sletting gjennomført
            String key = tvJobseekers.getSelectionModel().getSelectedItem().phoneNoProperty().get();

            Boolean deleted = RegSokerHjelper.slettValgtSoker(key);
            if (deleted) {
                MainAppHelper run = new MainAppHelper();
                run.reloadJobbsokerDatabase();
            }
            NavigationHelper.changePange("/org/openjfx/oversiktSokere.fxml", event);
        }
        else {
        // avbryter slettingen
    }

}

    public void btnDownloadJobseeker(ActionEvent event){
        String key = tvJobseekers.getSelectionModel().getSelectedItem().phoneNoProperty().get();
        OversiktSokereHjelper.saveJobseeker(key);
    }

    public void btnUploadJobseeker(ActionEvent event){
        FileChooserHjelper.lastOpp(Paths.JOBBSOKER);
        NavigationHelper.changePange("/org/openjfx/oversiktSokere.fxml", event);

    }

    public void btnFindTempJob(ActionEvent event){
        String workfieldsStr = tvJobseekers.getSelectionModel().getSelectedItem().workfieldsProperty().get();
        ArrayList<String> workfields = OversiktHjelper.stringToList(workfieldsStr);

        OversiktHjelper chosenWorkfields = new OversiktHjelper();
        chosenWorkfields.setValgteKategorier(workfields);

        String phoneNo = tvJobseekers.getSelectionModel().getSelectedItem().getPhoneNo();
        findJobbsoker(phoneNo);

        NavigationHelper.changePange("/org/openjfx/resultatVikariater.fxml", event);
    }
}
