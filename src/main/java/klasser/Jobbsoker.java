package klasser;

import java.io.Serializable;
import java.util.ArrayList;

public class Jobbsoker extends Person {
    private Cv cv;
    private String lonnskrav;


    public Jobbsoker(String fornavn, String etternavn, String adresse, String postnr, String poststed, String tlf,
                     String epost, String alder, Cv cv) {
        super(fornavn, etternavn, adresse, postnr, poststed, tlf, epost, alder);
        this.cv = cv;
        this.lonnskrav = "";
    }

/*
    @Override
    public String toString(){
        String ut = "";
        ut += fornavn+";"+etternavn+";"+adresse+";"+postNr+";"+poststed+";"+tlf+";"+epost+";"+alder+";"+lonnskrav+";";
        ut += utdannelse.get(0)+";"+utdannelse.get(1)+";"+utdannelse.get(2)+";"+utdannelse.get(3)+";"+utdannelse.get(4)+
                ";"+utdannelse.get(5)+";"+utdannelse.get(6)+";"+utdannelse.get(7)+";";
        ut += erfaring.get(0)+";"+erfaring.get(1)+";"+erfaring.get(2)+";"+erfaring.get(3)+";"+";"+erfaring.get(4)+";"+erfaring.get(5)+";";
        ut += referanser.get(0)+";"+referanser.get(1)+";";
        ut += kategorier.get(0)+";"+kategorier.get(1)+";"+kategorier.get(2)+";"+kategorier.get(3)+";";

        return ut;
    } */
}
