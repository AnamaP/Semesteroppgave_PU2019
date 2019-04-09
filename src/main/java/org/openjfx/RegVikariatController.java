package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import klasser.Arbeidsgiver;
import logikk.RegVikariatHjelper;
import logikk.navigeringsHjelper;

import java.io.File;
import java.io.IOException;

public class RegVikariatController {

    @FXML
    private TextField txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje;

    @FXML
    private TextField  txtStillingstittel, txtVarighet, txtLonn;

    @FXML
    private RadioButton radioHeltid, radioDeltid;

    @FXML
    private TextArea txtKvalifikasjoner, txtBeskrivelse;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;



    @FXML
    private void btnRegArbeidCsv(ActionEvent event) {

        // FileChooser
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showOpenDialog(chooserStage); //showSaveDialog
        String chosenpath = selectedFile.toString();

         Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                 txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje,txtStillingstittel, txtVarighet,
                 txtLonn, radioHeltid, radioDeltid, txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nyUtlysning.toString();

        // Lagrer til .csv
        LagreTilFil lagreCsv = new LagreTilCsv();
        try {
            lagreCsv.skrivVikariatTilFil(ut, chosenpath); // "vikariat.csv" tidligere
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Henter fra .csv
        HenteFraCsv hentCsv = new HenteFraCsv();
        hentCsv.henteFraFil(chosenpath); // "vikariat.csv" tidligere

        // Lagrer til .jobj
        String path = chosenpath; // "vikariat.jobj" tidligere
        LagreTilJobj lagreJobj = new LagreTilJobj();
        lagreJobj.skrivVikariatTilFil(ut, path);

        // Henter fra .jobj
        HenteFraJobj hentJobj = new HenteFraJobj();
        hentJobj.henteFraFil(chosenpath); // "vikariat.jobj" tidligere

        //Planlegger å Deretter vise på et nytt GUI hva som er skrevet inn.
    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
