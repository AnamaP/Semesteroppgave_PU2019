package org.openjfx.model.logic;

import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class WorkfieldsHelper {
    /**
     * Finner hvilke arbeidsområder som er blitt huket av under registreringen.
     */
    public static ArrayList<String> regWorkfields(CheckBox cbxSales, CheckBox cbxAdmin, CheckBox cbxIt, CheckBox cbxEconomy){

        ArrayList<String> workfields = new ArrayList<>();
        if (cbxSales.isSelected()) {
            workfields.add("Salg");
        }

        if (cbxAdmin.isSelected()) {
            workfields.add("Admin");
        }

        if (cbxIt.isSelected()) {
            workfields.add("It");
        }

        if (cbxEconomy.isSelected()) {
            workfields.add("Okonomi");
        }

        return workfields;
    }

    /**
     * Lager en string ut av en ArrayList med arbeidsområder.
     */
    public static String workfieldsToString(ArrayList workfields) {
        String sb = "";
        for(int i = 0; i < workfields.size(); i++){
            if(i == workfields.size() -1){
                sb += workfields.get(i);
            }
            else{
                sb += workfields.get(i) + ", ";
            }
        }
        return sb;
    }
}
