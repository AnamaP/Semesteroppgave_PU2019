package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import klasser.Jobbsoker;
import logikk.RegSokerHjelper;
import logikk.navigeringsHjelper;

import java.io.File;
import java.io.IOException;

public class RegSokerController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder;

    @FXML
    private ComboBox valgUtdanning, valgRetning;

    @FXML
    private TextField txtErfaring,  txtReferanse, txtLonnskrav;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;


    public void btnRegSoker(ActionEvent event) {

        // FileChooser, kan denne legges utenfor Controlleren? samme metode for RegVikariatController
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage);
        String chosenpath = selectedFile.toString();

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder,
                valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nySoker.toString();

        // Lagrer til .csv
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(ut, chosenpath); // her var "jobbsoker.csv" tidligere
        }
        catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Henter fra .csv
        HenteFraCsv hentCsv = new HenteFraCsv();
        hentCsv.henteFraFil(chosenpath); // her var "jobbsoker.csv" tidligere

        // Lagrer til .jobj
        String path = chosenpath; // her var "jobbsoker.jobj" tidligere
        LagreTilJobj lagreJobj = new LagreTilJobj();
        lagreJobj.skrivPersonTilFil(ut, path);

        // Henter fra .jobj
        HenteFraJobj hentJobj = new HenteFraJobj();
        hentJobj.henteFraFil(chosenpath);// her var "jobbsoker.jobj" tidligere

        //Tar brukeren med til neste side:
        //navigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
