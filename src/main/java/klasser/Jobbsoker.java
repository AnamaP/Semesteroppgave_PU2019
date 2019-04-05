package klasser;

import java.io.Serializable;
import java.util.ArrayList;

public class Jobbsoker extends Person {
    private Cv cv;


    public Jobbsoker(String fornavn, String etternavn, String adresse, String postnr, String poststed, String tlf,
                     String epost, String alder, Cv cv) {
        super(fornavn, etternavn, adresse, postnr, poststed, tlf, epost, alder);
        this.cv = cv;
    }



    /*
        this.fornavn = personalia.get(0);
        this.etternavn = personalia.get(1);
        this.adresse = personalia.get(2);
        this.postNr = personalia.get(3);
        this.poststed = personalia.get(4);
        this.tlf = personalia.get(5);
        this.epost = personalia.get(6);
        this.alder = personalia.get(7);
        this.lonnskrav = personalia.get(8);

        this.utdannelse = utdannelse;
        this.erfaring = erfaring;
        this.referanser = referanser;
        this.kategorier = kategorier;


        // Feilhåndtering - sjekk heller her om variabelen er null og kaste en feilmelding
        if(fornavn != null){
            this.fornavn = fornavn;
        }
        if(etternavn != null){
            this.etternavn = etternavn;
        }
        if(adresse != null){
            this.adresse = adresse;
        }
        if(postNr != null){
            this.postNr = postNr;
        }
        if(poststed != null){
            this.poststed = poststed;
        }
        if(tlf != null){
            this.tlf = tlf;
        }
        if(epost != null){
            this.epost = epost;
        }

    }

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
    }*/
}
