package filbehandling;

/* Abstrakt klasse som representerer lagring til fil
   - Anbefales å bruke JavaFX sin FileChooser for å la bruker velge fil.

  De ulike eksterne feilene som kan oppstå ved lagring av data til fil må korrekt håndteres:
  - betyr at avvik skal kastes fra klassen som lagrer dataene til fil
  - slike avvik skal deretter fanges i controller-klassen eller tilsvarende, som er koblet opp mot bgs.
  - hvis feil oppstår skal controller klassen påse at feilinformasjonen blir på en naturlig måte fremstilt til bruker */

import klasser.Person;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

abstract class LagreTilFil {


    public abstract void skrivPersonTilFil(List<Person> personer, String path) throws IOException;

}



/*
FileChooser fileChooser = new FileChooser();
 fileChooser.setTitle("Open Resource File");
 fileChooser.getExtensionFilters().addAll(
         new ExtensionFilter("Text Files", "*.txt"),
         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
         new ExtensionFilter("All Files", "*.*"));
 File selectedFile = fileChooser.showOpenDialog(mainStage);
 if (selectedFile != null) {
    mainStage.display(selectedFile);
 }
-----------------------------------------------------

public File showOpenDialog(Window ownerWindow)

Shows a new file open dialog. The method doesn't return until the displayed open dialog is dismissed.
The return value specifies the file chosen by the user or null if no selection has been made.
If the owner window for the file dialog is set, input to all windows in the dialog's owner chain is blocked while
the file dialog is being shown.

Parameters:
ownerWindow - the owner window of the displayed file dialog

Returns:
the selected file or null if no file has been selected
 */