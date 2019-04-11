package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logikk.NavigeringsHjelper;
import logikk.OversiktVikariaterHjelper;
import logikk.Paths;
import logikk.TabellVikariater;
import java.net.URL;
import java.util.ResourceBundle;

public class OversiktVikariaterController implements Initializable {

    @FXML
    TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor,tcFirmanavn, tcBransje,
            tcStillingstittel, tcVarighet, tcStillingstype, tcKategorier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcKontaktperson.setCellValueFactory(cellData->cellData.getValue().kontaktpersonProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcSektor.setCellValueFactory(cellData->cellData.getValue().sektorProperty());
        tcFirmanavn.setCellValueFactory(cellData->cellData.getValue().firmanavnProperty());
        tcBransje.setCellValueFactory(cellData->cellData.getValue().bransjeProperty());
        tcStillingstittel.setCellValueFactory(cellData->cellData.getValue().stillingstittelProperty());
        tcVarighet.setCellValueFactory(cellData->cellData.getValue().varighetProperty());
        tcStillingstype.setCellValueFactory(cellData->cellData.getValue().stillingstypeProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tvOversiktVikariater.setItems(OversiktVikariaterHjelper.visVikariater(Paths.VIKARIAT_CSV));
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}

