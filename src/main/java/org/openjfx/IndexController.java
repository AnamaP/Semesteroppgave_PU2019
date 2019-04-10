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


    public void btnLastNedSokere(ActionEvent actionEvent) {
        FilHandterer filHandterer

        String chosenpath = LastNedHjelper.fileChooser();
        String extension = chosenpath.substring(chosenpath.lastIndexOf("."),chosenpath.length());

        if(extension.equals(".csv")) {
            filHandterer = new CsvFilhandterer();
            filHandterer.lagreFilLokalt(chosenpath);
        } else {
            filHandterer = new JobjFilhandterer();
        }

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

        String chosenpath = LastNedHjelper.fileChooser();

        // Henter fra .csv
        HenteFraCsv hentCsvVikariat = new HenteFraCsv();
        String innholdCsv = hentCsvVikariat.henteFraFil(Paths.VIKARIAT_CSV);

        //Lagre til .csv
        LagreTilCsv lagreTilCsv = new LagreTilCsv();
        try {
            lagreTilCsv.skrivTilFil(innholdCsv, chosenpath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Henter fra .jobj
        HenteFraJobj hentJobjVikariat = new HenteFraJobj();
        String innholdJobj = hentJobjVikariat.henteFraFil(Paths.VIKARIAT_JOBJ);

        // Lagre til .job
        LagreTilJobj lagreTilJobj = new LagreTilJobj();
        lagreTilJobj.skrivTilFil(innholdJobj, chosenpath);
    }
}
