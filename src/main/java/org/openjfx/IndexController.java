package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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

    @FXML
    private TextField txtBrukernavn;
    // feilhåndtering for brukernavn??

    @FXML
    private TextField txtPassord;
    // feilhåndtering for passord??

    @FXML
    private void btnLoggInn(ActionEvent event){
        System.out.println("You clicked Logg inn!");
        // Her skal den gå videre til enten "Rediger jobbsøker" eller "Rediger arbeidsgiver" basert
        // på brukernavnet (eks. J001 / A001)
    }

}
