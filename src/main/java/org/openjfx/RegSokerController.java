package org.openjfx;

import feilhaandtering.ValidationHelper;
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

    @FXML
    private Label lblFeilmld;



    public void btnRegSoker(ActionEvent event) {

        Jobbsoker nySoker = RegSokerHjelper.nySoker(txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed,
                txtTlf, txtEpost, txtAlder, valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg,
                cbxAdmin, cbxIt, cbxOkonomi);

        String inptFirstname = txtFornavn.getText();
        String inptLastname = txtEtternavn.getText();
        String inptAddress = txtAdresse.getText();
        String inptZipCode = txtPostnr.getText();
        String inptPostal = txtPoststed.getText();
        String inptPhoneNmbr = txtTlf.getText();
        String inptEmail = txtEpost.getText();
        String inptAge = txtAlder.getText();
        String inptExperience = txtErfaring.getText();
        String inptReference = txtReferanse.getText();
        String inptSalary = txtLonnskrav.getText();

        //String test = FeilhandtererHjelper.getTextAreaData(txtFirstname);

        ValidationHelper validation = new ValidationHelper();
        String invalidInputs = validation.inputCollector(inptFirstname, inptLastname, inptAddress, inptZipCode, inptPostal,
                inptPhoneNmbr, inptEmail, inptAge, inptExperience, inptReference, inptSalary);

        if (!invalidInputs.isEmpty()) {
            lblFeilmld.setText(invalidInputs);
        }
        else{

        String input = nySoker.toString();

        // Lagrer til .csv
        Filhandterer csvFilhandterer = new CsvFilhandterer();
        try {
            csvFilhandterer.skrivTilFil(input, Paths.JOBBSOKER_CSV);
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
