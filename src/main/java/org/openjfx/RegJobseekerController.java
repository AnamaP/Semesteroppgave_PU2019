package org.openjfx;

import logic.*;
import fileHandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import classes.Jobseeker;
import java.io.IOException;
import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ViewJobseekerHelper.chosenJobseeker;

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
            jobseekersList.remove(chosenJobseeker);
            MainAppHelper reload = new MainAppHelper();
            reload.reloadJobbsokerDatabase();
        }

        Jobseeker newJobseeker = RegJobseekerHelper.newSeeker(txtFirstname, txtLastname, txtAddress, txtZipcode, txtPostal,
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
        FileHandler csvFileHandler = new CsvFileHandler();
        try {
            csvFileHandler.writeToDB(input, Paths.JOBSEEKER);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //Tar brukeren med til neste side:
        NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
        }
    }

    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    public void setData(){
        Jobseeker jobseeker = jobseekersList.get(chosenJobseeker);
        System.out.println("int: "+ chosenJobseeker);
        System.out.println(jobseeker.toString());

        txtFirstname.setText(jobseeker.getFirstname());
        txtLastname.setText(jobseeker.getLastname());
        txtAddress.setText(jobseeker.getAddress());
        txtZipcode.setText(jobseeker.getZipCode());
        txtPostal.setText(jobseeker.getPostal());
        txtPhoneNo.setText(jobseeker.getPhoneNo());
        txtEmail.setText(jobseeker.getEmail());
        txtAge.setText(jobseeker.getAge());
        txtSalary.setText(jobseeker.getSalary());

        txtExperience.setText(jobseeker.getCv().getExperience());
        txtReference.setText(jobseeker.getCv().getReference());

        shouldUpdate = true;
    }
}
