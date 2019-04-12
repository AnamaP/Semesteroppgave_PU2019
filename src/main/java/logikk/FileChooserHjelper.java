package logikk;

import javafx.stage.Stage;

import java.io.File;

public class FileChooserHjelper {

    public static String fileChooser(){
        // FileChooser
        Stage chooserStage = new Stage();
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter(".csv", "*.csv"),
                new javafx.stage.FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage); //showSaveDialog
        String chosenpath = selectedFile.toString();
        return chosenpath;
    }
}
