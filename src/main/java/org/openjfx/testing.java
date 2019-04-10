package org.openjfx;

import filbehandling.CsvFilhandterer;
import filbehandling.FilHandterer;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class testing extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".csv", "*.csv"),
                new FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        stage.show();

        File selectedFile = fileChooser.showSaveDialog(chooserStage);

        String path = selectedFile.toString();
        FilHandterer filHandterer = new CsvFilhandterer();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
