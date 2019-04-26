package filbehandling;

import java.io.IOException;

public abstract class Filhandterer {
    public abstract Object henteFraFil(String path);
    public abstract void skrivTilFil(Object person, String path) throws IOException;
    public abstract void skrivTilDB(Object object, String path) throws IOException;

    public static String getExtension(String chosenpath) {
        return chosenpath.substring(chosenpath.lastIndexOf("."));
    }
}
