package klasser;

import java.util.ArrayList;

public class Vikariat {

    //Kontaktperson:
    private String fornavn;
    private String etternavn;
    private String epost;
    private String tlf;

    //Info om firma:
    private String firmaNavn;
    private String orgNr;
    private String bransje;
    private String sektor;

    //Info om vikariatet:
    private String tittel;
    private String type;
    private String lonn;
    private String kvalifikasjoner;
    private String beskrivelse;

    private String arbeidstid;
    private ArrayList<String> kategorier;

    public Vikariat(ArrayList<String> nyVikariat, String sektor, String arbeidstid, ArrayList<String> kategorier) {
        this.fornavn = nyVikariat.get(0);
        this.etternavn = nyVikariat.get(1);
        this.epost = nyVikariat.get(2);
        this.tlf = nyVikariat.get(3);
        this.firmaNavn = nyVikariat.get(4);
        this.orgNr = nyVikariat.get(5);
        this.bransje = nyVikariat.get(6);
        this.tittel = nyVikariat.get(7);
        this.type = nyVikariat.get(8);
        this.lonn = nyVikariat.get(9);
        this.kvalifikasjoner = nyVikariat.get(10);
        this.beskrivelse = nyVikariat.get(11);

        this.sektor = sektor;
        this.arbeidstid = arbeidstid;
        this.kategorier = kategorier;
    }

    public String toString(){
        String ut = "";
        ut += fornavn+";"+etternavn+";"+epost+";"+tlf+";"+firmaNavn+";"+orgNr+";"+bransje+";"+sektor+";"+tittel+";"+type+";";
        ut += lonn+";"+kvalifikasjoner+";"+beskrivelse+";"+arbeidstid+";";
        ut += kategorier.get(0)+";"+kategorier.get(1)+";"+kategorier.get(2)+";"+kategorier.get(3)+";";
        return ut;
    }
}
