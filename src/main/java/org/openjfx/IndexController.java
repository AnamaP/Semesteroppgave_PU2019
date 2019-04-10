package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class IndexController {

    @FXML
    private void btnJobbsoker(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = getClass().getResource("/org/openjfx/regSoker.fxml");
            loader.setLocation(url);

            Parent parent = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        }catch (IOException io){
            io.printStackTrace();
        }
        System.out.println("You clicked Jobbsøker!");
        //Her skal den gå videre til Jobbsøker-registrering.
    }

    @FXML
    private void btnArbeidsgiver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = getClass().getResource("/org/openjfx/regVikariat.fxml");
            loader.setLocation(url);

            Parent parent = loader.load();
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        }catch (IOException io) {
            io.printStackTrace();
        }
        System.out.println("You clicked Vikariat!");
        //Her skal den gå videre til Vikariat-registrering.
    }


    public void btnLastNedSokere(ActionEvent actionEvent) {

        // FileChooser
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage); //showSaveDialog
        String chosenpath = selectedFile.toString();

        // Henter fra .csv
        HenteFraCsv hentCsvVikariat = new HenteFraCsv();
        String sb = hentCsvVikariat.henteFraFil("jobbsoker.csv");

        LagreTilCsv ltc = new LagreTilCsv();
        try {
            ltc.skrivPersonTilFil(sb, chosenpath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnLastNedVikariater(ActionEvent actionEvent) {

        // FileChooser - samle denne i en metode !!
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage);
        //file.separator sjekk ut for formatering
        String chosenpath = selectedFile.toString();

        // Henter fra .csv
        HenteFraCsv hentCsvVikariat = new HenteFraCsv();
        String sb = hentCsvVikariat.henteFraFil("vikariat.csv");

        LagreTilCsv ltc = new LagreTilCsv();
        try {
            ltc.skrivPersonTilFil(sb, chosenpath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        // Henter fra .jobj
        HenteFraJobj hentJobjVikariat = new HenteFraJobj();
        hentJobjVikariat.henteFraFil("vikariat.jobj");

    }
}
