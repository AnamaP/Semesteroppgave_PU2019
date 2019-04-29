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

        tvTempJobs.setItems(OversiktVikariaterHjelper.visResultat());
        setTableEditable();
    }

    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til oversikten:
        NavigationHelper.changePange("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnReadMore(ActionEvent event) {
        String key = tvTempJobs.getSelectionModel().getSelectedItem().phoneNoProperty().get();

        String title = OversiktVikariaterHjelper.lesMerTittel(key);
        String message = OversiktVikariaterHjelper.lesMerInnhold(key);

        AlertHelper.showMoreInfo(title,message);
    }

    public void btnEmploy(ActionEvent event) {
        String phoneNo = tvTempJobs.getSelectionModel().getSelectedItem().getPhoneNo();
        findJobbsoker(phoneNo);

        arbeidsgivere.get(chosenRow).getVikariat().setStatus("Besatt");
        jobbsokere.get(chosenRow).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadVikariaterDatabase();
        reload.reloadJobbsokerDatabase();

        NavigationHelper.changePange("/org/openjfx/oversiktSokere.fxml", event);
    }

    private void setTableEditable() {
        tvTempJobs.setEditable(true);
    }
}
