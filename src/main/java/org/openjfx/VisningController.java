package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logikk.NavigeringsHjelper;
import logikk.Paths;
import logikk.Table;
import logikk.VisningsHjelper;
import java.net.URL;
import java.util.ResourceBundle;

public class VisningController implements Initializable {
    @FXML
    TableView<Table> tcOversiktSoker;

    @FXML
    TableColumn<Table, String> tcNavn;

    @FXML
    TableColumn<Table, String> tcEpost;

    @FXML
    TableColumn<Table, String> tcUtdanning;

    @FXML
    TableColumn<Table, String> tcKategorier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcNavn.setCellValueFactory(cellData->cellData.getValue().navnProperty());
        tcEpost.setCellValueFactory(cellData->cellData.getValue().epostProperty());
        tcUtdanning.setCellValueFactory(cellData->cellData.getValue().utdanningProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());

        tcOversiktSoker.setItems(VisningsHjelper.visJobbsokere(Paths.JOBBSOKER_CSV));
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }
}

        /*
        tcNavn.setCellValueFactory(new PropertyValueFactory<>("rNavn"));
        tcEpost.setCellValueFactory(new PropertyValueFactory<>("rEpost"));
        tcUtdanning.setCellValueFactory(new PropertyValueFactory<>("rUtdanning"));
        tcKategorier.setCellValueFactory(new PropertyValueFactory<>("rKategorier"));

        tcOversiktSoker.setItems(data);
        */