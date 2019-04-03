package org.openjfx;

import filbehandling.LagreTilCsv;
import filbehandling.LagreTilFil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import klasser.Jobbsoker;
import klasser.Utdannelse;

import java.io.IOException;


public class RegSokerController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder, txtLonnskrav;

    @FXML
    private TextField txtStud1, txtStud2, txtSkole1, txtSkole2, txtAar1A, txtAar1B, txtAar2A, txtAar2B;

    @FXML
    private TextField txtStilling1, txtStilling2, txtReferanse1, txtReferanse2, txtDato1A, txtDato1B, txtDato2A, txtDato2B;

    @FXML
    private CheckBox cbxSalg, cbxService, cbxIt, cbxOkonomi;

    @FXML
    private void btnRegSokerCsv(ActionEvent event) {
        //Oppretter en basic Person:
        System.out.println("Du har registrert deg til cvs-fil!");
        String fornavn = txtFornavn.getText();
        String etternavn = txtEtternavn.getText();
        String adresse = txtAdresse.getText();
        String postnr = txtPostnr.getText();
        String poststed = txtPoststed.getText();
        String tlf = txtTlf.getText();
        String epost = txtEpost.getText();

        //Oppretter en Jobbsøker:
        String alder = txtAlder.getText();

        //Lage liste over utdaninger:
            Utdannelse utdanning1 = new Utdannelse(txtStud1.getText(), txtSkole1.getText(), txtAar1A.getText(), txtAar1B.getText());
            Utdannelse utdanning2 = new Utdannelse(txtStud2.getText(), txtSkole2.getText(), txtAar2A.getText(), txtAar2B.getText());

            Utdannelse [] utdannelser = {utdanning1, utdanning2};

        String [] erfaring = {txtStilling1.getText(), txtDato1A.getText()+" - "+txtDato1B.getText(), txtStilling2.getText(), txtDato2A.getText()+" - "+txtDato2B.getText()};
        String [] referanser = {txtReferanse1.getText(), txtReferanse2.getText()};
        String lonnskrav = txtLonnskrav.getText();

        Jobbsoker soker = new Jobbsoker(fornavn, etternavn, adresse, postnr, poststed, tlf, epost, alder, utdannelser, erfaring, referanser, lonnskrav);
        System.out.println(soker.toString());

        // Kode for å lagre til fil
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(soker, "./text.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Hente ut valgte kategorier:
        String [] kategorier = {};


        //Planlegger å Deretter vise på et nytt GUI hva som er skrevet inn.
    }

    @FXML
    private void btnRegSokerJobj(ActionEvent event) {
        System.out.println("Du har registrert deg til jobj-fil!");
    }

}
