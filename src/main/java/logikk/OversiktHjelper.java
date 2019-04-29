package logikk;

import java.util.ArrayList;

public class OversiktHjelper {


    public static int chosenRow;

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
}
