package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import logikk.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OversiktVikariaterController implements Initializable {

    @FXML
    private TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    private TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor, tcFirmanavn, tcOrgNr, tcBransje,
            tcStillingstittel, tcStillingstype, tcVarighet, tcLonn, tcKvalifikasjoner, tcKategorier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcKontaktperson.setCellValueFactory(cellData->cellData.getValue().kontaktpersonProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcSektor.setCellValueFactory(cellData->cellData.getValue().sektorProperty());
        tcFirmanavn.setCellValueFactory(cellData->cellData.getValue().firmanavnProperty());
        tcOrgNr.setCellValueFactory(cellData->cellData.getValue().orgnrProperty());
        tcBransje.setCellValueFactory(cellData->cellData.getValue().bransjeProperty());
        tcStillingstittel.setCellValueFactory(cellData->cellData.getValue().stillingstittelProperty());
        tcStillingstype.setCellValueFactory(cellData->cellData.getValue().stillingstypeProperty());
        tcVarighet.setCellValueFactory(cellData->cellData.getValue().varighetProperty());
        tcLonn.setCellValueFactory(cellData->cellData.getValue().lonnProperty());
        tcKvalifikasjoner.setCellValueFactory(cellData->cellData.getValue().kvalifikasjonerProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tvOversiktVikariater.setItems(OversiktVikariaterHjelper.visVikariater(Paths.VIKARIAT_CSV));
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnRedigerVikariat(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/regVikariat.fxml", event);
    }

    public void btnSlettVikariat(ActionEvent event) {
        String nokkel = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();

        System.out.println(nokkel);

        Boolean test = RegVikariatHjelper.slettValgtVikariat(nokkel);
        System.out.println(test);
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnLastNedVikariatr(ActionEvent event) {
        FileChooserHjelper.lastNed(Paths.VIKARIAT_CSV);
    }

    public void btnLastOppVikariat(ActionEvent event) {
        FileChooserHjelper.lastOpp(Paths.VIKARIAT_CSV);
    }

    public void btnFinnSokere(ActionEvent event) {

        String kategoriStr = tvOversiktVikariater.getSelectionModel().getSelectedItem().kategorierProperty().get();
        //System.out.println("kategoriStr:" + kategoriStr);
        ArrayList<String> kategorier = OversiktHjelper.stringToList(kategoriStr);

        OversiktSokereHjelper valgteKategorier = new OversiktSokereHjelper();
        valgteKategorier.setValgteKategorier(kategorier);
        //System.out.println("kategorier ArrayList<> : "+ kategorier.toString());

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/resultatSokere.fxml", event);
    }

}

