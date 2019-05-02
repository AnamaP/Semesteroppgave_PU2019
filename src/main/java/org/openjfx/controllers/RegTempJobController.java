package org.openjfx.controllers;

import org.openjfx.model.dataClasses.Company;
import org.openjfx.model.logic.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static org.openjfx.model.logic.RegTempJobHelper.tempJobsList;
import static org.openjfx.model.logic.ViewTempJobsHelper.chosenTempJob;

public class RegTempJobController {

    @FXML
    private TextField txtContactPerson, txtPhoneNo, txtSector, txtCompanyName, txtAddress, txtIndustry,
            txtJobTitle, txtDuration, txtSalary, txtQualif, txtDescription;

    @FXML
    private RadioButton radioFullTime, radioPartTime;

    @FXML
    private CheckBox cbxSales, cbxAdmin, cbxIt, cbxEconomy;

    private boolean shouldUpdate = false;

    /**
     * I metoden under går man igjennom 3 stadier:
     *
     * 1: Oppretter en ny utlysning bassert på hva bruker har skrevet inn.
     *
     * 2: Om man kommer fra registrering ønsker man at den gamle versjonen av jobbutlysningen skal slettes
     *    og erstattes etter at den nye versjonen er blitt registrert. Her kjørers også reloadTempJobsDB som setter
     *    det nå oppdaterte arrayet inn i csv-filen og overskriver det som ligger der, slik at det ikke finnes en
     *    gammel versjon av jobbutlysningen i csv-filen lenger.
     *
     * 3: Her kjører den en test på at det brukeren har skrevet inn er gyldige verdier og sender deg videre til
     *    en ny side om det du skriver inn er "godkjent".
     */
    @FXML
    private void btnRegTempJob(ActionEvent event) {
        // 1
        if(shouldUpdate){
            tempJobsList.remove(tempJobsList.get(chosenTempJob));
            MainAppHelper reload = new MainAppHelper();
            reload.reloadTempJobsDB();
            shouldUpdate = false;
        }

        // 2
        Boolean register = RegTempJobHelper.createTempJob(
                txtContactPerson, txtPhoneNo, txtSector, txtCompanyName, txtAddress, txtIndustry,
                txtJobTitle, txtDuration, txtSalary, radioFullTime, radioPartTime,
                txtQualif, txtDescription, cbxSales, cbxAdmin, cbxIt, cbxEconomy, "Ledig");

        // 3
        if(register){
            NavigationHelper.changePage("/org/openjfx/view/viewTempJobs.fxml", event);
        }
    }

    /**
     * Sender bruker tilbake til menysiden.
     */
    @FXML
    private void btnBack(ActionEvent event) {
        NavigationHelper.changePage("/org/openjfx/view/index.fxml", event);
    }

    /**
     * Denne kjøres om bruker har valgt å redigere en jobbutlysning i tabellen. Her vil man hente ut de
     * verdiene som ligger på denne jobbutlysningen og brukeren kan forandre det som ønskes.
     * shounlUpdate settes til true slik at programmet skjønner at dette ikke er en ny registrering men
     * noe som skal overskrive en eksisterende jobbutlysning.
     */
    public void setData(int valgtArbeidsgiver){
        Company tempJob = tempJobsList.get(valgtArbeidsgiver);

        txtContactPerson.setText(tempJob.getContactPerson());
        txtPhoneNo.setText(tempJob.getPhoneNo());
        txtSector.setText(tempJob.getSector());
        txtCompanyName.setText(tempJob.getCompanyName());
        txtAddress.setText(tempJob.getAddress());
        txtIndustry.setText(tempJob.getIndustry());
        txtJobTitle.setText(tempJob.getIndustry());
        txtQualif.setText(tempJob.getTempJob().getQualif());
        txtDuration.setText(tempJob.getTempJob().getDuration());
        txtSalary.setText(tempJob.getTempJob().getSalary());
        txtDescription.setText(tempJob.getTempJob().getDescription());

        shouldUpdate = true;
    }
}
