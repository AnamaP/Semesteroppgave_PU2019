package logic;

import javafx.scene.control.*;
import classes.Company;
import classes.TempJob;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class RegTempJobHelper {

    public static ArrayList<Company> tempJobsList = new ArrayList<>();


    public static Company createTempJob(
            TextField txtContactPerson, TextField txtPhoneNo, TextField txtSector, TextField txtCompanyName, TextField txtAddress,
            TextField txtIndustry, TextField txtJobTitle, TextField txtDuration, TextField txtSalary,
            RadioButton radioFullTime, RadioButton radioPartTime, TextField txtQualif,  TextArea txtDescription,
            CheckBox cbxSale, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxEconomy, String status) {

        ArrayList<String> workfields = RegWorkfieldsHelper.regWorkfields(cbxSale,cbxAdmin,cbxIt,cbxEconomy);

        String jobbType = regJobType(radioFullTime, radioPartTime);

        TempJob newTempJob = new TempJob(txtJobTitle.getText(), jobbType, txtDescription.getText(), txtDuration.getText(),
                                             txtSalary.getText(), txtQualif.getText(), workfields, status);

        Company newCompany = new Company(txtContactPerson.getText(),txtPhoneNo.getText(),txtSector.getText(),
                                        txtCompanyName.getText(), txtAddress.getText(), txtIndustry.getText(), newTempJob);

        RegTempJobHelper.tempJobsList.add(newCompany);

        return newCompany;
    }

    private static String regJobType(RadioButton radioFullTime, RadioButton radioPartTime) {

        String jobType = "Arbeidstid ikke valgt";

        if(radioFullTime.isSelected()){
            jobType = "Heltid";
        }
        if(radioPartTime.isSelected()){
            jobType = "Deltid";
        }

        return jobType;
    }
}
