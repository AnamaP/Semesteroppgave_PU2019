package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Arbeidsgiver;

public class TabellVikariater {
    private final SimpleStringProperty kontaktperson;
    private final SimpleStringProperty tlf;
    private final SimpleStringProperty sektor;
    private final SimpleStringProperty firmanavn;
    private final SimpleStringProperty bransje;
    private final SimpleStringProperty stillingstittel;
    private final SimpleStringProperty varighet;
    private final SimpleStringProperty kvalifikasjoner;
    private final SimpleStringProperty kategorier;

    // en toVikariat metode som koverterer all data her til en Arbeidsgiver
    public TabellVikariater(Arbeidsgiver arbeidsgiver){
        this.kontaktperson = new SimpleStringProperty(arbeidsgiver.getKontaktperson());
        this.tlf = new SimpleStringProperty(arbeidsgiver.getTlf());
        this.sektor= new SimpleStringProperty(arbeidsgiver.getSektor());
        this.firmanavn = new SimpleStringProperty(arbeidsgiver.getFirmanavn());
        this.bransje = new SimpleStringProperty(arbeidsgiver.getBransje());
        this.stillingstittel = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getTittel());
        this.varighet = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getVarighet());
        this.kvalifikasjoner = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getKvalifikasjoner());
        this.kategorier = new SimpleStringProperty(arbeidsgiver.getNyttVikariat().getKategorier().toString());
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
    public SimpleStringProperty bransjeProperty() {
        return bransje;
    }
    public SimpleStringProperty stillingstittelProperty() {
        return stillingstittel;
    }
    public SimpleStringProperty varighetProperty() {
        return varighet;
    }
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
