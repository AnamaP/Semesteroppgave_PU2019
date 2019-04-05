package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;
import klasser.Vikariat;
import logikk.RegVikariatHjelper;
import java.io.IOException;
import java.net.URL;

public class RegVikariatController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtEpost, txtTlf, txtFirmaNavn, txtOrgNr, txtBransje, txtTittel, txtType, txtLonn;

    @FXML
    private TextArea txtKvalifikasjoner, txtBeskrivelse;

    @FXML
    private RadioButton radioHeltid, radioDeltid, radioOffentlig, radioPrivat;

    @FXML
    private ColumnConstraints gridSektor, gridHelDel;

    @FXML
    private CheckBox cbxSalg, cbxService, cbxIt, cbxOkonomi;



    @FXML
    private void btnRegArbeidCsv(ActionEvent event) {

         Vikariat nyVikariat = RegVikariatHjelper.lagVikariat(txtFornavn, txtEtternavn, txtEpost, txtTlf, txtFirmaNavn, txtOrgNr,
                 txtBransje, txtTittel, txtType, txtLonn, txtKvalifikasjoner, txtBeskrivelse, radioHeltid,
                 radioDeltid, radioOffentlig, radioPrivat, cbxSalg, cbxService, cbxIt, cbxOkonomi);

        System.out.println(nyVikariat.toString());

        // Lagrer til .csv - - må linkes til FileChooser ?
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivVikariatTilFil(nyVikariat, "./vikariat.csv");
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
        Vikariat nyVikariat = RegVikariatHjelper.lagVikariat(txtFornavn, txtEtternavn, txtEpost, txtTlf, txtFirmaNavn, txtOrgNr,
                txtBransje, txtTittel, txtType, txtLonn, txtKvalifikasjoner, txtBeskrivelse, radioHeltid,
                radioDeltid, radioOffentlig, radioPrivat, cbxSalg, cbxService, cbxIt, cbxOkonomi);

        String path = "vikariat.jobj";
        LagreTilJobj lagre = new LagreTilJobj();
        lagre.skrivVikariatTilFil(nyVikariat, path);

        // Henter fra .jobj - må linkes til FileChooser ?
        HenteFraJobj hentJobj = new HenteFraJobj();
        hentJobj.henteFraFil("vikariat.jobj");

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
