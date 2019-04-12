package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Jobbsoker;
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


    public void btnRegSoker(ActionEvent event) {

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed,
                txtTlf, txtEpost, txtAlder,valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg,
                cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nySoker.toString();

        // Lagrer til .csv
        Filhandterer csvFilhandterer = new CsvFilhandterer();
        try {
            csvFilhandterer.skrivTilFil(ut, Paths.JOBBSOKER_CSV);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //Tar brukeren med til neste side:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversitSokere.fxml", event);
    }

    public void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
