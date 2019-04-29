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


public class ResultatSokereController implements Initializable {

    @FXML
    private TableView<TabellSokere> tvOversiktSoker;


    @FXML
    private TableColumn<TabellSokere, String> tcFornavn, tcEtternavn, tcAdresse, tcPostNr, tcPoststed, tcTlf, tcEpost, tcAlder,
            tcUtdanning, tcStudieretning, tcErfaring, tcKategorier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     tvOversiktSoker.setPlaceholder(new Label("Det er dessverre ingen aktuelle vikariater for denne jobbsøkeren per nå" +
                                    "\n Forsøk igjen senere eller velg en annen jobbsøker"));

        tcFornavn.setCellValueFactory(cellData->cellData.getValue().fornavnProperty());
        tcEtternavn.setCellValueFactory(cellData->cellData.getValue().etternavnProperty());
        tcAdresse.setCellValueFactory(cellData->cellData.getValue().adresseProperty());
        tcPostNr.setCellValueFactory(cellData->cellData.getValue().postnrProperty());
        tcPoststed.setCellValueFactory(cellData->cellData.getValue().poststedProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcEpost.setCellValueFactory(cellData->cellData.getValue().epostProperty());
        tcAlder.setCellValueFactory(cellData->cellData.getValue().alderProperty());
        tcUtdanning.setCellValueFactory(cellData->cellData.getValue().utdanningProperty());
        tcStudieretning.setCellValueFactory(cellData->cellData.getValue().studieretningProperty());
        tcErfaring.setCellValueFactory(cellData->cellData.getValue().erfaringProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tvOversiktSoker.setItems(OversiktSokereHjelper.visResultat());
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til oversikten:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnAnsett(ActionEvent event) {
        String tlf = tvOversiktSoker.getSelectionModel().getSelectedItem().getTlf();
        findJobbsoker(tlf);

        arbeidsgivere.get(chosenRow).getVikariat().setStatus("Besatt");
        jobbsokere.get(chosenRow).setStatus("Ansatt");

        MainAppHelper reload = new MainAppHelper();
        reload.reloadVikariaterDatabase();
        reload.reloadJobbsokerDatabase();

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
    }
}
