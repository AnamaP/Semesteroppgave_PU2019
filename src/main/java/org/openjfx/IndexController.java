package org.openjfx;

import filbehandling.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import klasser.Arbeidsgiver;
import logikk.*;

import java.io.IOException;
import java.util.ArrayList;

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
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/visning.fxml", event);
    }

    public void btnOversiktVikariater(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml",event);

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
        }
        else {
            filHandterer = new JobjFilhandterer();
            try {
                ArrayList<Arbeidsgiver> arbeidsgivere = RegVikariatHjelper.arbeidsgivere;
                filHandterer.lagreFilLokalt(chosenpath, jobjPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
