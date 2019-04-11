package logikk;

import javafx.beans.property.SimpleStringProperty;

public class TabellVikariater {
    private final SimpleStringProperty kontaktperson;
    private final SimpleStringProperty tlf;
    private final SimpleStringProperty sektor;
    private final SimpleStringProperty firmanavn;
    private final SimpleStringProperty bransje;
    private final SimpleStringProperty stillingstittel;
    private final SimpleStringProperty varighet;
    private final SimpleStringProperty stillingstype;
    private final SimpleStringProperty kategorier;

    public TabellVikariater(String kontaktperson, String tlf, String sektor, String firmanavn, String bransje,
                            String stillingstittel, String varighet, String stillingstype, String kategorier) {
        this.kontaktperson = new SimpleStringProperty(kontaktperson);
        this.tlf = new SimpleStringProperty(tlf);
        this.sektor = new SimpleStringProperty(sektor);
        this.firmanavn = new SimpleStringProperty(firmanavn);
        this.bransje = new SimpleStringProperty(bransje);
        this.stillingstittel = new SimpleStringProperty(stillingstittel);
        this.varighet = new SimpleStringProperty(varighet);
        this.stillingstype = new SimpleStringProperty(stillingstype);
        this.kategorier = new SimpleStringProperty(kategorier);
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

    public String getStillingstype() {
        return stillingstype.get();
    }

    public SimpleStringProperty stillingstypeProperty() {
        return stillingstype;
    }

    public void setStillingstype(String stillingstype) {
        this.stillingstype.set(stillingstype);
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
