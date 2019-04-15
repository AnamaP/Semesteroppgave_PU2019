package logikk;

import javafx.beans.property.SimpleIntegerProperty;
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


    public String getKontaktperson() {
        return kontaktperson.get();
    }

    public SimpleStringProperty kontaktpersonProperty() {
        return kontaktperson;
    }

    public void setKontaktperson(String kontaktperson) {
        this.kontaktperson.set(kontaktperson);
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

    public String getSektor() {
        return sektor.get();
    }

    public SimpleStringProperty sektorProperty() {
        return sektor;
    }

    public void setSektor(String sektor) {
        this.sektor.set(sektor);
    }

    public String getFirmanavn() {
        return firmanavn.get();
    }

    public SimpleStringProperty firmanavnProperty() {
        return firmanavn;
    }

    public void setFirmanavn(String firmanavn) {
        this.firmanavn.set(firmanavn);
    }

    public String getBransje() {
        return bransje.get();
    }

    public SimpleStringProperty bransjeProperty() {
        return bransje;
    }

    public void setBransje(String bransje) {
        this.bransje.set(bransje);
    }

    public String getStillingstittel() {
        return stillingstittel.get();
    }

    public SimpleStringProperty stillingstittelProperty() {
        return stillingstittel;
    }

    public void setStillingstittel(String stillingstittel) {
        this.stillingstittel.set(stillingstittel);
    }

    public String getVarighet() {
        return varighet.get();
    }

    public SimpleStringProperty varighetProperty() {
        return varighet;
    }

    public void setVarighet(String varighet) {
        this.varighet.set(varighet);
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
