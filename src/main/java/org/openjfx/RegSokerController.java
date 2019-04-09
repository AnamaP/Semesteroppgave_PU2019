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

        // FileChooser
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage);

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder,
               valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nySoker.toString();

        // Lagrer til .csv - - må linkes til FileChooser ?
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(ut, "./jobbsoker.csv");
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Henter fra .csv - må linkes til FileChooser ?
        HenteFraCsv hentCsv = new HenteFraCsv();
        hentCsv.henteFraFil("jobbsoker.csv");


        // Lagrer til .jobj - må linkes til FileChooser ?
        String path = "jobbsoker.jobj";
        LagreTilJobj lagre1 = new LagreTilJobj();
        lagre1.skrivPersonTilFil(ut, path);

        // Henter fra .jobj - må linkes til FileChooser ?
        HenteFraJobj hentJobj = new HenteFraJobj();
        hentJobj.henteFraFil("jobbsoker.jobj");

        //Tar brukeren med til neste side:
        //navigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

        /*
    public void btnRegSokerCsv(ActionEvent event) {

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder,
                valgUtdanning, valgRetning, txtErfaring,  txtReferanse, txtLonnskrav, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nySoker.toString();

        // Lagrer til .csv - - må linkes til FileChooser ?
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(ut, "./jobbsoker.csv");
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Henter fra .csv - må linkes til FileChooser ?
        HenteFraCsv hentCsv = new HenteFraCsv();
        hentCsv.henteFraFil("jobbsoker.csv");

        //Tar brukeren med til neste side:
        //navigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    }*/

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);

    }
}
