package org.openjfx;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import filbehandling.JobjFilhandterer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import klasser.Arbeidsgiver;
import logikk.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OversiktSokereController implements Initializable {
    @FXML
    TableView<TabellSokere> tvOversiktSoker;

    @FXML
    TableColumn<TabellSokere, String> tcNavn, tcEpost,tcUtdanning, tcKategorier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcNavn.setCellValueFactory(cellData->cellData.getValue().navnProperty());
        tcEpost.setCellValueFactory(cellData->cellData.getValue().epostProperty());
        tcUtdanning.setCellValueFactory(cellData->cellData.getValue().utdanningProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tvOversiktSoker.setItems(OversiktSokereHjelper.visJobbsokere(Paths.JOBBSOKER_CSV));
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnRedigerSoker(ActionEvent event) {
    }

    public void btnSlettSoker(ActionEvent event) {
    }

    public void btnLastNedSoker(ActionEvent event) {
        lastNed(Paths.JOBBSOKER_CSV);
    }

    public void btnLastOppSoker(ActionEvent event) {
    }

    private void lastNed(String csvPath) {
        Filhandterer filHandterer;
        String chosenpath = FileChooserHjelper.fileChooser();

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
        /*
        else {
            filHandterer = new JobjFilhandterer();
            try {
                ArrayList<Arbeidsgiver> arbeidsgivere = RegVikariatHjelper.arbeidsgivere;
                filHandterer.lagreFilLokalt(chosenpath, jobjPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
}
