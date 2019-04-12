package klasser;

import java.io.Serializable;

public class Arbeidsgiver implements Serializable {
    private String kontaktperson;
    private String tlf;
    private String sektor;
    private String firmanavn;
    private String orgnr;
    private String bransje;
    private Vikariat nyttVikariat;

    public Arbeidsgiver(String kontaktperson, String tlf, String sektor, String firmanavn, String orgnr, String bransje, Vikariat nyttVikariat) {
        this.kontaktperson = kontaktperson;
        this.tlf = tlf;
        this.sektor = sektor;
        this.firmanavn = firmanavn;
        this.orgnr = orgnr;
        this.bransje = bransje;
        this.nyttVikariat = nyttVikariat;
    }

    public String getKontaktperson() {
        return kontaktperson;
    }

    public String getTlf() {
        return tlf;
    }

    public String getSektor() {
        return sektor;
    }

    public String getFirmanavn() {
        return firmanavn;
    }

    public String getOrgnr() {
        return orgnr;
    }

    public String getBransje() {
        return bransje;
    }

    public Vikariat getNyttVikariat() {
        return nyttVikariat;
    }

    public String toString(){
        String ut = "";
        ut += kontaktperson+";"+tlf+";"+sektor+";"+firmanavn+";"+orgnr+";"+bransje+";"+nyttVikariat.toString();
        return ut;
    }


}
