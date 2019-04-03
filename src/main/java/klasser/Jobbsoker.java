package klasser;

public class Jobbsoker extends Person {
    private String alder;
    private Utdannelse [] utdannelse;
    private String[] erfaring;
    private String[] referanser;
    private String lonnskrav;
    //private String[] kategori;

    public Jobbsoker(String fornavn, String etternavn, String adresse, String postNr, String poststed, String tlf,
                     String epost, String alder, Utdannelse [] utdannelse, String[] erfaring, String[] referanser,
                     String lonnskrav) {  //, String[] kategori
        super(fornavn, etternavn, adresse, postNr, poststed, tlf, epost);
        this.alder = alder;
        this.utdannelse = utdannelse;
        this.erfaring = erfaring;
        this.referanser = referanser;
        this.lonnskrav = lonnskrav;
        //this.kategori = kategori;
    }

    @Override
    public String toString(){
        String ut = "";
        ut += "Navn: "+fornavn+" "+etternavn+"\nAdresse: "+adresse+" "+postNr+" "+poststed+"\n";
        ut += "Tlf: "+tlf+"\nEpost: "+epost+"\nAlder: "+alder;
        return ut;
    }
}
