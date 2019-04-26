package org.openjfx;

import logikk.*;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Jobbsoker;

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
                txtTlf, txtEpost, txtAlder, valgUtdanning, valgRetning, txtErfaring, txtReferanse, txtLonnskrav, cbxSalg,
                cbxAdmin, cbxIt, cbxOkonomi, "Ledig");

        // TODO: flytte kode for validering i en egen metode utenfor controller
        String inptFirstname = txtFornavn.getText();
        String inptLastname = txtEtternavn.getText();
        String inptAddress = txtAdresse.getText();
        String inptZipCode = txtPostnr.getText();
        String inptPostal = txtPoststed.getText();
        String inptPhoneNmbr = txtTlf.getText();
        String inptEmail = txtEpost.getText();
        String inptAge = txtAlder.getText();
        String inptExperience = txtErfaring.getText();
        String inptSalary = txtLonnskrav.getText();
        String inptEducation = String.valueOf(valgUtdanning.getValue());
        String inptStudy = String.valueOf(valgRetning.getValue());
        Boolean inptAdmin = cbxAdmin.isSelected();
        Boolean inptSales = cbxSalg.isSelected();
        Boolean inptIt = cbxIt.isSelected();
        Boolean inptEconomy = cbxOkonomi.isSelected();

        ValidationChecker validation = new ValidationChecker();
        String invalidInputs = validation.inputJobseekerCollector(inptFirstname, inptLastname, inptAddress, inptZipCode,
                inptPostal,inptPhoneNmbr, inptEmail, inptAge, inptExperience, inptSalary, inptEducation,
                inptStudy,inptSales, inptAdmin, inptIt, inptEconomy);

        if (!invalidInputs.isEmpty()){
            AlertHelper.showError(invalidInputs);
        }
        else{
        AlertHelper.showConfirmation();

        String input = nySoker.toString();

        // Lagrer til .csv
        Filhandterer csvFilhandterer = new CsvFilhandterer();
        try {
            csvFilhandterer.skrivTilDB(input, Paths.JOBBSOKER);
        }
        catch (IOException e) {
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
