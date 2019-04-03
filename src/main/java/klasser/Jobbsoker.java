package klasser;

import java.util.ArrayList;

public class Jobbsoker{
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String postNr;
    private String poststed;
    private String tlf;
    private String epost;
    private String alder;
    private String[] utdannelse;
    private String[] erfaring;
    private String[] referanser;
    private String lonnskrav;
    private ArrayList<String> kategorier;

    public Jobbsoker(String fornavn, String etternavn, String adresse, String postNr, String poststed, String tlf,
                     String epost, String alder, String[] utdannelse, String[] erfaring, String[] referanser,
                     String lonnskrav, ArrayList<String> kategorier) {  //
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
        this.alder = alder;
        this.utdannelse = utdannelse;
        this.erfaring = erfaring;
        this.referanser = referanser;
        this.lonnskrav = lonnskrav;
        this.kategorier = kategorier;
    }

    @Override
    public String toString(){
        String ut = "";
        ut += fornavn+";"+etternavn+";"+adresse+";"+postNr+";"+poststed+";"+tlf+";"+epost+";"+alder+";"+lonnskrav+";";
        ut += utdannelse[0]+";"+utdannelse[1]+";";
        ut += erfaring[0]+";"+erfaring[1]+";"+erfaring[2]+";"+erfaring[3]+";"+referanser[0]+";"+referanser[1]+";";
        ut += kategorier.get(0)+";"+kategorier.get(1)+";"+kategorier.get(2)+";"+kategorier.get(3)+";\n";
        return ut;

    }
}
