package logikk;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import klasser.Cv;
import klasser.Jobbsoker;

import java.util.ArrayList;

public class RegSokerHjelper {

    public static Jobbsoker nySoker(TextField txtFornavn, TextField txtEtternavn, TextField txtAdresse, TextField txtPostnr,
                            TextField txtPoststed, TextField txtTlf, TextField txtEpost, TextField txtAlder,
                            ComboBox valgUtdanning, ComboBox valgRetning, TextField txtErfaring, TextField txtReferanse,
                            TextField txtLonnskrav, CheckBox cbxSalg, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxOkonomi){

        ArrayList<String> kategorier = RegKategoriHjelper.regKategori(cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String studieretning = RegSokerHjelper.studieretning(valgRetning);
        String utdanning = RegSokerHjelper.utdanning(valgUtdanning);
        Cv cv = new Cv(utdanning, studieretning, txtErfaring.getText(), kategorier);

        // hvis referanse er satt så...
        if(txtReferanse.getText() != ""){
            cv.setReferanse(txtReferanse.getText());
        }

        Jobbsoker nySoker = new Jobbsoker(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnr.getText(),
                txtPoststed.getText(), txtTlf.getText(), txtEpost.getText(), txtAlder.getText(), cv);

        // hvis lønnskrav er satt så...
        if(txtLonnskrav.getText() != ""){
            nySoker.setLonnskrav(txtLonnskrav.getText());
        }
        return nySoker;
    }

    public static String utdanning(ComboBox valgUtdanning){
        String utdanning;

        if(valgUtdanning.getValue().equals("Vgs")){
            utdanning = "Vgs";
        }
        else if (valgUtdanning.getValue().equals("Bachelor")){
            utdanning = "Bachelor";
        }
        else if (valgUtdanning.getValue().equals("Master")){
            utdanning = "Master";
        }
        else{
            utdanning = "Ikke valgt.";
        }
        return utdanning;
    }

    public static String studieretning(ComboBox valgRetning) {
        String studieretning;
        if(valgRetning.getValue().equals("It")){
            studieretning = "It";
        }
        else if (valgRetning.getValue().equals("Salg")){
            studieretning = "Salg";
        }
        else if (valgRetning.getValue().equals("Økonomi")){
            studieretning = "Økonomi";
        }
        else if (valgRetning.getValue().equals("Ledelse")){
            studieretning = "Ledelse";
        }
        else{
            studieretning = "Ikke valgt.";
        }

        //Her skal man kunne hente ut studieretning. DENNE FUNKER IKKE ENDA..!
        return studieretning;
    }

}
