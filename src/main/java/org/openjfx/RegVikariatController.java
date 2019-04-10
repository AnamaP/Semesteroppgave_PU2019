package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klasser.Arbeidsgiver;
import logikk.Paths;
import logikk.RegVikariatHjelper;
import logikk.navigeringsHjelper;
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

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;


    @FXML
    private void btnRegVikariat(ActionEvent event) {

        Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje,
                txtStillingstittel, txtVarighet, txtLonn,radioHeltid, radioDeltid,
                txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nyUtlysning.toString();


        // Lagrer til .csv
        FilHandterer csvFilhandterer = new CsvFilhandterer();
        try {
            csvFilhandterer.skrivTilFil(ut, Paths.VIKARIAT_CSV);
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Lagrer til .jobj
        FilHandterer jobjFilhandterer = new JobjFilhandterer();
        try {
            jobjFilhandterer.skrivTilFil(ut, Paths.VIKARIAT_JOBJ);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Tar brukeren til visning:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
