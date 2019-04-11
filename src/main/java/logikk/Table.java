package logikk;

import javafx.beans.property.SimpleStringProperty;

public class Table {

    private final SimpleStringProperty rNavn;
    private final SimpleStringProperty rEpost;
    private final SimpleStringProperty rUtdanning;
    private final SimpleStringProperty rKategorier;

    public Table(String rNavn, String rEpost, String rUtdanning, String rKategorier) {
        this.rNavn = new SimpleStringProperty(rNavn);
        this.rEpost = new SimpleStringProperty(rEpost);
        this.rUtdanning = new SimpleStringProperty(rUtdanning);
        this.rKategorier = new SimpleStringProperty(rKategorier);
    }

    public String getrNavn() {
        return rNavn.get();
    }

    public SimpleStringProperty rNavnProperty(){
        return rNavn;
    }

    public void setrNavn(String rNavn) {
        this.rNavn.set(rNavn);
    }

    public String getrEpost() {
        return rEpost.get();
    }

    public SimpleStringProperty rEpostProperty(){
        return rEpost;
    }

    public void setrEpost(String rEpost) {
        this.rEpost.set(rEpost);
    }

    public String getrUtdanning() {
        return rUtdanning.get();
    }

    public SimpleStringProperty rUtdanningProperty(){
        return rUtdanning;
    }

    public void setrUtdanning(String rUtdanning) {
        this.rUtdanning.set(rUtdanning);
    }

    public String getrKategorier() {
        return rKategorier.get();
    }

    public SimpleStringProperty rKategorierProperty(){
        return rKategorier;
    }

    public void setrKategorier(String rKategorier) {
        this.rKategorier.set(rKategorier);
    }
}
