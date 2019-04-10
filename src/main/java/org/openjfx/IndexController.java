package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logikk.LastNedHjelper;
import logikk.Paths;
import logikk.navigeringsHjelper;
import java.io.IOException;
import java.nio.file.Files;

public class IndexController {

    @FXML
    private void btnJobbsoker(ActionEvent event) {
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/regSoker.fxml", event);
    }

    @FXML
    private void btnArbeidsgiver(ActionEvent event) {
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/regVikariat.fxml", event);
    }

    private void lastNed(String csvPath, String jobjPath) {
        FilHandterer filHandterer;
        String chosenpath = LastNedHjelper.fileChooser();
        String extension = FilHandterer.getExtention(chosenpath);

        if(extension.equals(".csv")) {
            filHandterer = new CsvFilhandterer();
            try {
                filHandterer.lagreFilLokalt(chosenpath, csvPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            filHandterer = new JobjFilhandterer();
            try {
                filHandterer.lagreFilLokalt(chosenpath, jobjPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnLastNedSokere(ActionEvent actionEvent){
        lastNed(Paths.JOBBSOKER_CSV, Paths.JOBBSOKER_JOBJ);
    }

    public void btnLastNedVikariater(ActionEvent actionEvent) {
        lastNed(Paths.VIKARIAT_CSV, Paths.VIKARIAT_JOBJ);
    }
}
