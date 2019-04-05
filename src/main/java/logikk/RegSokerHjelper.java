package logikk;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;

public class RegSokerHjelper {

    public static String utdanning(ComboBox valgUtdanning){
        String utdanning = "";

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
        String studieretning = "";
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
