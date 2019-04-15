package org.openjfx;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logikk.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OversiktSokereController implements Initializable {
    @FXML
    TableView<TabellSokere> tvOversiktSoker;

    @FXML
    TableColumn<TabellSokere, String> tcFornavn, tcEtternavn, tcAdresse, tcPostNr, tcPoststed, tcTlf, tcEpost, tcAlder,
                                      tcUtdanning, tcStudieretning, tcErfaring, tcKategorier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        var items = OversiktSokereHjelper.visJobbsokere(Paths.JOBBSOKER_CSV);
        tvOversiktSoker.setItems(OversiktSokereHjelper.visJobbsokere(Paths.JOBBSOKER_CSV));
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnRedigerSoker(ActionEvent event) {
        // TODO : her skal det kalles på metode som redigerer jobbsøker
    }

    public void btnSlettSoker(ActionEvent event) {
        // TODO : her skal det kalles på metode som sletter jobbsøker
    }

    public void btnLastNedSoker(ActionEvent event) {
        lastNed(Paths.JOBBSOKER_CSV);
    }

    public void btnLastOppSoker(ActionEvent event) {
        // TODO : her skal det kalles på FileChooser metode
    }

    public void btnFinnVikariater(ActionEvent event){
        // TODO : her skal det kalles på en metode som finner aktuelle vikariater for jobbsøker
    }

    private void lastNed(String csvPath) {
        Filhandterer filHandterer;
        String chosenpath = FileChooserHjelper.fileChooser();

        // Metode som sjekker hvilket filformat bruker har valgt, henter ut riktig fil med innhold
        String extension = Filhandterer.getExtention(chosenpath);

        if(extension.equals(".csv")) {
            filHandterer = new CsvFilhandterer();
            try {
                filHandterer.lagreFilLokalt(chosenpath, csvPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*
        else {
            filHandterer = new JobjFilhandterer();
            try {
                ArrayList<Arbeidsgiver> arbeidsgivere = RegVikariatHjelper.arbeidsgivere;
                filHandterer.lagreFilLokalt(chosenpath, jobjPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
