package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logikk.NavigeringsHjelper;
import logikk.OversiktSokereHjelper;
import logikk.Paths;
import logikk.TabellSokere;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResultatSokereController implements Initializable {

    private List<String> kategorier;

    @FXML
    private TableView<TabellSokere> tvOversiktSoker;

    @FXML
    private TableColumn<TabellSokere, String> tcFornavn, tcEtternavn, tcAdresse, tcPostNr, tcPoststed, tcTlf, tcEpost, tcAlder,
            tcUtdanning, tcStudieretning, tcErfaring, tcKategorier;

    public List<String> getKategorier() {
        return kategorier;
    }

    public void setKategorier(List<String> kategorier) {
        this.kategorier = kategorier;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setTableEditable();
        //List<String> kategorier = getKategorier();

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

        tvOversiktSoker.setItems(OversiktSokereHjelper.visResultat(Paths.JOBBSOKER_CSV, kategorier));
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);

    }

    public void btnAnsett(ActionEvent event) {

    }

    private void setTableEditable() {
        tvOversiktSoker.setEditable(true);
    }
}
