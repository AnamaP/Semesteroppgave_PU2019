package klasser;

// skal ikke arve fra person
public class VikariatGammel {
    private int lønn;
    private String stillingsstype;
    private String beskrivelse;
    private int stillingsbrøk;
    private String kvalifikasjoner;
    private String varighet;
    private String arbeidsstid;
    private String[] søkere;

    public VikariatGammel(int lønn, String stillingsstype, String beskrivelse, int stillingsbrøk, String kvalifikasjoner, String varighet, String arbeidsstid, String[] søkere) {
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
