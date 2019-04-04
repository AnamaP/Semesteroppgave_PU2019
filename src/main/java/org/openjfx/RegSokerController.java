package org.openjfx;

import filbehandling.LagreTilCsv;
import filbehandling.LagreTilFil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import klasser.Jobbsoker;
import klasser.Utdannelse;
import logikk.RegistrerSoker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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

        Jobbsoker nySoker = RegistrerSoker.lagJobssoker(txtFornavn,txtEtternavn,txtAdresse,txtPostnr,txtPoststed,txtTlf,
                txtEpost,txtAlder,txtLonnskrav, txtStud1,txtStud2,txtSkole1,txtSkole2,txtAar1A,txtAar1B,txtAar2A,txtAar2B,
                txtStilling1,txtStilling2,txtDato1A,txtDato1B,txtDato2A,txtDato2B, txtReferanse1, txtReferanse2,
                cbxSalg,cbxService,cbxIt,cbxOkonomi);

        System.out.println(nySoker.toString());

        //Kode for å lagre til fil
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(nySoker, "./jobbsoker.csv");
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }


        //Planlegger å Deretter vise på et nytt GUI hva som er skrevet inn.
    }

    @FXML
    private void btnRegSokerJobj(ActionEvent event) {
        System.out.println("Du har registrert deg til jobj-fil!");
        Jobbsoker sokerJobj = RegistrerSoker.lagJobssoker(txtFornavn,txtEtternavn,txtAdresse,txtPostnr,txtPoststed,txtTlf,
                txtEpost,txtAlder,txtLonnskrav, txtStud1,txtStud2,txtSkole1,txtSkole2,txtAar1A,txtAar1B,txtAar2A,txtAar2B,
                txtStilling1,txtStilling2,txtDato1A,txtDato1B,txtDato2A,txtDato2B, txtReferanse1, txtReferanse2,
                cbxSalg,cbxService,cbxIt,cbxOkonomi);
    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        System.out.println("Du har klikket deg tilbake!");
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = getClass().getResource("/org/openjfx/index.fxml");
            loader.setLocation(url);

            Parent parent = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
