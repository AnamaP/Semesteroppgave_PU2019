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

import static logikk.ViewHelper.chosenRow;
import static logikk.ViewJobseekerHelper.findJobseeker;
import static logikk.RegJobseekerHelper.jobseekersList;
import static logikk.RegTempJobHelper.tempJobsList;

public class MatchingTempJobsController implements Initializable {
    // TODO: Legge til ekstra felt som skal vises i guiresultatet
    @FXML
    private TableView<TableTempJobs> tvTempJobs;

    @FXML
    private TableColumn<TableTempJobs, String> tcContactPerson, tcPhoneNo, tcSector, tcCompanyName, tcIndustry,
            tcJobTitle, tcKWorkfields;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tvTempJobs.setPlaceholder(new Label("Det er dessverre ingen aktuelle kandidater for dette " +
                "vikariatet per nå. \n Gå tilbake og velg et annet vikariat."));

        tcContactPerson.setCellValueFactory(cellData->cellData.getValue().contactPersonProperty());
        tcPhoneNo.setCellValueFactory(cellData->cellData.getValue().phoneNoProperty());
        tcSector.setCellValueFactory(cellData->cellData.getValue().sectorProperty());
        tcCompanyName.setCellValueFactory(cellData->cellData.getValue().companyNameProperty());
        tcIndustry.setCellValueFactory(cellData->cellData.getValue().industryProperty());
        tcJobTitle.setCellValueFactory(cellData->cellData.getValue().jobTitleProperty());
        tcKWorkfields.setCellValueFactory(cellData->cellData.getValue().workfieldsProperty());

        tvTempJobs.setItems(ViewTempJobsHelper.showResults());
        setTableEditable();
    }

    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til oversikten:
        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        String key = tvTempJobs.getSelectionModel().getSelectedItem().phoneNoProperty().get();

        String title = ViewTempJobsHelper.readMoreTitle(key);
        String message = ViewTempJobsHelper.readMoreContent(key);

        AlertHelper.showMoreInfo(title,message);
    }

    public void btnEmploy(ActionEvent event) {
        String phoneNo = tvTempJobs.getSelectionModel().getSelectedItem().getPhoneNo();
        findJobseeker(phoneNo);

        tempJobsList.get(chosenRow).getTempJob().setStatus("Besatt");
        jobseekersList.get(chosenRow).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadVikariaterDatabase();
        reload.reloadJobbsokerDatabase();

        NavigationHelper.changePage("/org/openjfx/oversiktSokere.fxml", event);
    }

    private void setTableEditable() {
        tvTempJobs.setEditable(true);
    }
}
