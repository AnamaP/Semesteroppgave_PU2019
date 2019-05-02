package logic;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import classes.Cv;
import classes.Jobseeker;
import java.util.ArrayList;

import static logic.ValidationHelper.runJobseekerValidation;

public class RegJobseekerHelper {

    /**
     * Denne listen settes hver gang programmet starter opp og inneholder en kopi av alle jobbsøkerne som finnes
     * i databasen. Når man sletter eller endrer en søker vil endringen skje her først og så overskriver
     * denne listen det som ligger i databasen.
     */
    public static ArrayList<Jobseeker> jobseekersList = new ArrayList<>();

    /**
     * Oppretter en jobbsøker bassert på hva bruker har skrevet inn. Sender dette igjennom en runJobseekerValidation()
     * som blir false om noen av valideringene finner noe feil i det som er skrevet inn. Om søkeren ble registrert
     * returnerer den true.
     */
    public static boolean newSeeker(TextField txtFirstName, TextField txtLastName, TextField txtAddress, TextField txtZipCode,
                                      TextField txtPostal, TextField txtPhoneNo, TextField txtEmail, TextField txtAge,
                                      ComboBox choiseEducation, ComboBox choiseStudy, TextField txtExperience,
                                      TextField txtReference, TextField txtSalary, CheckBox cbxSales, CheckBox cbxAdmin,
                                      CheckBox cbxIt, CheckBox cbxEconomy, String status){

        ArrayList<String> workfields = WorkfieldsHelper.regWorkfields(cbxSales, cbxAdmin, cbxIt, cbxEconomy);

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

    /**
     * Henter ut hvilken utdanning bruker har valgt, kjører readComboBxValue og returnere innholdet som en string.
     */
    public static String education(ComboBox choiseEducation){
        return readComboBxValue(choiseEducation);
    }

    /**
     * Henter ut hvilken studieretning bruker har valgt, kjører readComboBxValue og returner innholdet som en String.
     */
    public static String study(ComboBox choiseStudy) {
        return readComboBxValue(choiseStudy);
    }

    /**
     * Sjekker at bruker har valgt en verdi.
     */
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
}
