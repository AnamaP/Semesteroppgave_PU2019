package logic;

import javafx.scene.control.*;
import classes.Company;
import classes.TempJob;

import java.util.ArrayList;

import static logic.ValidationHelper.runTempJobValidation;

public class RegTempJobHelper {

    public static ArrayList<Company> tempJobsList = new ArrayList<>();


    public static Boolean createTempJob(
            TextField txtContactPerson, TextField txtPhoneNo, TextField txtSector, TextField txtCompanyName, TextField txtAddress,
            TextField txtIndustry, TextField txtJobTitle, TextField txtDuration, TextField txtSalary,
            RadioButton radioFullTime, RadioButton radioPartTime, TextField txtQualif,  TextField txtDescription,
            CheckBox cbxSales, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxEconomy, String status) {

        ArrayList<String> workfields = RegWorkfieldsHelper.regWorkfields(cbxSales,cbxAdmin,cbxIt,cbxEconomy);

        String jobbType = regJobType(radioFullTime, radioPartTime);

        TempJob newTempJob = new TempJob(txtJobTitle.getText(), jobbType, txtDescription.getText(), txtDuration.getText(),
                                             txtSalary.getText(), txtQualif.getText(), workfields, status);

        Company newCompany = new Company(txtContactPerson.getText(),txtPhoneNo.getText(),txtSector.getText(),
                                        txtCompanyName.getText(), txtAddress.getText(), txtIndustry.getText(), newTempJob);

        if(runTempJobValidation(newCompany)){
            RegTempJobHelper.tempJobsList.add(newCompany);
            return true;
        }
        return false;
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
