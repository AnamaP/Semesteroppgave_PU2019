package klasser;

// skal ikke arve fra person
public class Arbeidsgiver extends Person{
    private String orgNr;
    private String sektor;
    private String bransje;
    private String navn;
    private String[] kategori;

    public Arbeidsgiver(String fornavn, String etternavn, String adresse, String postNr, String poststed, String tlf,
                        String epost, String orgNr, String sektor, String bransje, String navn, String[] kategori){
        super(fornavn, etternavn, adresse, postNr, poststed, tlf, epost);
        this.orgNr = orgNr;
        this.sektor = sektor;
        this.bransje = bransje;
        this.navn = navn;
        this.kategori = kategori;
    }
}
