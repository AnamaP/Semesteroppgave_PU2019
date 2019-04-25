package org.openjfx;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import logikk.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static logikk.OversiktSokereHjelper.visJobbsokere;

public class OversiktSokereController implements Initializable {

    @FXML
    TextField txtFilterField;

    @FXML
    TableView<TabellSokere> tvOversiktSokere;

    @FXML
    TableColumn<TabellSokere, String> tcFornavn, tcEtternavn, tcAdresse, tcPostNr, tcPoststed, tcTlf, tcEpost, tcAlder,
                                      tcUtdanning, tcStudieretning, tcErfaring, tcKategorier, tcStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tcFornavn.setCellValueFactory(cellData->cellData.getValue().fornavnProperty());
        tcEtternavn.setCellValueFactory(cellData->cellData.getValue().etternavnProperty());
        tcAdresse.setCellValueFactory(cellData->cellData.getValue().adresseProperty());
        tcPostNr.setCellValueFactory(cellData->cellData.getValue().postnrProperty());
        tcPoststed.setCellValueFactory(cellData->cellData.getValue().poststedProperty());
        tcTlf.setCellValueFactory(cellData->cellData.getValue().tlfProperty());
        tcEpost.setCellValueFactory(cellData->cellData.getValue().epostProperty());
        tcAlder.setCellValueFactory(cellData->cellData.getValue().alderProperty());
        tcUtdanning.setCellValueFactory(cellData->cellData.getValue().utdanningProperty());
        tcStudieretning.setCellValueFactory(cellData->cellData.getValue().studieretningProperty());
        tcErfaring.setCellValueFactory(cellData->cellData.getValue().erfaringProperty());
        tcKategorier.setCellValueFactory(cellData->cellData.getValue().kategorierProperty());
        tcStatus.setCellValueFactory(cellData->cellData.getValue().statusProperty());

        tvOversiktSokere.setItems(visJobbsokere(Paths.JOBBSOKER_CSV));


        //Filtrering av data i tabellen
        FilteredList<TabellSokere> filteredData = new FilteredList<>(visJobbsokere(Paths.JOBBSOKER_CSV),p-> true);
        txtFilterField.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(soker -> {
                if(newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(soker.getFornavn().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                else if(soker.getEtternavn().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                return false;

            });
        });

        SortedList<TabellSokere> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tvOversiktSokere.comparatorProperty());
        tvOversiktSokere.setItems(sortedData);

    }

    @FXML
    public void btnTilbake(ActionEvent event){
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/index.fxml", event);
    }

    public void btnRedigerSoker(ActionEvent event){
        // TODO : her skal det kalles på metode som redigerer en jobbsøker
    }

    public void btnSlettSoker(ActionEvent event) {

        String nokkel = tvOversiktSokere.getSelectionModel().getSelectedItem().tlfProperty().get();

        Boolean test = RegSokerHjelper.slettValgtSoker(nokkel);
        System.out.println(test);
        if(test){
            MainAppHelper run = new MainAppHelper();
            run.reloadJobbsokerDatabase();
        }
        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/oversiktSokere.fxml", event);
    }

    public void btnLastNedSoker(ActionEvent event){
        String key = tvOversiktSokere.getSelectionModel().getSelectedItem().tlfProperty().get();
        OversiktSokereHjelper.saveJobseeker(key);
    }

    public void btnLastOppSoker(ActionEvent event){
        FileChooserHjelper.lastOpp(Paths.JOBBSOKER_CSV);
    }

    public void btnFinnVikariater(ActionEvent event){

        String kategoriStr = tvOversiktSokere.getSelectionModel().getSelectedItem().kategorierProperty().get();
        //System.out.println("kategoriStr:" + kategoriStr);
        ArrayList<String> kategorier = OversiktHjelper.stringToList(kategoriStr);

        OversiktVikariaterHjelper valgteKategorier = new OversiktVikariaterHjelper();
        valgteKategorier.setValgteKategorier(kategorier);
        //System.out.println("kategorier ArrayList<> : "+ kategorier.toString());

        NavigeringsHjelper.gåTilAnnenSide("/org/openjfx/resultatVikariater.fxml", event);

    }
}
