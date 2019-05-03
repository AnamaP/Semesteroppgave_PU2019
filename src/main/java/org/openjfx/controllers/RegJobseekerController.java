package org.openjfx.controllers;

import org.openjfx.model.logic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openjfx.model.dataClasses.Jobseeker;
import static org.openjfx.model.logic.RegJobseekerHelper.jobseekersList;
import static org.openjfx.model.logic.ViewJobseekersHelper.chosenJobseeker;

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
     * 1: Om man kommer fra redigering ønsker man at den gamle versjonen av jobbsøkeren skal slettes
     *    og erstattes om shouldUpdate er true. Oppdaterer csv via reloadJobseekeresDB().
     *
     * 2: Oppretter en ny jobbsøker bassert på hva bruker har skrevet inn.
     *
     * 3: Validerer det bruker har skrevet inn og sender deg videre til en ny side om den er "godkjent".
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
        Boolean register = RegJobseekerHelper.newSeeker(txtFirstname, txtLastname, txtAddress, txtZipcode, txtPostal,
                txtPhoneNo, txtEmail, txtAge, choiseEducation, choiseStudy, txtExperience, txtReference, txtSalary, cbxSales,
                cbxAdmin, cbxIt, cbxEconomy, "Ledig");

        // 3
        if(register){
            NavigationHelper.changePage("/org/openjfx/view/viewJobseekers.fxml", event);
        }
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    public void btnBack(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigationHelper.changePage("/org/openjfx/view/index.fxml", event);
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
