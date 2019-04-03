package klasser;

// skal ikke arve fra person
public class Arbeidsgiver{
    private String orgNr;
    private String sektor;
    private String bransje;
    private String navn;
    private String[] kategori;

    public Arbeidsgiver(String orgNr, String sektor, String bransje, String navn, String[] kategori) {
        this.orgNr = orgNr;
        this.sektor = sektor;
        this.bransje = bransje;
        this.navn = navn;
        this.kategori = kategori;
    }
}
