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

import static logikk.OversiktSokereHjelper.findJobbsoker;
import static logikk.OversiktSokereHjelper.valgtJobbsoker;
import static logikk.OversiktVikariaterHjelper.valgtArbeidsgiver;
import static logikk.RegSokerHjelper.jobbsokere;
import static logikk.RegVikariatHjelper.arbeidsgivere;

public class ResultatVikariaterController implements Initializable {
    // TODO: Legge til ekstra felt som skal vises i guiresultatet
    @FXML
    private TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    private TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor, tcFirmanavn, tcBransje,
            tcStillingstittel, tcKategorier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tvOversiktVikariater.setPlaceholder(new Label("Det er dessverre ingen aktuelle kandidater for dette " +
                "vikariatet per nå. \n Gå tilbake og velg et annet vikariat."));

        tcKontaktperson.setCellValueFactory(cellData->cellData.getValue().kontaktpersonProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcSektor.setCellValueFactory(cellData->cellData.getValue().sektorProperty());
        tcFirmanavn.setCellValueFactory(cellData->cellData.getValue().firmanavnProperty());
        tcBransje.setCellValueFactory(cellData->cellData.getValue().bransjeProperty());
        tcStillingstittel.setCellValueFactory(cellData->cellData.getValue().stillingstittelProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tvOversiktVikariater.setItems(OversiktVikariaterHjelper.visResultat(Paths.VIKARIAT));
        setTableEditable();
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til oversikten:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnLesMer(ActionEvent event) {
        String key = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();

        String title = OversiktVikariaterHjelper.lesMerTittel(key);
        String message = OversiktVikariaterHjelper.lesMerInnhold(key);

        AlertHelper.showMoreInfo(title,message);
    }

    public void btnAnsett(ActionEvent event) {
        String tlf = tvOversiktVikariater.getSelectionModel().getSelectedItem().getTlf();
        findJobbsoker(tlf);

        arbeidsgivere.get(valgtArbeidsgiver).getVikariat().setStatus("Besatt");
        jobbsokere.get(valgtJobbsoker).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadVikariaterDatabase();
        reload.reloadJobbsokerDatabase();

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
    }

    private void setTableEditable() {
        tvOversiktVikariater.setEditable(true);
    }
}
