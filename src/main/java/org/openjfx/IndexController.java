package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logikk.*;


public class IndexController {

    @FXML
    private void btnJobbsoker(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/regSoker.fxml", event);
    }

    @FXML
    private void btnArbeidsgiver(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/regVikariat.fxml", event);
    }

    public void btnOversiktSokere(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnOversiktVikariater(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml",event);

    }

}
