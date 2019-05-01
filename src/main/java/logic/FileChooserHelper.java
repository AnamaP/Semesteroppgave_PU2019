package logic;

import fileHandling.CsvFileHandler;
import fileHandling.FileHandler;
import fileHandling.JobjFileHandler;
import javafx.stage.Stage;

import java.io.*;

import static logic.ValidationHelper.invalidInputs;

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
        String chosenpath;
        try {
            chosenpath = selectedFile.toString();
        }
        catch (NullPointerException e){
            chosenpath = "";
        }
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
        Object object = fileHandler.readFromFile(getPathBase(chosenpath)[0]);
        boolean csvFiltype = true;
        if(getPathBase(chosenpath)[1].equals("jobj")){
            csvFiltype = false;
        }

        ValidationHelper run = new ValidationHelper();
        if (run.validateFileInpt(object, path, csvFiltype)) {
            try {
                fileHandler.writeToDB(object, path);
            } catch (FileNotFoundException e) {
                System.err.println("Fant ikke filen du lette etter.");
            } catch (IOException e) {
                System.err.println("Kunne ikke lese filen du lette etter. Årsak : " + e.getCause());
            }
        }
        else {
            AlertHelper.showError(invalidInputs);
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
    private static String[] getPathBase(String pathWithExtension){
        String[] editedPath = pathWithExtension.split("\\.");
        return editedPath;
    }
}