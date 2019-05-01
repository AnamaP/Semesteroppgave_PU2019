package org.openjfx;

import logic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import classes.Jobseeker;
import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ValidationHelper.runJobseekerValidation;
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

        runJobseekerValidation(newJobseeker, event);
    }

    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    public void setData(){
        Jobseeker jobseeker = jobseekersList.get(chosenJobseeker);

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
