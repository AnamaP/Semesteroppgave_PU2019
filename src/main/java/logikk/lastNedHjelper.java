package logikk;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class lastNedHjelper {

    public static String fileChooser(){
        // FileChooser
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage); //showSaveDialog
        String chosenpath = selectedFile.toString();
        return chosenpath;
    }
}
