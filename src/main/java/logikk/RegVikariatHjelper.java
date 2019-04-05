package logikk;

import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import klasser.Vikariat;
import java.util.ArrayList;

public class RegVikariatHjelper {

    public static Vikariat lagVikariat(
            TextField txtFornavn, TextField txtEtternavn, TextField txtEpost, TextField txtTlf, TextField txtFirmaNavn, TextField txtOrgNr,
            TextField txtBransje, TextField txtTittel, TextField txtType, TextField txtLonn, TextArea txtKvalifikasjoner, TextArea txtBeskrivelse, RadioButton radioHeltid,
            RadioButton radioDeltid, RadioButton radioOffentlig, RadioButton radioPrivat,
            CheckBox cbxSalg, CheckBox cbxService, CheckBox cbxIt, CheckBox cbxOkonomi) {

        ArrayList<String> nyVikariat = regVikariat(txtFornavn, txtEtternavn, txtEpost, txtTlf, txtFirmaNavn, txtOrgNr, txtBransje, txtTittel, txtType, txtLonn, txtKvalifikasjoner, txtBeskrivelse);

        String arbeidstid = regArbeidsTid(radioHeltid, radioDeltid);

        String sektor = regSektor(radioOffentlig, radioPrivat);

        ArrayList<String> kategorier = regKategori(cbxSalg,cbxService,cbxIt,cbxOkonomi);

        return new Vikariat(nyVikariat, arbeidstid, sektor, kategorier);
    }


    private static ArrayList<String> regVikariat(
            TextField txtFornavn, TextField txtEtternavn, TextField txtEpost, TextField txtTlf, TextField txtFirmaNavn, TextField txtOrgNr,
            TextField txtBransje, TextField txtTittel, TextField txtType, TextField txtLonn, TextArea txtKvalifikasjoner, TextArea txtBeskrivelse){

        ArrayList<String> vikariat = new ArrayList<>();

        vikariat.add(txtFornavn.getText());
        vikariat.add(txtEtternavn.getText());
        vikariat.add(txtEpost.getText());
        vikariat.add(txtTlf.getText());
        vikariat.add(txtFirmaNavn.getText());
        vikariat.add(txtOrgNr.getText());
        vikariat.add(txtBransje.getText());
        vikariat.add(txtTittel.getText());
        vikariat.add(txtType.getText());
        vikariat.add(txtLonn.getText());
        vikariat.add(txtKvalifikasjoner.getText());
        vikariat.add(txtBeskrivelse.getText());

        return vikariat;
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


    private static String regSektor(RadioButton radioOffentlig, RadioButton radioPrivat) {

        String sektor = "sektor ikke valgt";

        if(radioOffentlig.isSelected()){
            sektor = "Offentlig";
        }
        if(radioPrivat.isSelected()){
            sektor = "Privat";
        }

        return sektor;
    }


    private static ArrayList<String> regKategori(CheckBox cbxSalg, CheckBox cbxService, CheckBox cbxIt, CheckBox cbxOkonomi){

        ArrayList<String> kategorier = new ArrayList<>();

        // Alternativ : lage en forløkke, hente ut med [i]+1
        if (cbxSalg.isSelected()) {
            kategorier.add("Salg"); // 0
        } else {
            kategorier.add("NULL");
        }

        if (cbxService.isSelected()) {
            kategorier.add("Service"); // 1
        } else {
            kategorier.add("NULL");
        }

        if (cbxIt.isSelected()) {
            kategorier.add("It"); // 2
        } else {
            kategorier.add("NULL");
        }

        if (cbxOkonomi.isSelected()) {
            kategorier.add("Okonomi"); // 3
        } else {
            kategorier.add("NULL");
        }

        return kategorier;
    }

}
