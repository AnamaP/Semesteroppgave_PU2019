package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Jobbsoker;
import logikk.RegSokerHjelper;
import logikk.navigeringsHjelper;

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

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed,
                txtTlf, txtEpost, txtAlder,valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg,
                cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nySoker.toString();

        // Lagrer til .csv
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(ut, "jobbsoker.csv");
        }
        catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Lagrer til .jobj
        String path = "jobbsoker.jobj";
        LagreTilJobj lagreJobj = new LagreTilJobj();
        lagreJobj.skrivPersonTilFil(ut, "jobbsoker.jobj");

        //Tar brukeren med til neste side:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
