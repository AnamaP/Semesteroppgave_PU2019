package filbehandling;

/* Lagril tl .jobj fil med Javas støtte for serialisering.
   - I denne filtypen lagres data i et binært dataformat somm er kompatibelt med Java sine serialiseringsklasser.
   - */

import klasser.Person;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class LagreTilJobj extends LagreTilFil {

    @Override
    public void skrivPersonTilFil(List<Person> personer, String path) throws IOException {

    }
}


