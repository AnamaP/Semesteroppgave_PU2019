package logikk;

import javafx.scene.control.TextField;

import java.util.ArrayList;

public class RegistrerPerson {

    ArrayList<String> personalia = regPersonalia(txtFornavn,txtEtternavn,txtAdresse,txtPostnr,txtPoststed,txtTlf,
            txtEpost,txtAlder,txtLonnskrav);

    private static ArrayList<String> regPersonalia(
            TextField txtFornavn, TextField txtEtternavn, TextField txtAdresse, TextField txtPostnr,
            TextField txtPoststed, TextField txtTlf, TextField txtEpost, TextField txtAlder){

        ArrayList<String> personalia = new ArrayList<>();

        personalia.add(txtFornavn.getText());
        personalia.add(txtEtternavn.getText());
        personalia.add(txtAdresse.getText());
        personalia.add(txtPostnr.getText());
        personalia.add(txtPoststed.getText());
        personalia.add(txtTlf.getText());
        personalia.add(txtEpost.getText());
        personalia.add(txtAlder.getText());

        return personalia;
    }
}
