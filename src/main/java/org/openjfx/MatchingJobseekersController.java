package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import logic.*;
import java.net.URL;
import java.util.ResourceBundle;

import static logic.FiltrationHelper.filtrateJbseekerTable;
import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ViewJobseekerHelper.selectedPhoneNo;
import static logic.ViewJobseekerHelper.showResults;

public class MatchingJobseekersController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    private TableView<TableJobseekers> tvJobseekers;

    @FXML
    private TableColumn<TableJobseekers, String> tcFistname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
            tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //1:
        tvJobseekers.setPlaceholder(new Label("Det er dessverre ingen aktuelle kandidater som oppfyller dine kriterier"));

        SetTableHelper run = new SetTableHelper();
        run.setJobbseekerTable(tcFistname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields);

        tvJobseekers.setItems(showResults());

        FilteredList<TableJobseekers> filteredData = new FilteredList<>(showResults(), p -> true);

        // bruker Listener til å fange opp endringer og ..
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(jobseeker -> {
            // hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (filtrateJbseekerTable(jobseeker, lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        SortedList<TableJobseekers> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvJobseekers.comparatorProperty());
        tvJobseekers.setItems(sortedData);
    }

    public void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml", event);
    }

    public void btnEmploy(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = selectedPhoneNo(tvJobseekers);
        run.findRow(jobseekersList, key, true);
        run.employ();

        NavigationHelper.changePage("/org/openjfx/viewTempJobs.fxml", event);
    }
}
