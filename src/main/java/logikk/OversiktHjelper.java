package logikk;

import klasser.Cv;
import klasser.Jobbsoker;

import java.util.ArrayList;

public class OversiktHjelper {

    public static int chosenRow;
    public static ArrayList<String> valgteKategorier;

    public void setValgteKategorier(ArrayList<String> valgteKategorier) {
        this.valgteKategorier = valgteKategorier;
    }

    public static ArrayList<String> stringToList(final String input) {
        String[] elements = input.split(", ");
        ArrayList<String> result = new ArrayList<>(elements.length);
        for (String item : elements) {
            result.add(String.valueOf(item));
        }
        return result;
    }

    public void findRow(ArrayList arrayList, String key){
        for(int i = 0; i < arrayList.size(); i++){
            String [] row = arrayList.get(i).toString().split(";");
            for(int j = 0; j < row.length; j++){
                if(row[j].equals(key)){
                    chosenRow = i;
                }
            }
        }
    }

    public int sjekkKategorier(ArrayList arrayList){
        int antall = 0;
        for(int i = 0; i < valgteKategorier.size(); i++) {
            if (arrayList.toString().contains(valgteKategorier.get(i))) {
                antall++;
            }
        }
        return antall;
    }

    public Jobbsoker hentSokerFraListe(String [] kolonner){
        ArrayList<String> kategorier = hentKategorier(kolonner, 13);
        Cv cv = new Cv(kolonner[9], kolonner[10], kolonner[11], kategorier);

        Jobbsoker jobbsoker = new Jobbsoker(kolonner[0], kolonner[1], kolonner[2], kolonner[3], kolonner[4],
                kolonner[5], kolonner[6], kolonner[7], cv, kolonner[kolonner.length-1]);

        return jobbsoker;
    }

    public ArrayList hentKategorier(String [] kolonner, int start){
        ArrayList<String> kategorier = new ArrayList<>();
        for (int i = start; i < kolonner.length -1; i++) {
            kategorier.add(kolonner[i]);
        }
        return kategorier;
    }
}
