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
        this.lonnskrav = "";  // Lag setter da lokhue..!!!
    }

    @Override
    public String toString(){
        String ut = super.toString() +";"+lonnskrav+";";
        return ut;
    }
}
