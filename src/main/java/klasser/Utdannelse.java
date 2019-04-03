package klasser;

public class Utdannelse {
    String studieretning;
    String skole;
    String start;
    String slutt;

    public Utdannelse(String studieretning, String skole, String start, String slutt) {
        this.studieretning = studieretning;
        this.skole = skole;
        this.start = start;
        this.slutt = slutt;
    }

    @Override
    public String toString(){
        String ut = studieretning+";"+skole+";"+start+";"+slutt+";";
        return ut;
    }
}
