package org.openjfx;

public class AlternativControllerTilVikariat {
}

/*
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
import java.nio.file.Path;

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
    private void btnRegVikariat(ActionEvent event, Paths paths) {

        Arbeidsgiver nyUtlysning = RegVikariatHjelper.lagVikariat(
                txtKontaktperson, txtTlf, txtSektor, txtFirmaNavn, txtOrgNr, txtBransje,
                txtStillingstittel, txtVarighet, txtLonn,radioHeltid, radioDeltid,
                txtKvalifikasjoner, txtBeskrivelse, cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String ut = nyUtlysning.toString();


        //path
        // Lagrer til .csv
        LagreTilFil lagreCsv = new LagreTilCsv();
        try {
            lagreCsv.skrivTilFil(ut, Paths.VIKARIAT+".csv");
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Lagrer til .jobj
        String path = Paths.VIKARIAT+".jobj";
        LagreTilJobj lagreJobj = new LagreTilJobj();
        lagreJobj.skrivTilFil(ut, path);


        //Tar brukeren til visning:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        //Tar brukeren tilbake til index:
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}

enum Paths{
    VIKARIART("vikariat"),
    JOBSOKER("jobbsoker");

    private final String path;

    Paths(String path){this.path = path;}

     String getPath(){
        return this.path;
    }

 */
