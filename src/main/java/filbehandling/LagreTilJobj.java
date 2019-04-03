package filbehandling;

/* Lagril tl .jobj fil med Javas støtte for serialisering.
   - I denne filtypen lagres data i et binært dataformat somm er kompatibelt med Java sine serialiseringsklasser.
   - */


import klasser.Jobbsoker;

import java.io.IOException;
import java.util.List;

public class LagreTilJobj extends LagreTilFil {

    @Override
    public void skrivPersonTilFil(Jobbsoker person, String path) throws IOException {

    }
}


