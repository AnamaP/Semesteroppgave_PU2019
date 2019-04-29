package org.openjfx;

import logikk.*;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Jobbsoker;

import java.io.IOException;

import static logikk.OversiktHjelper.chosenRow;
import static logikk.RegSokerHjelper.jobbsokere;

public class RegSokerController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder;

    @FXML
    private ComboBox valgUtdanning, valgRetning;

    @FXML
    private TextField txtErfaring,  txtReferanse, txtLonnskrav;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;

    private boolean shouldUpdate = false;

    public void btnRegSoker(ActionEvent event) {
        if(shouldUpdate){
            jobbsokere.remove(chosenRow);
            MainAppHelper reload = new MainAppHelper();
            reload.reloadJobbsokerDatabase();
        }

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

    public void setData(){
        Jobbsoker jobbsoker = jobbsokere.get(chosenRow);
        System.out.println("int: "+ chosenRow);
        System.out.println(jobbsoker.toString());

        txtFornavn.setText(jobbsoker.getFornavn());
        txtEtternavn.setText(jobbsoker.getEtternavn());
        txtAdresse.setText(jobbsoker.getAdresse());
        txtPostnr.setText(jobbsoker.getPostnr());
        txtPoststed.setText(jobbsoker.getPoststed());
        txtTlf.setText(jobbsoker.getTlf());
        txtEpost.setText(jobbsoker.getEpost());
        txtAlder.setText(jobbsoker.getAlder());
        txtLonnskrav.setText(jobbsoker.getLonnskrav());

        txtErfaring.setText(jobbsoker.getCv().getErfaring());
        txtReferanse.setText(jobbsoker.getCv().getReferanse());

        shouldUpdate = true;
    }
}
