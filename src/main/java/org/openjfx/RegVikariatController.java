package org.openjfx;

import logikk.*;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Arbeidsgiver;
import java.io.*;

public class RegVikariatController {

    @FXML
    private TextField txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtAdresse, txtBransje;

    @FXML
    private TextField  txtStillingstittel, txtVarighet, txtLonn,txtKvalifikasjoner;

    @FXML
    private RadioButton radioHeltid, radioDeltid;

    @FXML
    private TextArea txtBeskrivelse;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;

    @FXML
    private void btnRegVikariat(ActionEvent event) {

        Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtAdresse, txtBransje,
                txtStillingstittel, txtVarighet, txtLonn, radioHeltid, radioDeltid,
                txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        // TODO: flytte kode for validering i en egen metode utenfor controller
        String inptContactPerson = txtKontaktperson.getText();
        String inptPhoneNmbr = txtTlf.getText();
        String inptSector = txtSektor.getText();
        String inptCompanyName = txtFirmaNavn.getText();
        String inptAddress = txtAdresse.getText();
        String inptIndustry = txtBransje.getText();
        String inptJobTitle = txtStillingstittel.getText();
        String inptQualif = txtKvalifikasjoner.getText();
        String inptDuration = txtVarighet.getText();
        String inptSalary = txtLonn.getText();
        String inptJobDescription = txtBeskrivelse.getText();
        Boolean inptFullTime = radioHeltid.isSelected();
        Boolean inptPartTime = radioDeltid.isSelected();
        Boolean inptAdmin = cbxAdmin.isSelected();
        Boolean inptSales = cbxSalg.isSelected();
        Boolean inptIt = cbxIt.isSelected();
        Boolean inptEconomy = cbxOkonomi.isSelected();

        ValidationChecker validation = new ValidationChecker();
        String invalidInputs = validation.inputJobAdvertCollector(inptContactPerson, inptPhoneNmbr, inptSector, inptCompanyName,
                inptAddress, inptIndustry, inptJobTitle, inptJobDescription,inptDuration, inptSalary, inptQualif,
                inptSales,inptAdmin, inptIt, inptEconomy, inptFullTime, inptPartTime);

        // Dialogboks som vises dersom feilmeldinger
        if (!invalidInputs.isEmpty()) {
            AlertHelper.showError(invalidInputs);
        }
        else{
            // Dialogboks som vises dersom vellykket registrering
            AlertHelper.showConfirmation();

            String ut = nyUtlysning.toString();

            // Lagrer til .csv
            Filhandterer csvFilhandterer = new CsvFilhandterer();
            try {
                csvFilhandterer.skrivTilFil(ut, Paths.VIKARIAT_CSV);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Tar brukeren til visning:
            NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
        }
    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
