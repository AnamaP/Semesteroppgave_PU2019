package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegSokerController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost;

    @FXML
    private TextField txtStud1, txtStud2, txtSkole1, txtSkole2, txtAar1A, txtAar1B, txtAar2A, txtAar2B;

    @FXML
    private TextField txtStilling1, txtStilling2, txtReferanse1, getTxtReferanse2, txtDato1A, txtDato1B, txtDato2A, txtDato2B;

    @FXML
    private CheckBox cbxSalg, cbxService, cbxIt, cbxOkonomi, cbxAdmin;

    @FXML
    private void btnRegistrer(ActionEvent event) {
        System.out.println("Du har registrert deg!");
        //Planlegger å hente ut info som er skrevet inn i GUI og opprette en person. Deretter vise hva som er skrevet inn.
    }

}
