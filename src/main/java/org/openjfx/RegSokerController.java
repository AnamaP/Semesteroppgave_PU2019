package org.openjfx;

import feilhaandtering.ValideringsHjelper;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Jobbsoker;
import logikk.FeilhandtererHjelper;
import logikk.Paths;
import logikk.RegSokerHjelper;
import logikk.NavigeringsHjelper;
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

    @FXML
    private Label lblFeilmld;



    public void btnRegSoker(ActionEvent event) {

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed,
                txtTlf, txtEpost, txtAlder, valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg,
                cbxAdmin, cbxIt, cbxOkonomi);

        String inptFornavn = txtFornavn.getText();
        String inptEtternavn = txtEtternavn.getText();
        String inptAdresse = txtAdresse.getText();
        String inptPostnr = txtPostnr.getText();
        String inptPoststed = txtPoststed.getText();
        String inptTlf = txtTlf.getText();
        String inptEpost = txtEpost.getText();
        String inptAlder = txtAlder.getText();
        String inptErfaring = txtErfaring.getText();
        String inptReferanse = txtReferanse.getText();
        String inptLonnskrav = txtLonnskrav.getText();

        //String test = FeilhandtererHjelper.getTextAreaData(txtFornavn);

        ValideringsHjelper validering = new ValideringsHjelper();
        String ugyldigInputs = validering.sjekkAlleInputs(inptFornavn, inptEtternavn, inptAdresse, inptPostnr, inptPoststed,
                inptTlf, inptEpost, inptAlder, inptErfaring, inptReferanse, inptLonnskrav);

        if (!ugyldigInputs.isEmpty()) {
            lblFeilmld.setText(ugyldigInputs);
        }
        else{

        String ut = nySoker.toString();

        // Lagrer til .csv
        Filhandterer csvFilhandterer = new CsvFilhandterer();
        try {
            csvFilhandterer.skrivTilFil(ut, Paths.JOBBSOKER_CSV);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Tar brukeren med til neste side:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);

    }
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
