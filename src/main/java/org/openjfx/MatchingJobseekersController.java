package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logikk.*;
import java.net.URL;
import java.util.ResourceBundle;
import static logikk.OversiktHjelper.chosenRow;
import static logikk.OversiktSokereHjelper.findJobbsoker;
import static logikk.RegSokerHjelper.jobbsokere;
import static logikk.RegVikariatHjelper.arbeidsgivere;


public class MatchingJobseekersController implements Initializable {

    @FXML
    private TableView<TableJobseekers> tvJobseekers;


    @FXML
    private TableColumn<TableJobseekers, String> tcFistname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
            tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     tvJobseekers.setPlaceholder(new Label("Det er dessverre ingen aktuelle vikariater for denne jobbsøkeren per nå" +
                                    "\n Forsøk igjen senere eller velg en annen jobbsøker"));

        tcFistname.setCellValueFactory(cellData->cellData.getValue().firstnameProperty());
        tcLastname.setCellValueFactory(cellData->cellData.getValue().lastnameProperty());
        tcAddress.setCellValueFactory(cellData->cellData.getValue().addressProperty());
        tcZipcode.setCellValueFactory(cellData->cellData.getValue().zipcodeProperty());
        tcPostal.setCellValueFactory(cellData->cellData.getValue().postalProperty());
        tcPhoneNo.setCellValueFactory(cellData->cellData.getValue().phoneNoProperty());
        tcEmail.setCellValueFactory(cellData->cellData.getValue().emailProperty());
        tcAge.setCellValueFactory(cellData->cellData.getValue().ageProperty());
        tcEducation.setCellValueFactory(cellData->cellData.getValue().educationProperty());
        tcStudy.setCellValueFactory(cellData->cellData.getValue().studyProperty());
        tcExperience.setCellValueFactory(cellData->cellData.getValue().experienceProperty());
        tcWorkfields.setCellValueFactory(cellData->cellData.getValue().workfieldsProperty());

        tvJobseekers.setItems(OversiktSokereHjelper.visResultat());
    }

    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til oversikten:
        NavigationHelper.changePange("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnEmploy(ActionEvent event) {
        String phoneNo = tvJobseekers.getSelectionModel().getSelectedItem().getPhoneNo();
        findJobbsoker(phoneNo);

        arbeidsgivere.get(chosenRow).getVikariat().setStatus("Besatt");
        jobbsokere.get(chosenRow).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadVikariaterDatabase();
        reload.reloadJobbsokerDatabase();

        NavigationHelper.changePange("/org/openjfx/oversiktVikariater.fxml", event);
    }
}
