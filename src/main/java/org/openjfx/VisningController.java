package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import logikk.NavigeringsHjelper;

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

        /*
        //Hente fra fil - Vikariat:
        HenteFraCsv hentCsvVikariat = new HenteFraCsv();
        hentCsvVikariat.henteFraFil("vikariat.csv");

        HenteFraJobj hentJobjVikariat = new HenteFraJobj();
        hentJobjVikariat.henteFraFil("vikariat.jobj");

        //Hente fra fil - Jobbsoker:
        HenteFraCsv hentCsvSoker = new HenteFraCsv();
        hentCsvSoker.henteFraFil("jobbsoker.csv");

        HenteFraJobj hentJobjSoker = new HenteFraJobj();
        hentJobjSoker.henteFraFil("jobbsoker.jobj");
         */

    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

}
