package klasser;

// skal ikke arve fra person
public class Vikariat extends Person {
    private int lønn;
    private String stillingsstype;
    private String beskrivelse;
    private int stillingsbrøk;
    private String kvalifikasjoner;
    private String varighet;
    private String arbeidsstid;
    private String[] søkere;

    public Vikariat(String fornavn, String etternavn, String adresse, String postNr, String poststed, String tlf,
                    String epost, int lønn, String stillingsstype, String beskrivelse, int stillingsbrøk,
                    String kvalifikasjoner, String varighet, String arbeidsstid, String[] søkere) {
        super(fornavn, etternavn, adresse, postNr, poststed, tlf, epost);
        this.lønn = lønn;
        this.stillingsstype = stillingsstype;
        this.beskrivelse = beskrivelse;
        this.stillingsbrøk = stillingsbrøk;
        this.kvalifikasjoner = kvalifikasjoner;
        this.varighet = varighet;
        this.arbeidsstid = arbeidsstid;
        this.søkere = søkere;
    }
}
