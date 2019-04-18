package klasser;

import java.util.ArrayList;

public class Vikariat {
    private String tittel;
    private String varighet;
    private String lonn;
    private String beskrivelse;
    private String arbeidstid;
    private String kvalifikasjoner;
    private ArrayList<String> kategorier;

    public Vikariat(String tittel, String varighet, String beskrivelse, String kvalifikasjoner, String arbeidstid, ArrayList<String> kategorier) {
        this.tittel = tittel;
        this.varighet = varighet;
        this.lonn = "";
        this.beskrivelse = beskrivelse;
        this.kvalifikasjoner = kvalifikasjoner;
        this.arbeidstid = arbeidstid;
        this.kategorier = kategorier;
    }

    public String getTittel() {
        return tittel;
    }

    public String getVarighet() {
        return varighet;
    }

    public String getLonn() {
        return lonn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getArbeidstid() {
        return arbeidstid;
    }

    public String getKvalifikasjoner() {
        return kvalifikasjoner;
    }

    public void setLonn(String lonn){
        this.lonn = lonn;
    }

    public String kategorierToString() {
        String sb = "";
        for(int i = 0; i < kategorier.size(); i++){
            if(i == kategorier.size() -1){
                sb += kategorier.get(i);
            }
            else{
                sb += kategorier.get(i) + ", ";
            }
        }
        return sb;
    }

    public String toString(){
        return tittel+";"+varighet+";"+lonn+";"+beskrivelse+";"+kvalifikasjoner+";"+kategorierToString()+";";
    }
}
