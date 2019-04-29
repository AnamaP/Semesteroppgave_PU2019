package org.openjfx;

import klasser.Company;
import logikk.*;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;

import static logikk.ViewHelper.chosenRow;
import static logikk.RegTempJobHelper.tempJobsList;

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
            tempJobsList.remove(tempJobsList.get(chosenRow));
            MainAppHelper reload = new MainAppHelper();
            reload.reloadVikariaterDatabase();
        }

        Company newTempJob = RegTempJobHelper.createTempJob(
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
            FileHandler csvFileHandler = new CsvFileHandler();
            try {
                csvFileHandler.writeToDB(ut, Paths.TEMPJOB);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //Tar brukeren til visning:
            NavigationHelper.changePage("/org/openjfx/oversiktVikariater.fxml", event);
        }
    }

    @FXML
    private void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    public void setData(int valgtArbeidsgiver){
        Company tempJob = tempJobsList.get(valgtArbeidsgiver);
        System.out.println("int: "+valgtArbeidsgiver);
        System.out.println(tempJob.toString());

        txtContactPerson.setText(tempJob.getContactPerson());
        txtPhoneNo.setText(tempJob.getPhoneNo());
        txtSector.setText(tempJob.getSector());
        txtCompanyName.setText(tempJob.getCompanyName());
        txtAddress.setText(tempJob.getAddress());
        txtIndustry.setText(tempJob.getIndustry());
        txtJobTitle.setText(tempJob.getIndustry());
        txtQualif.setText(tempJob.getTempJob().getQualif());
        txtDuration.setText(tempJob.getTempJob().getDuration());
        txtSalary.setText(tempJob.getTempJob().getSalary());
        txtDescription.setText(tempJob.getTempJob().getDescription());

        shouldUpdate = true;
    }
}
