package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logikk.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static logikk.OversiktSokereHjelper.*;

public class OversiktSokereController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    TableView<TabellSokere> tvOversiktSokere;

    @FXML
    TableColumn<TabellSokere, String> tcFornavn, tcEtternavn, tcAdresse, tcPostNr, tcPoststed, tcTlf, tcEpost, tcAlder,
            tcUtdanning, tcStudieretning, tcErfaring, tcKategorier, tcStatus;

    // Initialize klassen under gjør at tilhørende metoder automatisk blir kalt etter at fxml filen har blitt hentet.

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initierer kolonnene
        tcFornavn.setCellValueFactory(cellData -> cellData.getValue().fornavnProperty());
        tcEtternavn.setCellValueFactory(cellData -> cellData.getValue().etternavnProperty());
        tcAdresse.setCellValueFactory(cellData -> cellData.getValue().adresseProperty());
        tcPostNr.setCellValueFactory(cellData -> cellData.getValue().postnrProperty());
        tcPoststed.setCellValueFactory(cellData -> cellData.getValue().poststedProperty());
        tcTlf.setCellValueFactory(cellData -> cellData.getValue().tlfProperty());
        tcEpost.setCellValueFactory(cellData -> cellData.getValue().epostProperty());
        tcAlder.setCellValueFactory(cellData -> cellData.getValue().alderProperty());
        tcUtdanning.setCellValueFactory(cellData -> cellData.getValue().utdanningProperty());
        tcStudieretning.setCellValueFactory(cellData -> cellData.getValue().studieretningProperty());
        tcErfaring.setCellValueFactory(cellData -> cellData.getValue().erfaringProperty());
        tcKategorier.setCellValueFactory(cellData -> cellData.getValue().kategorierProperty());
        tcStatus.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        tvOversiktSokere.setItems(visJobbsokere());


        /* Muliggjør sortering og filtrering av data i tabellen*/

        FilteredList<TabellSokere> filteredData = new FilteredList<>(visJobbsokere(), p -> true);

        // bruker Listener til å fange opp endringer og ..
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(soker -> {
            // hvis det ikke er skrevet noe inn i filteret så skal all informasjon vises
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if (soker.getFornavn().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getEtternavn().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getPostnr().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getPoststed().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getTlf().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getKategorier().toLowerCase().contains(lowerCaseFilter) ||
                    soker.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                return true;
            }
            return false;
        }));

        SortedList<TabellSokere> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvOversiktSokere.comparatorProperty());
        tvOversiktSokere.setItems(sortedData);

    }

    @FXML
    public void btnTilbake(ActionEvent event) {
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnRedigerSoker(ActionEvent event) {
        String key = tvOversiktSokere.getSelectionModel().getSelectedItem().getTlf();
        findJobbsoker(key);

        // Load FXML
        URL url = getClass().getResource("/org/openjfx/regSoker.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RegSokerController controller = loader.getController();
        loader.setLocation(url);
        controller.setData();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    public void btnSlettSoker(ActionEvent event) {
        String message = tvOversiktSokere.getSelectionModel().getSelectedItem().getFornavn();
        message += " "+tvOversiktSokere.getSelectionModel().getSelectedItem().getEtternavn();
        Alert question = new Alert(Alert.AlertType.CONFIRMATION);
        question.setHeaderText("Er du sikker på at du vil slette : ");
        question.setContentText(message +"?");
        Optional<ButtonType> result = question.showAndWait();

        if (result.get() == ButtonType.OK) {
            // blir sletting gjennomført
            String nokkel = tvOversiktSokere.getSelectionModel().getSelectedItem().tlfProperty().get();

            Boolean test = RegSokerHjelper.slettValgtSoker(nokkel);
            if (test) {
                MainAppHelper run = new MainAppHelper();
                run.reloadJobbsokerDatabase();
            }
            NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);
        }
        else {
        // avbryter slettingen
    }

}

    public void btnLastNedSoker(ActionEvent event){
        String key = tvOversiktSokere.getSelectionModel().getSelectedItem().tlfProperty().get();
        OversiktSokereHjelper.saveJobseeker(key);
    }

    public void btnLastOppSoker(ActionEvent event){
        FileChooserHjelper.lastOpp(Paths.JOBBSOKER);
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);

    }

    public void btnFinnVikariater(ActionEvent event){
        String kategoriStr = tvOversiktSokere.getSelectionModel().getSelectedItem().kategorierProperty().get();
        ArrayList<String> kategorier = OversiktHjelper.stringToList(kategoriStr);

        OversiktHjelper valgteKategorier = new OversiktHjelper();
        valgteKategorier.setValgteKategorier(kategorier);

        String tlf = tvOversiktSokere.getSelectionModel().getSelectedItem().getTlf();
        findJobbsoker(tlf);

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/resultatVikariater.fxml", event);
    }
}
