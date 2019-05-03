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

import static org.openjfx.model.logic.FiltrationHelper.filtrateTempJobTable;
import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;
import static org.openjfx.model.logic.ViewTempJobsHelper.*;

public class MatchingTempJobsController implements Initializable {
    @FXML
    TextField txtFilterField;

    @FXML
    private TableView<TableTempJobs> tvTempJobs;

    @FXML
    private TableColumn<TableTempJobs, String> tcContactPerson, tcPhoneNo, tcSector, tcCompanyName,
            tcAddress,  tcIndustry, tcJobTitle, tcJobType, tcKWorkfields;

    /**
     * Denne metoden har følgende punkter:
     *
     *  1: Om listen står tom vil denne meldingen gis til bruker. Dette skjer om filtreringen ikke finner noen
     *     matcher eller om man ikke finner noen matcher til en valgt jobbsøker.
     *
     *  2: setTempJobsTable initialiserer kolonnene.
     *
     *  3: Denne fyller opp tabellen med jobbutlysninger.
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
        tvTempJobs.setPlaceholder(new Label("Det er dessverre ingen aktuelle vikariater som oppfyller dine kriterier "));

        // 2:
        SetTableHelper run = new SetTableHelper();
        run.setTempJobsTable(tcContactPerson, tcPhoneNo, tcSector, tcCompanyName,
                tcAddress,  tcIndustry, tcJobTitle, tcJobType, tcKWorkfields);

        // 3:
        tvTempJobs.setItems(ViewTempJobsHelper.showResults());

        // 4:
        FilteredList<TableTempJobs> filteredData = new FilteredList<>(showResults(), p -> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(tempJob -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (filtrateTempJobTable(tempJob, lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        // 5:
        SortedList<TableTempJobs> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvTempJobs.comparatorProperty());
        tvTempJobs.setItems(sortedData);
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    public void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/viewJobseekers.fxml", event);
    }

    /**
     * Ved å trykke på denne knappen får brukeren opp mer informasjon om jobbutlysningen som vi ikke
     * så det nødvendig å presentere til alle tider. Dette gjorde GUI mye mer ryddig.
     * Programmet henter ut hvilken rad bruker har valgt og kjører readMore() metoden.
     * Dette vises til bruker i en allert-box. Om ingen rad er valgt vil bruker få en meldig om dette.
     */
    public void btnReadMore(ActionEvent event) {
        try {
            String key = selectedPhoneNo(tvTempJobs);
            readMore(key);
        }
        catch (NullPointerException e) { // TODO: Denne må håndteres med egendefinert avvik
            AlertHelper.showError("Du må velge et jobbutlysning for å lese mer!");
        }
    }

    /**
     * Ved å trykke på denne knappen finner programmet valgt rad og via emplot() finner den frem valgt jobbsøker og
     * setter nye verdier i "Status" til Ansatt / Besatt.
     */
    public void btnEmploy(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = selectedPhoneNo(tvTempJobs);
        run.findRow(tempJobsList, key, false);
        run.employ();

        NavigationHelper.changePage("/org/openjfx/view/viewJobseekers.fxml", event);
    }
}
