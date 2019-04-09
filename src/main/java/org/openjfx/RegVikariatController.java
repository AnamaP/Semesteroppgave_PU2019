package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klasser.Arbeidsgiver;
import klasser.Vikariat;
import logikk.RegVikariatHjelper;
import logikk.navigeringsHjelper;

import java.io.IOException;

public class RegVikariatController {

    @FXML
    private TextField txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje;

    @FXML
    private TextField  txtTittel, txtStillingstittel, txtLonn;

    @FXML
    private RadioButton radioHeltid, radioDeltid;

    @FXML
    private TextArea txtKvalifikasjoner, txtBeskrivelse;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;



    @FXML
    private void btnRegArbeidCsv(ActionEvent event) {

         Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                 txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje, txtTittel, txtStillingstittel,
                 txtLonn, radioHeltid, radioDeltid, txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nyUtlysning.toString();

        // Lagrer til .csv - - må linkes til FileChooser ?
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivVikariatTilFil(ut, "./vikariat.csv");
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Henter fra .csv - må linkes til FileChooser ?
        HenteFraCsv hentCsv = new HenteFraCsv();
        hentCsv.henteFraFil("vikariat.csv");

        //Planlegger å Deretter vise på et nytt GUI hva som er skrevet inn.
    }

    @FXML
    private void btnRegArbeidJobj(ActionEvent event) {
        Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje, txtTittel, txtStillingstittel,
                txtLonn, radioHeltid, radioDeltid, txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nyUtlysning.toString();

        String path = "vikariat.jobj";
        LagreTilJobj lagre = new LagreTilJobj();
        lagre.skrivVikariatTilFil(ut, path);

        // Henter fra .jobj - må linkes til FileChooser ?
        HenteFraJobj hentJobj = new HenteFraJobj();
        hentJobj.henteFraFil("vikariat.jobj");

    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);

    }
}
