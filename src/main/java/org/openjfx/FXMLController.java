package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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


    public void initialize() {
        // TODO
    }
}
