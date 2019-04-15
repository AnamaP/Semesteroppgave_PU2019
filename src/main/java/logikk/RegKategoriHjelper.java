package logikk;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class RegKategoriHjelper {
    public static ArrayList<String> regKategori(CheckBox cbxSalg, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxOkonomi){

        ArrayList<String> kategorier = new ArrayList<>();

        // Alternativ : lage en forløkke, hente ut med [i]+1
        if (cbxSalg.isSelected()) {
            kategorier.add("Salg"); // 0
        } else {
            kategorier.add("");
        }

        if (cbxAdmin.isSelected()) {
            kategorier.add("Admin"); // 1
        } else {
            kategorier.add("");
        }

        if (cbxIt.isSelected()) {
            kategorier.add("It"); // 2
        } else {
            kategorier.add("");
        }

        if (cbxOkonomi.isSelected()) {
            kategorier.add("Okonomi"); // 3
        } else {
            kategorier.add("");
        }

        return kategorier;
    }
}
