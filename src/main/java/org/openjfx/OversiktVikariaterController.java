package org.openjfx;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import logikk.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OversiktVikariaterController implements Initializable {

    @FXML
    private TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    private TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor, tcFirmanavn, tcBransje,
            tcStillingstittel, tcVarighet, tcKvalifikasjoner, tcKategorier;


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
        String nokkel = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();

        System.out.println(nokkel);

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

    public void btnFinnSokere(ActionEvent event) {

        String kategoriStr = tvOversiktVikariater.getSelectionModel().getSelectedItem().kategorierProperty().get();
        System.out.println("kategoriStr:" + kategoriStr);
        ArrayList<String> kategorier = stringToList(kategoriStr);

        OversiktSokereHjelper valgteKategorier = new OversiktSokereHjelper();
        valgteKategorier.setValgteKategorier(kategorier);
        System.out.println("kategorier ArrayList<> : "+ kategorier.toString());

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/resultatSokere.fxml", event);

    }

    private void setTableEditable() {
        tvOversiktVikariater.setEditable(true);
    }

    public static ArrayList<String> stringToList(final String input) {
        String[] elements = input.substring(1, input.length() - 1).split(", ");
        ArrayList<String> result = new ArrayList<>(elements.length);
        for (String item : elements) {
            result.add(String.valueOf(item));
        }
        return result;
    }
}

