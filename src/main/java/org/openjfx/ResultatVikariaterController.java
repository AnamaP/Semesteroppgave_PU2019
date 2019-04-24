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

public class ResultatVikariaterController implements Initializable {

    @FXML
    private TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    private TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor, tcFirmanavn, tcBransje,
            tcStillingstittel, tcVarighet, tcKvalifikasjoner, tcKategorier;

    @FXML
    private Label lblMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcKontaktperson.setCellValueFactory(cellData->cellData.getValue().kontaktpersonProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcSektor.setCellValueFactory(cellData->cellData.getValue().sektorProperty());
        tcFirmanavn.setCellValueFactory(cellData->cellData.getValue().firmanavnProperty());
        tcBransje.setCellValueFactory(cellData->cellData.getValue().bransjeProperty());
        tcStillingstittel.setCellValueFactory(cellData->cellData.getValue().stillingstittelProperty());
        tcVarighet.setCellValueFactory(cellData->cellData.getValue().varighetProperty());
        tcKvalifikasjoner.setCellValueFactory(cellData->cellData.getValue().kvalifikasjonerProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tvOversiktVikariater.setItems(OversiktVikariaterHjelper.visResultat(Paths.VIKARIAT_CSV));
        setTableEditable();

        if(tvOversiktVikariater.getItems().isEmpty()){
            AlertHelper.showError("Det er dessverre ingen aktuelle kandidater for dette vikariatet per nå. \n" +
                                "Gå tilbake og velg et annet vikariat.");
        }
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til oversikten:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnAnsett(ActionEvent event) {

    }

    private void setTableEditable() {
        tvOversiktVikariater.setEditable(true);
    }
}
