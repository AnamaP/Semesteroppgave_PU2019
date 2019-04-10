package filbehandling;

import java.io.IOException;

public abstract class FilHandterer {
    public abstract String henteFraFil(String path);
    public abstract void skrivTilFil(String person, String path) throws IOException;
    public abstract void lagreFilLokalt(String toPath, String fromPath) throws IOException;

    public static String getExtention(String chosenpath) {
        return chosenpath.substring(chosenpath.lastIndexOf("."),chosenpath.length());
    }
}
