package logikk;

import javafx.beans.property.SimpleStringProperty;
import klasser.Jobbsoker;

public class TabellSokere {

    private final SimpleStringProperty fornavn; //0
    private final SimpleStringProperty etternavn;// 1
    private final SimpleStringProperty adresse;//2
    private final SimpleStringProperty postnr;//3
    private final SimpleStringProperty poststed;//4
    private final SimpleStringProperty tlf;//5
    private final SimpleStringProperty epost;//6
    private final SimpleStringProperty alder;//7
    private final SimpleStringProperty lonnskrav;//8
    private final SimpleStringProperty utdanning;//9
    private final SimpleStringProperty studieretning;//10
    private final SimpleStringProperty erfaring; //11
    private final SimpleStringProperty referanse; //12
    private final SimpleStringProperty kategorier;//13, 14, 15
    private final SimpleStringProperty status; //16

    public TabellSokere(Jobbsoker jobbsoker) {
        this.fornavn = new SimpleStringProperty(jobbsoker.getFornavn());
        this.etternavn = new SimpleStringProperty(jobbsoker.getEtternavn());
        this.adresse = new SimpleStringProperty(jobbsoker.getAdresse());
        this.postnr = new SimpleStringProperty(jobbsoker.getPostnr());
        this.poststed = new SimpleStringProperty(jobbsoker.getPoststed());
        this.tlf = new SimpleStringProperty(jobbsoker.getTlf());
        this.epost = new SimpleStringProperty(jobbsoker.getEpost());
        this.alder = new SimpleStringProperty(jobbsoker.getAlder());
        this.lonnskrav = new SimpleStringProperty(jobbsoker.getLonnskrav());
        this.utdanning = new SimpleStringProperty(jobbsoker.getCv().getUtdanning());
        this.studieretning = new SimpleStringProperty(jobbsoker.getCv().getStudieretning());
        this.erfaring = new SimpleStringProperty(jobbsoker.getCv().getErfaring());
        this.referanse = new SimpleStringProperty(jobbsoker.getCv().getReferanse());
        this.kategorier = new SimpleStringProperty(jobbsoker.getCv().kategorierToString());
        this.status = new SimpleStringProperty(jobbsoker.getStatus());
    }

    public String getFornavn() {
        return fornavn.get();
    }

    public void setFornavn(String fornavn) {
        this.fornavn.set(fornavn);
    }

    public String getEtternavn() {
        return etternavn.get();
    }

    public void setEtternavn(String etternavn) {
        this.etternavn.set(etternavn);
    }

    public String getAdresse() {
        return adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public String getPostnr() {
        return postnr.get();
    }

    public void setPostnr(String postnr) {
        this.postnr.set(postnr);
    }

    public String getPoststed() {
        return poststed.get();
    }

    public void setPoststed(String poststed) {
        this.poststed.set(poststed);
    }

    public String getTlf() {
        return tlf.get();
    }

    public void setTlf(String tlf) {
        this.tlf.set(tlf);
    }

    public String getEpost() {
        return epost.get();
    }

    public void setEpost(String epost) {
        this.epost.set(epost);
    }

    public String getAlder() {
        return alder.get();
    }

    public void setAlder(String alder) {
        this.alder.set(alder);
    }

    public String getLonnskrav() {
        return lonnskrav.get();
    }

    public void setLonnskrav(String lonnskrav) {
        this.lonnskrav.set(lonnskrav);
    }

    public String getUtdanning() {
        return utdanning.get();
    }

    public void setUtdanning(String utdanning) {
        this.utdanning.set(utdanning);
    }

    public String getStudieretning() {
        return studieretning.get();
    }

    public void setStudieretning(String studieretning) {
        this.studieretning.set(studieretning);
    }

    public String getErfaring() {
        return erfaring.get();
    }

    public void setErfaring(String erfaring) {
        this.erfaring.set(erfaring);
    }

    public String getReferanse() {
        return referanse.get();
    }

    public void setReferanse(String referanse) {
        this.referanse.set(referanse);
    }

    public String getKategorier() {
        return kategorier.get();
    }

    public void setKategorier(String kategorier) {
        this.kategorier.set(kategorier);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public SimpleStringProperty fornavnProperty() {
        return fornavn;
    }
    public SimpleStringProperty etternavnProperty() {
        return etternavn;
    }
    public SimpleStringProperty adresseProperty() {
        return adresse;
    }
    public SimpleStringProperty postnrProperty() {
        return postnr;
    }
    public SimpleStringProperty poststedProperty() {
        return poststed;
    }
    public SimpleStringProperty tlfProperty() {
        return tlf;
    }
    public SimpleStringProperty epostProperty() {
        return epost;
    }
    public SimpleStringProperty alderProperty() {
        return alder;
    }
    public SimpleStringProperty lonnskravProperty() {
        return lonnskrav;
    }
    public SimpleStringProperty utdanningProperty() {
        return utdanning;
    }
    public SimpleStringProperty studieretningProperty() {
        return studieretning;
    }
    public SimpleStringProperty erfaringProperty() {
        return erfaring;
    }
    public SimpleStringProperty referanseProperty() {
        return referanse;
    }
    public SimpleStringProperty kategorierProperty() {
        return kategorier;
    }
    public SimpleStringProperty statusProperty(){return status;}
}