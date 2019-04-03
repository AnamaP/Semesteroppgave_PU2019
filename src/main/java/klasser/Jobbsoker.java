package klasser;

public class Jobbsoker extends Person {
    private String alder;
    private String[] utdannelse;
    private String[] erfaring;
    private String[] referanser;
    private int lonnskrav;
    private String[] kategori;

    public Jobbsoker(String fornavn, String etternavn, String adresse, String postNr, String poststed, String tlf,
                     String epost, String alder, String[] utdannelse, String[] erfaring, String[] referanser, int lonnskrav, String[] kategori) {
        super(fornavn, etternavn, adresse, postNr, poststed, tlf, epost);
        this.alder = alder;
        this.utdannelse = utdannelse;
        this.erfaring = erfaring;
        this.referanser = referanser;
        this.lonnskrav = lonnskrav;
        this.kategori = kategori;
    }
}
