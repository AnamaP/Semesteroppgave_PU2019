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

import static logic.FiltrationHelper.filtrateTempJobTable;
import static logic.RegTempJobHelper.tempJobsList;
import static logic.ViewTempJobsHelper.showResults;
import static logic.ViewTempJobsHelper.selectedPhoneNo;

public class MatchingTempJobsController implements Initializable {
    @FXML
    TextField txtFilterField;

    @FXML
    private TableView<TableTempJobs> tvTempJobs;

    @FXML
    private TableColumn<TableTempJobs, String> tcContactPerson, tcPhoneNo, tcSector, tcCompanyName,
            tcAddress,  tcIndustry, tcJobTitle, tcJobType, tcKWorkfields;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tvTempJobs.setPlaceholder(new Label("Det er dessverre ingen aktuelle vikariater som oppfyller dine kriterier "));

        SetTableHelper run = new SetTableHelper();
        run.setTempJobsTable(tcContactPerson, tcPhoneNo, tcSector, tcCompanyName,
                tcAddress,  tcIndustry, tcJobTitle, tcJobType, tcKWorkfields);

        tvTempJobs.setItems(ViewTempJobsHelper.showResults());

        // Muliggjør sortering og filtrering av data i tabellen.
        FilteredList<TableTempJobs> filteredData = new FilteredList<>(showResults(), p -> true);

        // Bruker Listener til å fange opp endringer.
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(tempJob -> {
            // Hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (filtrateTempJobTable(tempJob, lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        SortedList<TableTempJobs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvTempJobs.comparatorProperty());
        tvTempJobs.setItems(sortedData);
    }

    public void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = selectedPhoneNo(tvTempJobs);
        run.showMore(key);
    }

    public void btnEmploy(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = selectedPhoneNo(tvTempJobs);
        run.findRow(tempJobsList, key, false);
        run.employ();

        NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
    }
}
