package logic;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class RegWorkfieldsHelper {
    public static ArrayList<String> regWorkfields(CheckBox cbxSales, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxEconomy){

        ArrayList<String> workfields = new ArrayList<>();

        // Alternativ : lage en forløkke, hente ut med [i]+1
        if (cbxSales.isSelected()) {
            workfields.add("Salg"); // 0
        }

        if (cbxAdmin.isSelected()) {
            workfields.add("Admin"); // 1
        }

        if (cbxIt.isSelected()) {
            workfields.add("It"); // 2
        }

        if (cbxEconomy.isSelected()) {
            workfields.add("Okonomi"); // 3
        }

        return workfields;
    }
}
