package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private void btnJobbsoker(ActionEvent event) {
        System.out.println("You clicked Jobbsøker!");
        //Her skal den gå videre til Jobbsøker-registrering.
    }

    @FXML
    private void btnArbeidsgiver(ActionEvent event) {
        System.out.println("You clicked Arbeidsgiver!");
        //Her skal den gå videre til Arbeidsgiver-registrering.
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


    public void initialize() {
        // TODO
    }
}
