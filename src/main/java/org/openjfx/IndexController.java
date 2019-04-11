package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import logikk.LastNedHjelper;
import logikk.Paths;
import logikk.NavigeringsHjelper;
import logikk.VisningsHjelper;

import java.io.IOException;

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
        String ut = VisningsHjelper.viserJobbsokere(Paths.JOBBSOKER_CSV);
        VisningController.txtVisning.setText(ut);
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    public void btnOverisktVikariater(ActionEvent event) {

    }

    public void btnLastNedSokere(ActionEvent event){
        lastNed(Paths.JOBBSOKER_CSV, Paths.JOBBSOKER_JOBJ);
    }

    public void btnLastNedVikariater(ActionEvent event) {
        lastNed(Paths.VIKARIAT_CSV, Paths.VIKARIAT_JOBJ);
    }

    private void lastNed(String csvPath, String jobjPath) {
        Filhandterer filHandterer;
        String chosenpath = LastNedHjelper.fileChooser();

        // Metode som sjekker hvilket filformat bruker har valgt, henter ut riktig fil med innhold
        String extension = Filhandterer.getExtention(chosenpath);

        if(extension.equals(".csv")) {
            filHandterer = new CsvFilhandterer();
            try {
                filHandterer.lagreFilLokalt(chosenpath, csvPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            filHandterer = new JobjFilhandterer();
            try {
                filHandterer.lagreFilLokalt(chosenpath, jobjPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
