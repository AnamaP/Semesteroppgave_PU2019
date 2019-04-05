package logikk;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import klasser.Jobbsoker;
import java.util.ArrayList;

public class RegistrerSoker {

    public static Jobbsoker lagJobssoker(
            TextField txtFornavn, TextField txtEtternavn, TextField txtAdresse,TextField txtPostnr,TextField txtPoststed,
            TextField txtTlf, TextField txtEpost, TextField txtAlder,TextField txtLonnskrav,

            TextField txtStud1,TextField txtStud2, TextField txtSkole1, TextField txtSkole2, TextField txtAar1A,
            TextField txtAar1B, TextField txtAar2A, TextField txtAar2B,

            TextField txtStilling1, TextField txtStilling2, TextField txtDato1A, TextField txtDato1B,TextField txtDato2A,
            TextField txtDato2B,

            TextField txtReferanse1, TextField txtReferanse2,

            CheckBox cbxSalg, CheckBox cbxService, CheckBox cbxIt, CheckBox cbxOkonomi) {



        ArrayList<String> utdannelse = regUtdannelse(txtStud1,txtStud2,txtSkole1,txtSkole2,txtAar1A,txtAar1B,txtAar2A,txtAar2B);

        ArrayList<String> erfaring = regErfaring(txtStilling1,txtStilling2,txtDato1A,txtDato1B,txtDato2A,txtDato2B);

        ArrayList<String> referanser = regReferanse(txtReferanse1, txtReferanse2);

        ArrayList<String> kategorier = regKategori(cbxSalg,cbxService,cbxIt,cbxOkonomi);


        return new Jobbsoker(personalia,utdannelse,erfaring,referanser,kategorier);
    }



    private static ArrayList<String> regUtdannelse(
            TextField txtStud1,TextField txtStud2, TextField txtSkole1, TextField txtSkole2, TextField txtAar1A,
            TextField txtAar1B, TextField txtAar2A, TextField txtAar2B){

        ArrayList<String> utdannelse = new ArrayList<>();

        utdannelse.add(txtStud1.getText()); // 0
        utdannelse.add(txtSkole1.getText()); // 1
        utdannelse.add(txtAar1A.getText()); // 2
        utdannelse.add(txtAar1B.getText()); // 3
        utdannelse.add(txtStud2.getText()); // 4
        utdannelse.add(txtSkole2.getText()); // 5
        utdannelse.add(txtAar2A.getText()); // 6
        utdannelse.add(txtAar2B.getText()); // 7

        return utdannelse;
    }

    private static ArrayList<String> regErfaring(
            TextField txtStilling1, TextField txtStilling2, TextField txtDato1A, TextField txtDato1B,TextField txtDato2A,
            TextField txtDato2B){

        ArrayList<String> erfaring = new ArrayList<>();

        erfaring.add(txtStilling1.getText()); // 0
        erfaring.add(txtDato1A.getText()); // 1
        erfaring.add(txtDato1B.getText()); // 2
        erfaring.add(txtStilling2.getText()); // 3
        erfaring.add(txtDato2A.getText()); //4
        erfaring.add(txtDato2B.getText()); // 5

        return erfaring;
    }

    private static ArrayList<String> regReferanse(TextField txtReferanse1, TextField txtReferanse2){

        ArrayList<String> referanser = new ArrayList<>();

        referanser.add(txtReferanse1.getText()); // 0
        referanser.add(txtReferanse2.getText()); // 1

        return referanser;
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
