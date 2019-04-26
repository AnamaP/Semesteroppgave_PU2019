package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Arbeidsgiver;

import static logikk.OversiktVikariaterHjelper.findArbeidsgiver;
import static logikk.RegVikariatHjelper.arbeidsgivere;

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

    public String getKontaktperson() {
        return kontaktperson.get();
    }

    public String getSektor() {
        return sektor.get();
    }

    public String getFirmanavn() {
        return firmanavn.get();
    }

    public String getAdresse() {
        return adresse.get();
    }

    public String getBransje() {
        return bransje.get();
    }

    public String getStillingstittel() {
        return stillingstittel.get();
    }

    public String getStillingstype() {
        return stillingstype.get();
    }

    public String getStatus() {
        return status.get();
    }

    public void setKontaktperson(int chosenArbeidsgiver, String nyKontaktperson) {
        arbeidsgivere.get(chosenArbeidsgiver).setKontaktperson(nyKontaktperson);
        MainAppHelper reload = new MainAppHelper();
        reload.reloadVikariaterDatabase();
        this.kontaktperson.set(nyKontaktperson);
    }

    public void setSektor(String sektor) {
        this.sektor.set(sektor);
    }

    public void setFirmanavn(String firmanavn) {
        this.firmanavn.set(firmanavn);
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public void setBransje(String bransje) {
        this.bransje.set(bransje);
    }

    public void setStillingstittel(String stillingstittel) {
        this.stillingstittel.set(stillingstittel);
    }

    public void setStillingstype(String stillingstype) {
        this.stillingstype.set(stillingstype);
    }

    public void setStatus(String status) {
        this.status.set(status);
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
