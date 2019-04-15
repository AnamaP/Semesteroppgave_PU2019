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
        this.referanse = "";
        this.kategorier = kategorier;
    }

    public void setReferanse(String referanse){
        this.referanse = referanse;
    }

    public String getUtdanning() {
        return utdanning;
    }

    public String getStudieretning() {
        return studieretning;
    }

    public String getErfaring() {
        return erfaring;
    }

    public String getReferanse() {
        return referanse;
    }

    public String getKategorier() {
        return kategorier.toString();
    }

    public String toString(){
        return utdanning+";"+studieretning+";"+erfaring+";"+referanse+";"+getKategorier()+";";
    }
}
