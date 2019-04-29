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

public class RegJobseekerController {

    @FXML
    private TextField txtFirstname, txtLastname, txtAddress, txtZipcode, txtPostal, txtPhoneNo, txtEmail, txtAge;

    @FXML
    private ComboBox choiseEducation, choiseStudy;

    @FXML
    private TextField txtExperience, txtReference, txtSalary;

    @FXML
    private CheckBox cbxSales, cbxAdmin, cbxIt, cbxEconomy;

    private boolean shouldUpdate = false;

    public void btnRegJobseeker(ActionEvent event) {
        if(shouldUpdate){
            jobbsokere.remove(chosenRow);
            MainAppHelper reload = new MainAppHelper();
            reload.reloadJobbsokerDatabase();
        }

        Jobbsoker newJobseeker = RegSokerHjelper.nySoker(txtFirstname, txtLastname, txtAddress, txtZipcode, txtPostal,
                txtPhoneNo, txtEmail, txtAge, choiseEducation, choiseStudy, txtExperience, txtReference, txtSalary, cbxSales,
                cbxAdmin, cbxIt, cbxEconomy, "Ledig");

        // TODO: flytte kode for validering i en egen metode utenfor controller
        String inptFirstname = txtFirstname.getText();
        String inptLastname = txtLastname.getText();
        String inptAddress = txtAddress.getText();
        String inptZipcode = txtZipcode.getText();
        String inptPostal = txtPostal.getText();
        String inptPhoneNo = txtPhoneNo.getText();
        String inptEmail = txtEmail.getText();
        String inptAge = txtAge.getText();
        String inptExperience = txtExperience.getText();
        String inptSalary = txtSalary.getText();
        String inptEducation = String.valueOf(choiseEducation.getValue());
        String inptStudy = String.valueOf(choiseStudy.getValue());
        Boolean inptAdmin = cbxAdmin.isSelected();
        Boolean inptSales = cbxSales.isSelected();
        Boolean inptIt = cbxIt.isSelected();
        Boolean inptEconomy = cbxEconomy.isSelected();

        ValidationChecker validation = new ValidationChecker();
        String invalidInputs = validation.inputJobseekerCollector(inptFirstname, inptLastname, inptAddress, inptZipcode,
                inptPostal,inptPhoneNo, inptEmail, inptAge, inptExperience, inptSalary, inptEducation,
                inptStudy,inptSales, inptAdmin, inptIt, inptEconomy);

        if (!invalidInputs.isEmpty()){
            AlertHelper.showError(invalidInputs);
        }
        else{
        AlertHelper.showConfirmation();

        String input = newJobseeker.toString();

        // Lagrer til .csv
        Filhandterer csvFileHandler = new CsvFilhandterer();
        try {
            csvFileHandler.skrivTilDB(input, Paths.JOBBSOKER);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //Tar brukeren med til neste side:
        NavigationHelper.changePange("/org/openjfx/oversiktSokere.fxml", event);
        }
    }

    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePange("/org/openjfx/index.fxml", event);
    }

    public void setData(){
        Jobbsoker jobseeker = jobbsokere.get(chosenRow);
        System.out.println("int: "+ chosenRow);
        System.out.println(jobseeker.toString());

        txtFirstname.setText(jobseeker.getFirstname());
        txtLastname.setText(jobseeker.getLastname());
        txtAddress.setText(jobseeker.getAddress());
        txtZipcode.setText(jobseeker.getZipcode());
        txtPostal.setText(jobseeker.getPostal());
        txtPhoneNo.setText(jobseeker.getTlf());
        txtEmail.setText(jobseeker.getEmail());
        txtAge.setText(jobseeker.getAge());
        txtSalary.setText(jobseeker.getSalary());

        txtExperience.setText(jobseeker.getCv().getExperience());
        txtReference.setText(jobseeker.getCv().getReference());

        shouldUpdate = true;
    }
}
