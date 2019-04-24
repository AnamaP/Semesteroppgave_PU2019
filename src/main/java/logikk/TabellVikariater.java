package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Arbeidsgiver;

public class TabellVikariater {
    private final SimpleStringProperty kontaktperson; //0
    private final SimpleStringProperty tlf; //1
    private final SimpleStringProperty sektor; //2
    private final SimpleStringProperty firmanavn; //3
    private final SimpleStringProperty adresse; //4
    private final SimpleStringProperty bransje; //5
    private final SimpleStringProperty stillingstittel; //6
    private final SimpleStringProperty stillingstype; //7
    private final SimpleStringProperty kategorier; // 11,12,13,14
    private final SimpleStringProperty status; //15

    // en toVikariat metode som koverterer all data her til en Arbeidsgiver
    public TabellVikariater(Arbeidsgiver arbeidsgiver){
        this.kontaktperson = new SimpleStringProperty(arbeidsgiver.getKontaktperson());
        this.tlf = new SimpleStringProperty(arbeidsgiver.getTlf());
        this.sektor= new SimpleStringProperty(arbeidsgiver.getSektor());
        this.firmanavn = new SimpleStringProperty(arbeidsgiver.getFirmanavn());
        this.adresse = new SimpleStringProperty(arbeidsgiver.getAdresse());
        this.bransje = new SimpleStringProperty(arbeidsgiver.getBransje());
        this.stillingstittel = new SimpleStringProperty(arbeidsgiver.getVikariat().getTittel());
        this.stillingstype = new SimpleStringProperty(arbeidsgiver.getVikariat().getStillingstype());
        this.kategorier = new SimpleStringProperty(arbeidsgiver.getVikariat().kategorierToString());
        this.status = new SimpleStringProperty(arbeidsgiver.getVikariat().getStatus());
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
    public SimpleStringProperty adresseProperty() {return adresse;}
    public SimpleStringProperty bransjeProperty() {
        return bransje;
    }
    public SimpleStringProperty stillingstittelProperty() {
        return stillingstittel;
    }
    public SimpleStringProperty stillingstypeProperty(){ return stillingstype;}
    public String getKategorier() {
        return kategorier.get();
    }
    public SimpleStringProperty kategorierProperty() {
        return kategorier;
    }
    public void setKategorier(String kategorier) {
        this.kategorier.set(kategorier);
    }
    public SimpleStringProperty statusProperty() { return status; }
}
