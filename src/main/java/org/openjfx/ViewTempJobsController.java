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

import static logikk.OversiktHjelper.chosenRow;
import static logikk.OversiktVikariaterHjelper.*;

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

        tcContactPerson.setCellValueFactory(cellData->cellData.getValue().contactPersonProperty());
        tcPhoneNo.setCellValueFactory(cellData->cellData.getValue().phoneNoProperty());
        tcSector.setCellValueFactory(cellData->cellData.getValue().sectorProperty());
        tcCompanyName.setCellValueFactory(cellData->cellData.getValue().companyNameProperty());
        tcAddress.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        tcIndustry.setCellValueFactory(cellData->cellData.getValue().industryProperty());
        tcJobTitle.setCellValueFactory(cellData->cellData.getValue().jobTitleProperty());
        tcJobType.setCellValueFactory(cellData->cellData.getValue().jobTypeProperty());
        tcWorkfields.setCellValueFactory(cellData->cellData.getValue().workfieldsProperty());
        tcStatus.setCellValueFactory(cellData->cellData.getValue().statusProperty());

        tvTempJobs.setItems(visVikariater());

        // Muliggjør sortering og filtrering av data i tabellen.
        FilteredList<TableTempJobs> filteredData = new FilteredList<>(visVikariater(), p-> true);

        // Bruker Listener til å fange opp endringer.
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(tempJob -> {
            // Hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises.
            if(newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(tempJob.getContactPerson().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getPhoneNo().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getSector().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getCompanyName().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getAddress().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getWorkfields().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getIndustry().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getJobTitle().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getJobType().toLowerCase().contains(lowerCaseFilter) ||
                    tempJob.getStatus().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
            return false;
        }));

        SortedList<TableTempJobs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvTempJobs.comparatorProperty());
        tvTempJobs.setItems(sortedData);
    }

    @FXML
    private void btnBack(ActionEvent event){
        NavigationHelper.changePange("/org/openjfx/index.fxml", event);
    }

    public void btnDownload(ActionEvent event) {
        String key = tvTempJobs.getSelectionModel().getSelectedItem().phoneNoProperty().get();
        OversiktVikariaterHjelper.saveTempJob(key);
    }

    public void btnUpload(ActionEvent event) {
        FileChooserHjelper.lastOpp(Paths.VIKARIAT);
        NavigationHelper.changePange("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        String key = tvTempJobs.getSelectionModel().getSelectedItem().phoneNoProperty().get();

        String title = OversiktVikariaterHjelper.lesMerTittel(key);
        String message = OversiktVikariaterHjelper.lesMerInnhold(key);

        AlertHelper.showMoreInfo(title,message);
    }

    public void btnEdit(ActionEvent event) throws IOException {
        String key = tvTempJobs.getSelectionModel().getSelectedItem().getPhoneNo();
        findArbeidsgiver(key);

        // Load FXML
        URL url = getClass().getResource("/org/openjfx/regVikariat.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent parent = loader.load();
        RegTempJobController controller = loader.getController();
        loader.setLocation(url);
        controller.setData(chosenRow);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(parent);
        stage.setScene(scene);

        //NavigeringsHjelper.changePange("/org/openjfx/regVikariat.fxml", event);

    }

    public void btnDelete(ActionEvent event) {
        // TODO: kontrollsjekke at man ikke kan registreres med duplikate tlf nr
        String message = tvTempJobs.getSelectionModel().getSelectedItem().getJobTitle();
        Alert question = new Alert(Alert.AlertType.CONFIRMATION);
        question.setHeaderText("Er du sikker på at du vil slette : ");
        question.setContentText(message + "?");
        Optional<ButtonType> result = question.showAndWait();

        if (result.get() == ButtonType.OK) {
            // utføres sletting
            String key = tvTempJobs.getSelectionModel().getSelectedItem().phoneNoProperty().get();
            OversiktVikariaterHjelper.slettValgtVikariat(key);

            MainAppHelper run = new MainAppHelper();
            run.reloadVikariaterDatabase();

            NavigationHelper.changePange("/org/openjfx/oversiktVikariater.fxml", event);
        }
        else {
            // Avbryter sletting..
        }
    }

    public void btnFindJobseekers(ActionEvent event) {
        String workfieldsStr = tvTempJobs.getSelectionModel().getSelectedItem().workfieldsProperty().get();
        ArrayList<String> workfields = OversiktHjelper.stringToList(workfieldsStr);

        OversiktHjelper chosenWorkfields = new OversiktHjelper();
        chosenWorkfields.setValgteKategorier(workfields);

        String phoneNo = tvTempJobs.getSelectionModel().getSelectedItem().getPhoneNo();
        findArbeidsgiver(phoneNo);

        NavigationHelper.changePange("/org/openjfx/resultatSokere.fxml", event);
    }
}

