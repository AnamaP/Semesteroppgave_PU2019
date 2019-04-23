package logikk;

import java.util.ArrayList;

public class OversiktHjelper {
    public static ArrayList<String> stringToList(final String input) {
        String[] elements = input.split(", ");
        ArrayList<String> result = new ArrayList<>(elements.length);
        for (String item : elements) {
            result.add(String.valueOf(item));
        }
        return result;
    }
}
