package klasser;

public class Jobbsoker extends Person {
    private Cv cv;
    private String lonnskrav;
    private String status;


    public Jobbsoker(String fornavn, String etternavn, String adresse, String postnr, String poststed, String tlf,
                     String epost, String alder, Cv cv, String status) {
        super(fornavn, etternavn, adresse, postnr, poststed, tlf, epost, alder);
        this.cv = cv;
        this.lonnskrav = "";
        this.status = status;
    }

    public void setLonnskrav(String lonnskrav){
        this.lonnskrav = lonnskrav;
    }

    public Cv getCv() {
        return cv;
    }

    public String getLonnskrav() {
        return lonnskrav;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        String ut = super.toString() +";"+lonnskrav+";"+cv.toString()+";"+status;
        return ut;
    }
}
