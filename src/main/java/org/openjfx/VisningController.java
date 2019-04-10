package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;

public class VisningController {
    private static final String PERSON_FILE_PATH = "vikariat.jobj";


    @FXML
    public TextFlow txtVisning;


    @FXML
    private void initialize(){
        String sisteVikariat = PERSON_FILE_PATH;
    }

    @FXML
    public void btnSeResultater(ActionEvent event){

    }

    @FXML
    public void btnTilbake(ActionEvent event){



    }

}
