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

public class MatchingTempJobsController implements Initializable {
    // TODO: Legge til ekstra felt som skal vises i guiresultatet
    @FXML
    private TableView<TableTempJobs> tvTempJobs;

    @FXML
    private TableColumn<TableTempJobs, String> tcContactPerson, tcPhoneNo, tcSector, tcCompanyName,
            tcAddress,  tcIndustry, tcJobTitle, tcJobType, tcKWorkfields;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tvTempJobs.setPlaceholder(new Label("Det er dessverre ingen aktuelle kandidater for dette " +
                "vikariatet per nå. \n Gå tilbake og velg et annet vikariat."));

        SetTableHelper run = new SetTableHelper();
        run.setTempJobsTable(tcContactPerson, tcPhoneNo, tcSector, tcCompanyName,
                tcAddress,  tcIndustry, tcJobTitle, tcJobType, tcKWorkfields);

        tvTempJobs.setItems(ViewTempJobsHelper.showResults());
    }

    public void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = run.selectedPhoneNoTempJobs(tvTempJobs);
        run.showMore(key);
    }

    public void btnEmploy(ActionEvent event) {
        ViewHelper run = new ViewHelper();
        String key = run.selectedPhoneNoTempJobs(tvTempJobs);
        run.employ(key);

        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
    }
}
