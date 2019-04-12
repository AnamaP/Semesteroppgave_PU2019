package logikk;

import javafx.beans.property.SimpleStringProperty;

public class TabellSokere {

    private final SimpleStringProperty navn;
    private final SimpleStringProperty epost;
    private final SimpleStringProperty utdanning;
    private final SimpleStringProperty kategorier;

    public TabellSokere(String navn, String epost, String utdanning, String kategorier) {
        this.navn = new SimpleStringProperty(navn);
        this.epost = new SimpleStringProperty(epost);
        this.utdanning = new SimpleStringProperty(utdanning);
        this.kategorier = new SimpleStringProperty(kategorier);
    }

    public String getNavn() {
        return navn.get();
    }

    public SimpleStringProperty navnProperty(){
        return navn;
    }

    public void setNavn(String navn) {
        this.navn.set(navn);
    }

    public String getEpost() {
        return epost.get();
    }

    public SimpleStringProperty epostProperty(){
        return epost;
    }

    public void setEpost(String epost) {
        this.epost.set(epost);
    }

    public String getUtdanning() {
        return utdanning.get();
    }

    public SimpleStringProperty utdanningProperty(){
        return utdanning;
    }

    public void setUtdanning(String utdanning) {
        this.utdanning.set(utdanning);
    }

    public String getKategorier() {
        return kategorier.get();
    }

    public SimpleStringProperty kategorierProperty(){
        return kategorier;
    }

    public void setKategorier(String kategorier) {
        this.kategorier.set(kategorier);
    }
}
