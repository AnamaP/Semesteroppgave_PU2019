package klasser;

public class Person {
    String fornavn;
    String etternavn;
    String adresse;
    String postNr;
    String poststed;
    String tlf;
    String epost;

    public Person(String fornavn, String etternavn, String adresse, String postNr, String poststed, String tlf, String epost) {
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
}
