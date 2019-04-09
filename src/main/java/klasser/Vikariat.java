package klasser;

import java.util.ArrayList;

public class Vikariat {
    private String tittel;
    private String type;
    private String lonn;
    private String beskrivelse;
    private String arbeidstid;
    private String kvalifikasjoner;

    private ArrayList<String> kategorier;

    public Vikariat(String tittel, String type, String lonn, String beskrivelse, String kvalifikasjoner, String arbeidstid, ArrayList<String> kategorier) {
        this.tittel = tittel;
        this.type = type;
        this.lonn = lonn;
        this.beskrivelse = beskrivelse;
        this.kvalifikasjoner = kvalifikasjoner;
        this.arbeidstid = arbeidstid;
        this.kategorier = kategorier;
    }

    public String toString(){
        String ut = "";
        ut += tittel+";"+type+";"+lonn+";"+beskrivelse+";"+kvalifikasjoner+";";
        ut += kategorier.get(0)+";"+kategorier.get(1)+";"+kategorier.get(2)+";"+kategorier.get(3)+";";
        return ut;
    }
}
