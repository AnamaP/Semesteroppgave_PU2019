package logic;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import classes.Cv;
import classes.Jobseeker;
import java.util.ArrayList;

import static logic.ValidationHelper.runJobseekerValidation;

public class RegJobseekerHelper {

    public static ArrayList<Jobseeker> jobseekersList = new ArrayList<>();

    public static boolean newSeeker(TextField txtFirstName, TextField txtLastName, TextField txtAddress, TextField txtZipCode,
                                      TextField txtPostal, TextField txtPhoneNo, TextField txtEmail, TextField txtAge,
                                      ComboBox choiseEducation, ComboBox choiseStudy, TextField txtExperience,
                                      TextField txtReference, TextField txtSalary, CheckBox cbxSales, CheckBox cbxAdmin,
                                      CheckBox cbxIt, CheckBox cbxEconomy, String status){

        ArrayList<String> workfields = RegWorkfieldsHelper.regWorkfields(cbxSales, cbxAdmin, cbxIt, cbxEconomy);

        String education = RegJobseekerHelper.education(choiseEducation);
        String study = RegJobseekerHelper.study(choiseStudy);
        Cv cv = new Cv(education, study, txtExperience.getText(), workfields);

        // hvis referanse er satt så...
        if(txtReference.getText() != ""){
            cv.setReference(txtReference.getText());
        }

        Jobseeker newJobseeker = new Jobseeker(txtFirstName.getText(), txtLastName.getText(), txtAddress.getText(), txtZipCode.getText(),
                txtPostal.getText(), txtPhoneNo.getText(), txtEmail.getText(), txtAge.getText(), cv, status);

        // hvis lønnskrav er satt så...
        if(txtSalary.getText() != ""){
            newJobseeker.setSalary(txtSalary.getText());
        }

        if(runJobseekerValidation(newJobseeker)) {
            RegJobseekerHelper.jobseekersList.add(newJobseeker);
            return true;
        }
        return false;
    }

    public static String education(ComboBox choiseEducation){
        return readComboBxValue(choiseEducation);
    }

    public static String study(ComboBox choiseStudy) {
        return readComboBxValue(choiseStudy);
    }

    private static String readComboBxValue(ComboBox comboBox){
        String choise;
        try{
            choise = comboBox.getSelectionModel().getSelectedItem().toString();
        }
        catch(NullPointerException nope){
            choise = "not selected";
        }
        System.out.println(choise);
        return choise;
    }

    public static Boolean deleteChosenJobseeker(String key) {
        for(int i = 0; i < jobseekersList.size(); i++){
            String tlf = jobseekersList.get(i).getPhoneNo();
            if(tlf.equals(key)){
                jobseekersList.remove(i);
                return true;
            }
        }
        return false;
    }
}
