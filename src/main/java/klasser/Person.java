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
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.postnr = postnr;
        this.poststed = poststed;
        this.tlf = tlf;
        this.epost = epost;
        this.alder = alder;
    }
}
