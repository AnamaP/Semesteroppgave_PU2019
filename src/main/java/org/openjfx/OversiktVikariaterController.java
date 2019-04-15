package org.openjfx;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import klasser.Arbeidsgiver;
import logikk.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OversiktVikariaterController implements Initializable {

    @FXML
    TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor, tcFirmanavn, tcBransje,
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
        setTableEditable();
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnRedigerVikariat(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/regVikariat.fxml", event);
    }

    public void btnSlettVikariat(ActionEvent event) {
        //ObservableList<TabellVikariater> valgtVikariat, alleVikariater;
        //alleVikariater = tvOversiktVikariater.getItems();
        //valgtVikariat = tvOversiktVikariater.getSelectionModel().getSelectedItems();
        String nokkel = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();

        System.out.println(nokkel);

        //valgtVikariat.forEach(alleVikariater::remove);

        Boolean test = RegVikariatHjelper.slettValgtVikariat(nokkel);
        System.out.println(test);
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnLastNedVikariatr(ActionEvent event) {
        lastNed(Paths.VIKARIAT_CSV);
    }

    public void btnLastOppVikariat(ActionEvent event) {

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
    }

    private void setTableEditable() {
        tvOversiktVikariater.setEditable(true);
        //Denne gjør at man kan velge kun én rute.
        //tvOversiktVikariater.getSelectionModel().cellSelectionEnabledProperty().set(true);
    }
}

