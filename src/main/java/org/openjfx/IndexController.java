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
import klasser.Arbeidsgiver;
import logikk.lastNedHjelper;
import logikk.navigeringsHjelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

public class IndexController {

    @FXML
    private void btnJobbsoker(ActionEvent event) {
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/regSoker.fxml", event);
    }

    @FXML
    private void btnArbeidsgiver(ActionEvent event) {
        navigeringsHjelper.gåTilAnnenSide("/org/openjfx/regVikariat.fxml", event);
    }


    public void btnLastNedSokere(ActionEvent actionEvent) {

        String chosenpath = lastNedHjelper.fileChooser();

        // Henter fra .csv
        HenteFraCsv hentCsvVikariat = new HenteFraCsv();
        String sb = hentCsvVikariat.henteFraFil("jobbsoker.csv");

        LagreTilCsv ltc = new LagreTilCsv();
        try {
            ltc.skrivTilFil(sb, chosenpath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Henter fra .jobj
        HenteFraJobj hentJobjVikariat = new HenteFraJobj();
        String innholdJobj = hentJobjVikariat.henteFraFil("jobbsoker.jobj");

        LagreTilJobj lagreTilJobj = new LagreTilJobj();
        lagreTilJobj.skrivTilFil(innholdJobj, chosenpath);
    }


    public void btnLastNedVikariater(ActionEvent actionEvent) {

        String chosenpath = lastNedHjelper.fileChooser();

        // Henter fra .csv
        HenteFraCsv hentCsvVikariat = new HenteFraCsv();
        String innholdCsv = hentCsvVikariat.henteFraFil("vikariat.csv");

        LagreTilCsv lagreTilCsv = new LagreTilCsv();
        try {
            lagreTilCsv.skrivTilFil(innholdCsv, chosenpath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Henter fra .jobj
        HenteFraJobj hentJobjVikariat = new HenteFraJobj();
        String innholdJobj = hentJobjVikariat.henteFraFil("vikariat.jobj");

        LagreTilJobj lagreTilJobj = new LagreTilJobj();
        lagreTilJobj.skrivTilFil(innholdJobj, chosenpath);

    }
}
