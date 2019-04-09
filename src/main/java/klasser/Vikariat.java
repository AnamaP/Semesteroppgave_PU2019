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

    public void setLonn(String lonn){
        this.lonn = lonn;
    }

    public String toString(){
        String ut = "";
        ut += tittel+";"+varighet+";"+lonn+";"+beskrivelse+";"+kvalifikasjoner+";";
        ut += kategorier.get(0)+";"+kategorier.get(1)+";"+kategorier.get(2)+";"+kategorier.get(3)+";";
        return ut;
    }
}
