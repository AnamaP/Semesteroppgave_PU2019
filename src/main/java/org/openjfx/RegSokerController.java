package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import klasser.Jobbsoker;
import logikk.RegSokerHjelper;
import logikk.navigeringsHjelper;

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

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder,
                valgUtdanning, valgRetning, txtErfaring,  txtReferanse, txtLonnskrav, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nySoker.toString();

        new FileChooser().showSaveDialog(((MenuItem)event.getTarget()).getParentPopup().getOwnerWindow());

        // Lagrer til .jobj - må linkes til FileChooser ?
        String path = "jobbsoker.jobj";
        LagreTilJobj lagre = new LagreTilJobj();
        lagre.skrivPersonTilFil(ut, path);

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
    }*/

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);

    }
}
