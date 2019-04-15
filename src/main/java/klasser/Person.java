package klasser;

import java.io.Serializable;

public class Person implements Serializable {
    private String fornavn;
    private String etternavn;
    private String adresse;
    private String postnr;
    private String poststed;
    private String tlf;
    private String epost;
    private String alder;

    public Person(String fornavn, String etternavn, String adresse, String postnr, String poststed, String tlf, String epost, String alder) {

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
        if(postnr != null){
            this.postnr = postnr;
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
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getPostnr() {
        return postnr;
    }

    public String getPoststed() {
        return poststed;
    }

    public String getTlf() {
        return tlf;
    }

    public String getEpost() {
        return epost;
    }

    public String getAlder() {
        return alder;
    }

    public String toString() {
        return fornavn+";"+etternavn+";"+adresse+";"+postnr+";"+poststed+";"+tlf+";"+epost+";"+alder;
    }
}
