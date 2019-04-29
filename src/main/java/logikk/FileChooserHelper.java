package logikk;

import filbehandling.CsvFileHandler;
import filbehandling.FileHandler;
import filbehandling.JobjFileHandler;
import javafx.stage.Stage;

import java.io.*;

public class FileChooserHelper {
    final static int SAVE_FLAG = 1;
    final static int OPEN_FLAG = 2;


    public static String saveDialog(){
        return formatFileChooser("Lagre som", SAVE_FLAG);
    }

    public static String openDialog(){
        return formatFileChooser("Last opp fil", OPEN_FLAG);
    }

    private static String formatFileChooser(String title, int flag){
        Stage chooserStage = new Stage();
        File selectedFile;
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter(".csv", "*.csv"),
                new javafx.stage.FileChooser.ExtensionFilter(".jobj", "*.jobj"));

        if(flag == SAVE_FLAG) {
             selectedFile = fileChooser.showSaveDialog(chooserStage);
        }
        else {
            selectedFile = fileChooser.showOpenDialog(chooserStage);
        }

        String chosenpath = selectedFile.toString();
        return chosenpath;
    }

    // Metode som sjekker hvilket filformat bruker har valgt
    public static FileHandler getExtensionFilter(String chosenpath){
        FileHandler fileHandler;
        String extension = FileHandler.getExtension(chosenpath);

        if(extension.equals(".csv")){
            fileHandler = new CsvFileHandler();
        }
        else{
            fileHandler = new JobjFileHandler();
        }
        return fileHandler;

    }

    // Skal lese innhold fra lokal fil, sjekke at det stemmer, legge elementet til i arrayet og oppdatere tabellen
    // chosenpath er filen man velger til å laste opp, mens path er dit den skal
    public static void upload(String path){
        String chosenpath = openDialog();

        FileHandler fileHandler = getExtensionFilter(chosenpath);
        Object object = fileHandler.readFromFile(getPathBase(chosenpath));

        try {
            fileHandler.writeToDB(object, path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(Object object) {
        String chosenpath = saveDialog();
        FileHandler fileHandler = getExtensionFilter(chosenpath);

        try {
            fileHandler.writeToFile(object, chosenpath);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metode som endrer/tar bort selve notasjonen på filformatet
    private static String getPathBase(String pathWithExtension){
        String[] editedPath = pathWithExtension.split("\\.");
        return editedPath[0];
    }
}