package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logic.*;
import java.net.URL;
import java.util.ResourceBundle;

import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ViewJobseekerHelper.selectedPhoneNo;


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

        SetTableHelper run = new SetTableHelper();
        run.setJobbseekerTable(tcFistname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields);

        tvJobseekers.setItems(ViewJobseekerHelper.showResults());
    }

    public void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnEmploy(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = selectedPhoneNo(tvJobseekers);
        run.findRow(jobseekersList, key, true);
        run.employ();

        NavigationHelper.changePage("/org/openjfx/oversiktVikariater.fxml", event);
    }
}
