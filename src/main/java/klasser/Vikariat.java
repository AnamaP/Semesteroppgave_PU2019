package klasser;

import java.io.Serializable;
import java.util.ArrayList;

public class Vikariat implements Serializable {
    private String tittel;
    private String stillingstype;
    private String beskrivelse;
    private String varighet;
    private String lonn;
    private String kvalifikasjoner;
    private ArrayList<String> kategorier;
    private String status;

    public Vikariat(String tittel, String stillingstype, String beskrivelse, String varighet, String lonn, String kvalifikasjoner, ArrayList<String> kategorier, String status) {
        this.tittel = tittel;
        this.stillingstype = stillingstype;
        this.beskrivelse = beskrivelse;
        this.varighet = varighet;
        this.lonn = lonn;
        this.kvalifikasjoner = kvalifikasjoner;
        this.kategorier = kategorier;
        this.status = status;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }
    public void setStillingstype(String stillingstype) {
        this.stillingstype = stillingstype;
    }
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
    public void setVarighet(String varighet) {
        this.varighet = varighet;
    }
    public void setLonn(String lonn) {
        this.lonn = lonn;
    }
    public void setKvalifikasjoner(String kvalifikasjoner) {
        this.kvalifikasjoner = kvalifikasjoner;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getTittel() {
        return tittel;
    }
    public String getStillingstype() {
        return stillingstype;
    }
    public String getBeskrivelse() {
        return beskrivelse;
    }
    public String getVarighet() {
        return varighet;
    }
    public String getLonn() {
        return lonn;
    }
    public String getKvalifikasjoner() {
        return kvalifikasjoner;
    }
    public ArrayList<String> getKategorier() {
        return kategorier;
    }
    public String getStatus() {
        return status;
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
        return tittel+";"+stillingstype+";"+beskrivelse+";"+varighet+";"+lonn+";"+kvalifikasjoner+";"+kategorierToString()+";"+status;
    }
}
