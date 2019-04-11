package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import logikk.NavigeringsHjelper;

public class VisningController {

    @FXML
    public static Label txtVisning;


    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}
