package logikk;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klasser.Arbeidsgiver;
import klasser.Vikariat;
import java.util.ArrayList;

public class RegVikariatHjelper {

    // lage en statisk array list med arbeidsgivere - ved kall på "lagVikariat" så legges arb.giver i listen
    public static ArrayList<Arbeidsgiver> arbeidsgivere = new ArrayList<>();

    public RegVikariatHjelper(ArrayList<Arbeidsgiver> arbeidsgivere){
        Filhandterer filhandterer = new CsvFilhandterer();
        Object liste = filhandterer.henteFraFil(Paths.VIKARIAT_CSV);

    }

    public static Arbeidsgiver lagVikariat(
            TextField txtKontaktperson, TextField txtTlf, TextField txtSektor, TextField txtFirmaNavn, TextField txtOrgNr,
            TextField txtBransje, TextField txtStillingstittel, TextField txtVarighet, TextField txtLonn,
            RadioButton radioHeltid, RadioButton radioDeltid, TextField txtKvalifikasjoner,  TextArea txtBeskrivelse,
            CheckBox cbxSalg, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxOkonomi) {

        ArrayList<String> kategorier = RegKategoriHjelper.regKategori(cbxSalg,cbxAdmin,cbxIt,cbxOkonomi);

        String stillingsType = regArbeidsTid(radioHeltid, radioDeltid);

        Vikariat nyttVikariat = new Vikariat(txtStillingstittel.getText(), txtVarighet.getText(), txtBeskrivelse.getText(),
                                             txtKvalifikasjoner.getText(), stillingsType, kategorier);

        // hvis "antatt årslønn" er satt så...
        if(txtLonn.getText() != ""){
            nyttVikariat.setLonn(txtLonn.getText());
        }

        Arbeidsgiver nyArbeidsgiver = new Arbeidsgiver(txtKontaktperson.getText(),txtTlf.getText(),txtSektor.getText(),
                                        txtFirmaNavn.getText(), txtOrgNr.getText(), txtBransje.getText(), nyttVikariat);

        RegVikariatHjelper.arbeidsgivere.add(nyArbeidsgiver);

        return nyArbeidsgiver;
    }

    private static String regArbeidsTid(RadioButton radioHeltid, RadioButton radioDeltid) {

        String arbeidstid = "arbeidstid ikke valgt";

        if(radioHeltid.isSelected()){
            arbeidstid = "Heltid";
        }
        if(radioDeltid.isSelected()){
            arbeidstid = "Deltid";
        }

        return arbeidstid;
    }

}
