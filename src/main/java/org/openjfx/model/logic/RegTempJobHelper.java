package org.openjfx.model.logic;

import javafx.scene.control.*;
import org.openjfx.model.dataClasses.Company;
import org.openjfx.model.dataClasses.TempJob;

import java.util.ArrayList;

import static org.openjfx.model.logic.ValidationHelper.runTempJobValidation;

public class RegTempJobHelper {

    /**
     * Denne listen settes hver gang programmet starter opp og inneholder en kopi av alle jobbutlysningene som
     * finnes i databasen. Når man sletter eller endrer en utlysning vil endringen skje her først og så
     * overskriver denne listen det som ligger i databasen.
     */
    public static ArrayList<Company> tempJobsList = new ArrayList<>();

    /**
     * Oppretter en jobbutlysning bassert på hva bruker har skrevet inn. Sender dette igjennom en runTempJobValidation()
     * som blir false om noen av valideringene finner noe feil i det som er skrevet inn. Om utlysningen ble registrert
     * returnerer den true.
     */
    public static Boolean createTempJob(
            TextField txtContactPerson, TextField txtPhoneNo, TextField txtSector, TextField txtCompanyName, TextField txtAddress,
            TextField txtIndustry, TextField txtJobTitle, TextField txtDuration, TextField txtSalary,
            RadioButton radioFullTime, RadioButton radioPartTime, TextField txtQualif,  TextField txtDescription,
            CheckBox cbxSales, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxEconomy, String status) {

        ArrayList<String> workfields = WorkfieldsHelper.regWorkfields(cbxSales,cbxAdmin,cbxIt,cbxEconomy);

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

    /**
     * Henter ut hvilken radio-knapp bruker har valgt og returner innholdet som en String.
     */
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
