package klasser;

import java.util.ArrayList;

public class Vikariat {
    private String tittel;
    private String varighet;
    private String lonn;
    private String beskrivelse;
    private String stillingstype;
    private String kvalifikasjoner;
    private ArrayList<String> kategorier;
    private String status;

    public Vikariat(String tittel, String varighet, String beskrivelse, String kvalifikasjoner, String stillingstype, ArrayList<String> kategorier, String status) {
        this.tittel = tittel;
        this.varighet = varighet;
        this.lonn = "";
        this.beskrivelse = beskrivelse;
        this.kvalifikasjoner = kvalifikasjoner;
        this.stillingstype = stillingstype;
        this.kategorier = kategorier;
        this.status = status;
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

    public String getStillingstype() {
        return stillingstype;
    }

    public String getKvalifikasjoner() {
        return kvalifikasjoner;
    }

    public String getStatus(){
        return status;
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
        return tittel+";"+varighet+";"+lonn+";"+beskrivelse+";"+stillingstype+";"+kvalifikasjoner+";"+kategorierToString()+";"+status;
    }
}
