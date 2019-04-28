package org.openjfx;

import logikk.*;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Arbeidsgiver;
import java.io.*;

import static logikk.OversiktVikariaterHjelper.valgtArbeidsgiver;
import static logikk.RegVikariatHjelper.arbeidsgivere;

public class RegVikariatController {

    @FXML
    private TextField txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtAdresse, txtBransje,
                      txtStillingstittel, txtVarighet, txtLonn,txtKvalifikasjoner;

    @FXML
    private RadioButton radioHeltid, radioDeltid;

    @FXML
    private TextArea txtBeskrivelse;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;

    private boolean shouldUpdate = false;

    @FXML
    private void btnRegVikariat(ActionEvent event) {

        if(shouldUpdate){
            arbeidsgivere.remove(arbeidsgivere.get(valgtArbeidsgiver));
            MainAppHelper reload = new MainAppHelper();
            reload.reloadJobbsokerDatabase();
        }

        Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtAdresse, txtBransje,
                txtStillingstittel, txtVarighet, txtLonn, radioHeltid, radioDeltid,
                txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi, "Ledig");

        // TODO: flytte kode for validering i en egen metode utenfor controller
        String inptName = txtKontaktperson.getText();
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
        String invalidInputs = validation.inputJobAdvertCollector(inptName, inptPhoneNmbr, inptSector, inptCompanyName,
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
                csvFilhandterer.skrivTilDB(ut, Paths.VIKARIAT);
            }
            catch (IOException e) {
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

    public void setData(int valgtArbeidsgiver){
        Arbeidsgiver arbeidsgiver = arbeidsgivere.get(valgtArbeidsgiver);
        System.out.println("int: "+valgtArbeidsgiver);
        System.out.println(arbeidsgiver.toString());

        txtKontaktperson.setText(arbeidsgiver.getKontaktperson());
        txtTlf.setText(arbeidsgiver.getTlf());
        txtSektor.setText(arbeidsgiver.getSektor());
        txtFirmaNavn.setText(arbeidsgiver.getFirmanavn());
        txtAdresse.setText(arbeidsgiver.getAdresse());
        txtBransje.setText(arbeidsgiver.getBransje());
        txtStillingstittel.setText(arbeidsgiver.getBransje());
        txtKvalifikasjoner.setText(arbeidsgiver.getVikariat().getKvalifikasjoner());
        txtVarighet.setText(arbeidsgiver.getVikariat().getVarighet());
        txtLonn.setText(arbeidsgiver.getVikariat().getLonn());
        txtBeskrivelse.setText(arbeidsgiver.getVikariat().getBeskrivelse());

        shouldUpdate = true;
    }
}
