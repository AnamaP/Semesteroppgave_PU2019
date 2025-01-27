package org.openjfx.controllers;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.openjfx.model.logic.*;
import java.net.URL;
import java.util.ResourceBundle;

import static org.openjfx.model.logic.FiltrationHelper.filtrateJobseekerTable;
import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.ViewJobseekersHelper.selectedPhoneNo;
import static org.openjfx.model.logic.ViewJobseekersHelper.showResults;

public class MatchingJobseekersController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    private TableView<TableJobseekers> tvJobseekers;

    @FXML
    private TableColumn<TableJobseekers, String> tcFistname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
            tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields;


    /**
     * Denne metoden har følgende punkter:
     *
     *  1: Om listen står tom vil denne meldingen gis til bruker. Dette skjer om filtreringen ikke finner noen
     *     matcher eller om man ikke finner noen matcher til en valgt jobbutlysning.
     *
     *  2: setTempJobsTable initialiserer kolonnene.
     *
     *  3: Denne fyller opp tabellen med jobbsøkere.
     *
     *  4: Her setter man ObservablaList inn i filterredData som muliggjør filtrering av data i tabellen.
     *     Programmet bruker Listener til å fange opp endringer. Hvis det ikke er skrevet noe inn i filteret så skal
     *     all informasjon vises og om noe skrives inn skriver den kun ut de elementene som inneholder dette.
     *
     *  5: Legger sortert og filtrert liste inn i tabellen.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1:
        tvJobseekers.setPlaceholder(new Label("Det er dessverre ingen aktuelle kandidater som oppfyller dine kriterier"));

        // 2:
        SetTableHelper run = new SetTableHelper();
        run.setJobbseekerTable(tcFistname, tcLastname, tcAddress, tcZipcode, tcPostal, tcPhoneNo,
                tcEmail, tcAge, tcEducation, tcStudy, tcExperience, tcWorkfields);

        // 3:
        tvJobseekers.setItems(showResults());

        // 4:
        FilteredList<TableJobseekers> filteredContent = new FilteredList<>(showResults(), p -> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredContent.setPredicate(jobseeker -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (filtrateJobseekerTable(jobseeker, lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        // 5:
        SortedList<TableJobseekers> sortedContent = new SortedList<>(filteredContent);
        sortedContent.comparatorProperty().bind(tvJobseekers.comparatorProperty());
        tvJobseekers.setItems(sortedContent);
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    public void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml", event);
    }

    /**
     * Ved å trykke på denne knappen finner programmet valgt rad og via emplot() finner den frem valgt
     * jobbutlysning og setter nye verdier i "Status" til Ansatt / Besatt.
     */
    public void btnEmploy(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = selectedPhoneNo(tvJobseekers);
        run.findRow(jobseekersList, key, true);
        run.employ();

        NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml", event);
    }
}
