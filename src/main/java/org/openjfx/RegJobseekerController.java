package org.openjfx;

import logic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import classes.Jobseeker;
import static logic.RegJobseekerHelper.jobseekersList;
import static logic.ValidationHelper.invalidInputs;
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

    private static boolean shouldUpdate = false;

    /**
     * I metoden under går man igjennom 3 stadier:
     *
     * 1: Om man kommer fra registrering ønsker man at den gamle versjonen av jobbsøkeren skal slettes
     *    og erstattes slik at kun den nye versjonen blir registrert. Her kjørers også reloadJobseekersDB som setter
     *    det nå oppdaterte arrayet inn i csv-filen og overskriver det som ligger der, slik at det ikke finnes en
     *    gammel versjon av jobbsøkeren i csv-filen lenger.
     *
     * 2: Oppretter en ny jobbsøker bassert på hva bruker har skrevet inn.
     *
     * 3: Her kjører den en test på at det brukeren har skrevet inn er gyldige verdier og sender deg videre til
     *    en ny side om det du skriver inn er "godkjent".
     */
    public void btnRegJobseeker(ActionEvent event) {
        // 1
        if(shouldUpdate){
            jobseekersList.remove(chosenJobseeker);
            MainAppHelper reload = new MainAppHelper();
            reload.reloadJobseekersDB();
            shouldUpdate = false;
        }

        // 2
        Boolean registrer = RegJobseekerHelper.newSeeker(txtFirstname, txtLastname, txtAddress, txtZipcode, txtPostal,
                txtPhoneNo, txtEmail, txtAge, choiseEducation, choiseStudy, txtExperience, txtReference, txtSalary, cbxSales,
                cbxAdmin, cbxIt, cbxEconomy, "Ledig");

        // 3
        if(registrer){
            NavigationHelper.changePage("/org/openjfx/viewJobseekers.fxml", event);
        }
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePage("/org/openjfx/index.fxml", event);
    }

    /**
     * Denne kjøres om bruker har valgt å redigere en jobbsøker i tabellen. Her vil man hente ut de
     * verdiene som ligger på denne jobbsøkeren og brukeren kan forandre det som ønskes.
     * shounlUpdate settes til true slik at programmet skjønner at dette ikke er en ny registrering men
     * noe som skal overskrive en eksisterende jobbsøker.
     */
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
