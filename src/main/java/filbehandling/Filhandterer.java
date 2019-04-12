package filbehandling;

import java.io.IOException;

public abstract class Filhandterer {
    public abstract Object henteFraFil(String path);
    public abstract void skrivTilFil(Object person, String path) throws IOException;
    public abstract void lagreFilLokalt(String toPath, String fromPath) throws IOException;

    public static String getExtention(String chosenpath) {
        return chosenpath.substring(chosenpath.lastIndexOf("."),chosenpath.length());
    }
}
