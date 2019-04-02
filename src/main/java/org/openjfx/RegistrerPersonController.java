package org.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RegistrerPersonController {

    @FXML
    private TextField txtFornavn, txtEtternavn, txtAdresse, txtPostnr, txtPoststed, txtTlf, txtEpost;

    @FXML
    private void btnRegistrer(ActionEvent event) {
        System.out.println("Du har registrert deg!");
        //Planlegger å hente ut info som er skrevet inn i GUI og opprette en person. Deretter vise hva som er skrevet inn.
    }

}
