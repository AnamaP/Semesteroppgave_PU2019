package klasser;

import java.io.Serializable;

public class Arbeidsgiver implements Serializable {
    private String kontaktperson;
    private String tlf;
    private String sektor;
    private String firmanavn;
    private String adresse;
    private String bransje;
    private Vikariat vikariat;

    public Arbeidsgiver(String kontaktperson, String tlf, String sektor, String firmanavn, String adresse, String bransje, Vikariat vikariat) {
        this.kontaktperson = kontaktperson;
        this.tlf = tlf;
        this.sektor = sektor;
        this.firmanavn = firmanavn;
        this.adresse = adresse;
        this.bransje = bransje;
        this.vikariat = vikariat;
    }

    public void setKontaktperson(String kontaktperson) {
        this.kontaktperson = kontaktperson;
    }
    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
    public void setSektor(String sektor) {
        this.sektor = sektor;
    }
    public void setFirmanavn(String firmanavn) {
        this.firmanavn = firmanavn;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setBransje(String bransje) {
        this.bransje = bransje;
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
    public String getAdresse() {
        return adresse;
    }
    public String getBransje() {
        return bransje;
    }
    public Vikariat getVikariat() {
        return vikariat;
    }

    public String toString(){
        String ut = "";
        ut += kontaktperson+";"+tlf+";"+sektor+";"+firmanavn+";"+ adresse +";"+bransje+";"+vikariat.toString();
        return ut;
    }


}
