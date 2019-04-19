package org.openjfx;

import Exceptions.ValidationChecker;
import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import klasser.Arbeidsgiver;
import logikk.Paths;
import logikk.RegVikariatHjelper;
import logikk.NavigeringsHjelper;
import java.io.*;

public class RegVikariatController {

    @FXML
    private TextField txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje;

    @FXML
    private TextField  txtStillingstittel, txtVarighet, txtLonn,txtKvalifikasjoner;

    @FXML
    private RadioButton radioHeltid, radioDeltid;

    @FXML
    private TextArea txtBeskrivelse;

    /* @FXML
    private VBox vbxWorkfields;*/

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;

    @FXML
    private Label lblFeilmld;

    @FXML
    private void btnRegVikariat(ActionEvent event) {

        Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje,
                txtStillingstittel, txtVarighet, txtLonn, radioHeltid, radioDeltid,
                txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        // TODO: flytte kode for validering i en egen metode utenfor controller
        String inptContactPerson = txtKontaktperson.getText();
        String inptPhoneNmbr = txtTlf.getText();
        String inptSector = txtSektor.getText();
        String inptCompanyName = txtFirmaNavn.getText();
        String inptOrgNmbr = txtOrgNr.getText();
        String inptIndustry = txtBransje.getText();
        String inptJobTitle = txtStillingstittel.getText();
        String inptQualif = txtKvalifikasjoner.getText();
        String inptDuration = txtVarighet.getText();
        String inptSalary = txtLonn.getText();
        String inptJobDescription = txtBeskrivelse.getText();
        //String inptJobType =
        //String inptWorkfields = String.valueOf(vbxWorkfields.getChildren().toString());

        ValidationChecker validation = new ValidationChecker();
        String invalidInputs = validation.inputJobAdvertCollector(inptContactPerson, inptPhoneNmbr, inptSector, inptCompanyName,
                inptOrgNmbr, inptIndustry, inptJobTitle, inptJobDescription, inptDuration, inptSalary, inptQualif);

        if (!invalidInputs.isEmpty()) {
            lblFeilmld.setText(invalidInputs);
        } else {

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
