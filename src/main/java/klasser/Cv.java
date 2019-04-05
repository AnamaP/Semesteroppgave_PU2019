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
        this.referanse = ""; //Lag settere for denne
        this.kategorier = kategorier;
    }

    public String toString(){
        String ut = "";
        ut += utdanning+";"+studieretning+";"+erfaring+";"+referanse+";";
        ut += kategorier.get(0)+";"+kategorier.get(1)+";"+kategorier.get(2)+";"+kategorier.get(3)+";";
        return ut;
    }
}
