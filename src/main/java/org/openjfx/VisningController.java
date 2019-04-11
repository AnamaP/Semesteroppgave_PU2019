package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import logikk.NavigeringsHjelper;
import logikk.Paths;
import logikk.Table;
import logikk.VisningsHjelper;

import java.net.URL;
import java.util.ResourceBundle;

public class VisningController implements Initializable {
    @FXML
    public TextArea txtVisning;

    // DEFINE TABLE
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

    // CREATE TABLE DATA
    private ObservableList<Table> data = FXCollections.observableArrayList(
        new Table("Trine", "trine@test.no","OsloMet, It","SALG"),
        new Table("Turid", "turid@test.no","OsloMet, Admin","OKONOMI")

    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcNavn.setCellValueFactory(cellData->cellData.getValue().rNavnProperty());
        tcEpost.setCellValueFactory(cellData->cellData.getValue().rEpostProperty());
        tcUtdanning.setCellValueFactory(cellData->cellData.getValue().rUtdanningProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().rKategorierProperty());

        /*
        tcNavn.setCellValueFactory(new PropertyValueFactory<>("rNavn"));
        tcEpost.setCellValueFactory(new PropertyValueFactory<>("rEpost"));
        tcUtdanning.setCellValueFactory(new PropertyValueFactory<>("rUtdanning"));
        tcKategorier.setCellValueFactory(new PropertyValueFactory<>("rKategorier"));
        */
        tcOversiktSoker.setItems(data);
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnVisSokere(ActionEvent event) {
        String ut = VisningsHjelper.visJobbsokere(Paths.JOBBSOKER_CSV);
        txtVisning.setText(ut);
    }
}
