package logikk;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import klasser.Cv;
import klasser.Jobbsoker;
import java.util.ArrayList;

public class RegSokerHjelper {

    public static ArrayList<Jobbsoker> jobbsokere = new ArrayList<>();

    public static Jobbsoker nySoker(TextField txtFornavn, TextField txtEtternavn, TextField txtAdresse, TextField txtPostnr,
                            TextField txtPoststed, TextField txtTlf, TextField txtEpost, TextField txtAlder,
                            ComboBox valgUtdanning, ComboBox valgRetning, TextField txtErfaring, TextField txtReferanse,
                            TextField txtLonnskrav, CheckBox cbxSalg, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxOkonomi, String status){

        ArrayList<String> kategorier = RegKategoriHjelper.regKategori(cbxSalg, cbxAdmin, cbxIt, cbxOkonomi);

        String studieretning = RegSokerHjelper.studieretning(valgRetning);
        String utdanning = RegSokerHjelper.utdanning(valgUtdanning);
        Cv cv = new Cv(utdanning, studieretning, txtErfaring.getText(), kategorier);

        // hvis referanse er satt så...
        if(txtReferanse.getText() != ""){
            cv.setReferanse(txtReferanse.getText());
        }

        Jobbsoker nySoker = new Jobbsoker(txtFornavn.getText(), txtEtternavn.getText(), txtAdresse.getText(), txtPostnr.getText(),
                txtPoststed.getText(), txtTlf.getText(), txtEpost.getText(), txtAlder.getText(), cv, status);

        // hvis lønnskrav er satt så...
        if(txtLonnskrav.getText() != ""){
            nySoker.setLonnskrav(txtLonnskrav.getText());
        }

        RegSokerHjelper.jobbsokere.add(nySoker);

        return nySoker;
    }

    public static String utdanning(ComboBox valgUtdanning){
        return (String) valgUtdanning.getValue();
    }

    public static String studieretning(ComboBox valgRetning) {
        return (String) valgRetning.getValue();
    }

    public static Boolean slettValgtSoker(String nokkel) {
        for(int i = 0; i < jobbsokere.size(); i++){
            String tlf = jobbsokere.get(i).getTlf();
            if(tlf.equals(nokkel)){
                jobbsokere.remove(i);
                return true;
            }
        }
        return false;
    }
}
