package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import klasser.Cv;
import klasser.Jobbsoker;
import logikk.RegSokerHjelper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RegSokerController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost, txtAlder;

    @FXML
    private ComboBox valgUtdanning, valgRetning;

    @FXML
    private TextField txtErfaring,  txtReferanse, txtLonnskrav;

    @FXML
    private CheckBox cbxSalg, cbxAdmin, cbxIt, cbxOkonomi;


    public void btnRegSokerJobj(ActionEvent event) {

        ArrayList<String> kategorier = RegSokerHjelper.regKategori(cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String studieretning = RegSokerHjelper.studieretning(valgRetning);
        String utdanning = RegSokerHjelper.utdanning(valgUtdanning);
        Cv nyCv = new Cv(utdanning, studieretning, txtErfaring.getText(), kategorier);

        Jobbsoker nySoker = new Jobbsoker(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnr.getText(),
                                         txtPoststed.getText(), txtTlf.getText(), txtEpost.getText(), txtAlder.getText(), nyCv);
        String test = "";

        // Lagrer til .jobj - må linkes til FileChooser ?
        String path = "jobbsoker.jobj";
        LagreTilJobj lagre = new LagreTilJobj();
        lagre.skrivPersonTilFil(test, path);

        // Henter fra .jobj - må linkes til FileChooser ?
        HenteFraJobj hentJobj = new HenteFraJobj();
        hentJobj.henteFraFil("jobbsoker.jobj");
    }

    public void btnRegSokerCsv(ActionEvent event) {

        ArrayList<String> kategorier = RegSokerHjelper.regKategori(cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String studieretning = RegSokerHjelper.studieretning(valgRetning);
        String utdanning = RegSokerHjelper.utdanning(valgUtdanning);
        Cv nyCv = new Cv(utdanning, studieretning, txtErfaring.getText(), kategorier);

        Jobbsoker nySoker = new Jobbsoker(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnr.getText(),
                txtPoststed.getText(), txtTlf.getText(), txtEpost.getText(), txtAlder.getText(), nyCv);

        String ut = nySoker.toString() + nyCv.toString();

        // Lagrer til .csv - - må linkes til FileChooser ?
        LagreTilFil lagre = new LagreTilCsv();
        try {
            lagre.skrivPersonTilFil(ut, "./jobbsoker.csv");
        } catch (IOException e) { // Endres til FileNotFoundException ??
            // bør legge til en feilmeldingen i sysOut
            e.printStackTrace();
        }

        // Henter fra .csv - må linkes til FileChooser ?
        HenteFraCsv hentCsv = new HenteFraCsv();
        hentCsv.henteFraFil("jobbsoker.csv");

        //Planlegger å Deretter vise på et nytt GUI hva som er skrevet inn.
    }

    public void btnTilbake(ActionEvent event) {
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
