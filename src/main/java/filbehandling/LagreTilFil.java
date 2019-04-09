package filbehandling;

/* Abstrakt klasse som representerer lagring til fil
   - Anbefales å bruke JavaFX sin FileChooser for å la bruker velge fil.

  De ulike eksterne feilene som kan oppstå ved lagring av data til fil må korrekt håndteres:
  - betyr at avvik skal kastes fra klassen som lagrer dataene til fil
  - slike avvik skal deretter fanges i controller-klassen eller tilsvarende, som er koblet opp mot bgs.
  - hvis feil oppstår skal controller klassen påse at feilinformasjonen blir på en naturlig måte fremstilt til bruker */


import java.io.IOException;

public abstract class LagreTilFil {


    public abstract void skrivPersonTilFil(String person, String path) throws IOException;

    public abstract void skrivVikariatTilFil(String nyUtlysning, String path) throws IOException;

}