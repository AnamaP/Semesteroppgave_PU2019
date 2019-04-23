package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Arbeidsgiver;

public class TabellVikariater {
    private final SimpleStringProperty kontaktperson; //0
    private final SimpleStringProperty tlf; //1
    private final SimpleStringProperty sektor; //2
    private final SimpleStringProperty firmanavn; //3
    private final SimpleStringProperty orgnr;
    private final SimpleStringProperty bransje; //4
    private final SimpleStringProperty stillingstittel; //5
    private final SimpleStringProperty stillingstype;
    private final SimpleStringProperty varighet; //6
    private final SimpleStringProperty lonn;
    private final SimpleStringProperty kvalifikasjoner; //7
    private final SimpleStringProperty kategorier; // 8,9,10,11

    // en toVikariat metode som koverterer all data her til en Arbeidsgiver
    public TabellVikariater(Arbeidsgiver arbeidsgiver){
        this.kontaktperson = new SimpleStringProperty(arbeidsgiver.getKontaktperson());
        this.tlf = new SimpleStringProperty(arbeidsgiver.getTlf());
        this.sektor= new SimpleStringProperty(arbeidsgiver.getSektor());
        this.firmanavn = new SimpleStringProperty(arbeidsgiver.getFirmanavn());
        this.orgnr = new SimpleStringProperty(arbeidsgiver.getOrgnr());
        this.bransje = new SimpleStringProperty(arbeidsgiver.getBransje());
        this.stillingstittel = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getTittel());
        this.stillingstype = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getArbeidstid());
        this.varighet = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getVarighet());
        this.lonn = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getLonn());
        this.kvalifikasjoner = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getKvalifikasjoner());
        this.kategorier = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().kategorierToString());
    }

    public SimpleStringProperty kontaktpersonProperty() {
        return kontaktperson;
    }
    public String getTlf() {
        return tlf.get();
    }
    public SimpleStringProperty tlfProperty() {
        return tlf;
    }
    public void setTlf(String tlf) {
        this.tlf.set(tlf);
    }
    public SimpleStringProperty sektorProperty() {
        return sektor;
    }
    public SimpleStringProperty firmanavnProperty() {
        return firmanavn;
    }
    public SimpleStringProperty orgnrProperty() {return orgnr;}
    public SimpleStringProperty bransjeProperty() {
        return bransje;
    }
    public SimpleStringProperty stillingstittelProperty() {
        return stillingstittel;
    }
    public String getStillingstype() { return stillingstype.get();}
    public SimpleStringProperty stillingstypeProperty(){ return stillingstype;}
    public void setStillingstype(String stillingstype){ this.stillingstype.set(stillingstype);}
    public SimpleStringProperty varighetProperty() {
        return varighet;
    }
    public SimpleStringProperty lonnProperty(){ return lonn;}
    public String getKvalifikasjoner() {
        return kvalifikasjoner.get();
    }
    public SimpleStringProperty kvalifikasjonerProperty() {
        return kvalifikasjoner;
    }
    public void setKvalifikasjoner(String kvalifikasjoner) {
        this.kvalifikasjoner.set(kvalifikasjoner);
    }
    public String getKategorier() {
        return kategorier.get();
    }
    public SimpleStringProperty kategorierProperty() {
        return kategorier;
    }
    public void setKategorier(String kategorier) {
        this.kategorier.set(kategorier);
    }
}
