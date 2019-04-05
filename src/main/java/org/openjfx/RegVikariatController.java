package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class RegVikariatController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtEpost, txtTlf, txtFirmaNavn, txtOrgNr, txtBransje, txtTittel, txtType, txtLonn;

    @FXML
    private TextArea txtKvalifikasjoner, txtBeskrivelse;

    @FXML
    private RadioButton radioHeltid, radioDeltid, radioOffentlig, radioPrivat;

    @FXML
    private ColumnConstraints gridSektor, gridHelDel;

    @FXML
    private CheckBox cbxSalg, cbxService, cbxIt, cbxAdmin;


    @FXML
    private void btnRegArbeidJobj(ActionEvent event) {

    }

    @FXML
    private void btnRegArbeidCsv(ActionEvent event) {

    }

    @FXML
    private void btnTilbake(ActionEvent event) {
        System.out.println("Du har klikket deg tilbake!");
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = getClass().getResource("/org/openjfx/index.fxml");
            loader.setLocation(url);

            Parent parent = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
