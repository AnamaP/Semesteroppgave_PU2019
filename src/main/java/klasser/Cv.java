package klasser;

import java.util.ArrayList;

public class Cv {
    private String utdanning;
    private String studieretning;
    private String erfaring;
    private String referanse;
    private ArrayList<String> kategorier;

    public Cv(String utdanning, String studieretning, String erfaring, ArrayList<String> kategorier) {
        this.utdanning = utdanning;
        this.studieretning = studieretning;
        this.erfaring = erfaring;
        this.referanse = ""; //Lag settere for disse
        this.kategorier = kategorier;
    }
}
