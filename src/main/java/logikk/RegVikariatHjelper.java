package logikk;

import javafx.scene.control.*;
import klasser.Arbeidsgiver;
import klasser.Vikariat;
import java.util.ArrayList;

public class RegVikariatHjelper {

    public static ArrayList<Arbeidsgiver> arbeidsgivere = new ArrayList<>();

    public static Arbeidsgiver lagVikariat(
            TextField txtKontaktperson, TextField txtTlf, TextField txtSektor, TextField txtFirmaNavn, TextField txtAdresse,
            TextField txtBransje, TextField txtStillingstittel, TextField txtVarighet, TextField txtLonn,
            RadioButton radioHeltid, RadioButton radioDeltid, TextField txtKvalifikasjoner,  TextArea txtBeskrivelse,
            CheckBox cbxSalg, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxOkonomi, String status) {

        ArrayList<String> kategorier = RegKategoriHjelper.regKategori(cbxSalg,cbxAdmin,cbxIt,cbxOkonomi);

        String stillingstype = regArbeidsTid(radioHeltid, radioDeltid);

        Vikariat nyttVikariat = new Vikariat(txtStillingstittel.getText(), stillingstype, txtBeskrivelse.getText(), txtVarighet.getText(),
                                             txtLonn.getText(), txtKvalifikasjoner.getText(), kategorier, status);

        Arbeidsgiver nyArbeidsgiver = new Arbeidsgiver(txtKontaktperson.getText(),txtTlf.getText(),txtSektor.getText(),
                                        txtFirmaNavn.getText(), txtAdresse.getText(), txtBransje.getText(), nyttVikariat);

        RegVikariatHjelper.arbeidsgivere.add(nyArbeidsgiver);

        return nyArbeidsgiver;
    }

    private static String regArbeidsTid(RadioButton radioHeltid, RadioButton radioDeltid) {

        String arbeidstid = "Arbeidstid ikke valgt";

        if(radioHeltid.isSelected()){
            arbeidstid = "Heltid";
        }
        if(radioDeltid.isSelected()){
            arbeidstid = "Deltid";
        }

        return arbeidstid;
    }
}
