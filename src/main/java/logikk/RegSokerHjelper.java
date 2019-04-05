package logikk;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import klasser.Jobbsoker;
import java.util.ArrayList;

public class RegSokerHjelper {

    public static String utdanning(ComboBox valgUtdanning){
        String utdanning = "";
        //Her skal man hente ut valg av utdanningstype.
        return utdanning;
    }

    public static String studieretning(ComboBox valgRetning) {
        String studieretning = "";
        //Her skal man kunne hente ut studieretning.
        return studieretning;
    }


    public static ArrayList<String> regKategori(CheckBox cbxSalg, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxOkonomi){

        ArrayList<String> kategorier = new ArrayList<>();

        // Alternativ : lage en forløkke, hente ut med [i]+1
        if (cbxSalg.isSelected()) {
            kategorier.add("Salg"); // 0
        } else {
            kategorier.add("NULL");
        }

        if (cbxAdmin.isSelected()) {
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
