package org.openjfx;

import logikk.*;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Arbeidsgiver;
import java.io.*;

import static logikk.OversiktHjelper.chosenRow;
import static logikk.RegVikariatHjelper.arbeidsgivere;

public class RegTempJobController {

    @FXML
    private TextField txtContactPerson, txtPhoneNo, txtSector, txtCompanyName, txtAddress, txtIndustry,
            txtJobTitle, txtDuration, txtSalary, txtQualif;

    @FXML
    private RadioButton radioFullTime, radioPartTime;

    @FXML
    private TextArea txtDescription;

    @FXML
    private CheckBox cbxSales, cbxAdmin, cbxIt, cbxEconomy;

    private boolean shouldUpdate = false;

    @FXML
    private void btnRegTempJob(ActionEvent event) {

        if(shouldUpdate){
            arbeidsgivere.remove(arbeidsgivere.get(chosenRow));
            MainAppHelper reload = new MainAppHelper();
            reload.reloadVikariaterDatabase();
        }

        Arbeidsgiver newTempJob = RegVikariatHjelper.lagVikariat(
                txtContactPerson, txtPhoneNo, txtSector, txtCompanyName, txtAddress, txtIndustry,
                txtJobTitle, txtDuration, txtSalary, radioFullTime, radioPartTime,
                txtQualif, txtDescription, cbxSales, cbxAdmin, cbxIt, cbxEconomy, "Ledig");

        // TODO: flytte kode for validering i en egen metode utenfor controller
        String inptContactPerson = txtContactPerson.getText();
        String inptPhoneNo = txtPhoneNo.getText();
        String inptSector = txtSector.getText();
        String inptCompanyName = txtCompanyName.getText();
        String inptAddress = txtAddress.getText();
        String inptIndustry = txtIndustry.getText();
        String inptJobTitle = txtJobTitle.getText();
        String inptQualif = txtQualif.getText();
        String inptDuration = txtDuration.getText();
        String inptSalary = txtSalary.getText();
        String inptDescription = txtDescription.getText();
        Boolean inptFullTime = radioFullTime.isSelected();
        Boolean inptPartTime = radioPartTime.isSelected();
        Boolean inptAdmin = cbxAdmin.isSelected();
        Boolean inptSales = cbxSales.isSelected();
        Boolean inptIt = cbxIt.isSelected();
        Boolean inptEconomy = cbxEconomy.isSelected();

        ValidationChecker validation = new ValidationChecker();
        String invalidInputs = validation.inputJobAdvertCollector(inptContactPerson, inptPhoneNo, inptSector, inptCompanyName,
                inptAddress, inptIndustry, inptJobTitle, inptDescription,inptDuration, inptSalary, inptQualif,
                inptSales,inptAdmin, inptIt, inptEconomy, inptFullTime, inptPartTime);

        // Dialogboks som vises dersom feilmeldinger
        if (!invalidInputs.isEmpty()) {
            AlertHelper.showError(invalidInputs);
        }
        else{
            // Dialogboks som vises dersom vellykket registrering
            AlertHelper.showConfirmation();

            String ut = newTempJob.toString();

            // Lagrer til .csv
            Filhandterer csvFilhandterer = new CsvFilhandterer();
            try {
                csvFilhandterer.skrivTilDB(ut, Paths.VIKARIAT);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //Tar brukeren til visning:
            NavigationHelper.changePange("/org/openjfx/oversiktVikariater.fxml", event);
        }
    }

    @FXML
    private void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePange("/org/openjfx/index.fxml", event);
    }

    public void setData(int valgtArbeidsgiver){
        Arbeidsgiver tempJob = arbeidsgivere.get(valgtArbeidsgiver);
        System.out.println("int: "+valgtArbeidsgiver);
        System.out.println(tempJob.toString());

        txtContactPerson.setText(tempJob.getKontaktperson());
        txtPhoneNo.setText(tempJob.getTlf());
        txtSector.setText(tempJob.getSektor());
        txtCompanyName.setText(tempJob.getFirmanavn());
        txtAddress.setText(tempJob.getAdresse());
        txtIndustry.setText(tempJob.getBransje());
        txtJobTitle.setText(tempJob.getBransje());
        txtQualif.setText(tempJob.getVikariat().getKvalifikasjoner());
        txtDuration.setText(tempJob.getVikariat().getVarighet());
        txtSalary.setText(tempJob.getVikariat().getLonn());
        txtDescription.setText(tempJob.getVikariat().getBeskrivelse());

        shouldUpdate = true;
    }
}
