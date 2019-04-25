package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logikk.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static logikk.OversiktVikariaterHjelper.*;

public class OversiktVikariaterController implements Initializable {

    @FXML
    private TextField txtFilterField;

    @FXML
    private TableView<TabellVikariater> tvOversiktVikariater;

    @FXML
    private TableColumn<TabellVikariater, String> tcKontaktperson, tcTlf, tcSektor, tcFirmanavn, tcAdresse, tcBransje,
            tcStillingstittel, tcStillingstype, tcKategorier, tcStatus;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcKontaktperson.setCellValueFactory(cellData->cellData.getValue().kontaktpersonProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcSektor.setCellValueFactory(cellData->cellData.getValue().sektorProperty());
        tcFirmanavn.setCellValueFactory(cellData->cellData.getValue().firmanavnProperty());
        tcAdresse.setCellValueFactory(cellData->cellData.getValue().adresseProperty());
        tcBransje.setCellValueFactory(cellData->cellData.getValue().bransjeProperty());
        tcStillingstittel.setCellValueFactory(cellData->cellData.getValue().stillingstittelProperty());
        tcStillingstype.setCellValueFactory(cellData->cellData.getValue().stillingstypeProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());
        tcStatus.setCellValueFactory(cellData->cellData.getValue().statusProperty());

        tvOversiktVikariater.setItems(visVikariater(Paths.VIKARIAT_CSV));

        // Muliggjør sortering og filtrering av data i tabellen.
        FilteredList<TabellVikariater> filteredData = new FilteredList<>(visVikariater(Paths.VIKARIAT_CSV), p-> true);

        // Bruker Listener til å fange opp endringer.
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(soker -> {
            // Hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises.
            if(newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(soker.getKontaktperson().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getTlf().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getSektor().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getFirmanavn().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getAdresse().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getKategorier().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getBransje().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getStillingstittel().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getStillingstype().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getStatus().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }
            return false;
        }));

        SortedList<TabellVikariater> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvOversiktVikariater.comparatorProperty());
        tvOversiktVikariater.setItems(sortedData);
    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnLastNedVikariat(ActionEvent event) {
        String key = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();
        OversiktVikariaterHjelper.saveTempJob(key);
    }

    public void btnLastOppVikariat(ActionEvent event) {
        FileChooserHjelper.lastOpp(Paths.VIKARIAT_CSV);
    }

    public void btnLesMerOmVikariat(ActionEvent event) {
        String key = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();

        String title = OversiktVikariaterHjelper.lesMerTittel(key);
        String message = OversiktVikariaterHjelper.lesMerInnhold(key);

        AlertHelper.showMoreInfo(title,message);
    }

    public void btnRedigerVikariat(ActionEvent event) {
        String key = tvOversiktVikariater.getSelectionModel().getSelectedItem().getTlf();
        findArbeidsgiver(key);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("test.fxml"));
        try {
            Parent root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RegVikariatController controller = loader.<RegVikariatController>getController();

        controller.setData(valgtArbeidsgiver);
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/regVikariater.fxml", event);
    }

    public void btnSlettVikariat(ActionEvent event) {
        // TODO: kontrollsjekke at man ikke kan registreres med duplikate tlf nr
        String key = tvOversiktVikariater.getSelectionModel().getSelectedItem().tlfProperty().get();

        System.out.println(key);

        OversiktVikariaterHjelper.slettValgtVikariat(key);
        MainAppHelper run = new MainAppHelper();
        run.reloadVikariaterDatabase();

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktVikariater.fxml", event);
    }

    public void btnFinnSokere(ActionEvent event) {
        String kategoriStr = tvOversiktVikariater.getSelectionModel().getSelectedItem().kategorierProperty().get();
        ArrayList<String> kategorier = OversiktHjelper.stringToList(kategoriStr);

        OversiktSokereHjelper valgteKategorier = new OversiktSokereHjelper();
        valgteKategorier.setValgteKategorier(kategorier);

        String tlf = tvOversiktVikariater.getSelectionModel().getSelectedItem().getTlf();
        findArbeidsgiver(tlf);

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/resultatSokere.fxml", event);
    }
}

