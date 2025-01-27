package org.openjfx.model.logic;

import org.openjfx.model.exceptions.PathNotFoundException;
import org.openjfx.model.fileHandling.CsvFileHandler;
import org.openjfx.model.fileHandling.FileHandler;
import org.openjfx.model.fileHandling.JobjFileHandler;
import javafx.stage.Stage;
import org.openjfx.model.thread.ReaderThreadStarter;

import java.io.*;
import java.util.concurrent.ExecutionException;

import static org.openjfx.model.logic.ValidationHelper.invalidInputs;

public class FileChooserHelper {
    /**
     * Bruker FLAG for å vise forskjell på om det er åpne eller lagre
     */
    final static int SAVE_FLAG = 1;
    final static int OPEN_FLAG = 2;

    /**
     * Denne metoden viser Lagre vinduet
     */
    public static String saveDialog(){
        return formatFileChooser("Lagre som", SAVE_FLAG);
    }
    /**
     * Denne metoden viser Åpne vinduet
     */
    public static String openDialog(){
        return formatFileChooser("Last opp fil", OPEN_FLAG);
    }

    /**
     *  Denne metoden håndterer FileChooser vinduet som dukker opp ved nedlasting/opplasting.
     */
    private static String formatFileChooser(String title, int flag){
        Stage chooserStage = new Stage();
        File selectedFile;
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter(".csv", "*.csv"),
                new javafx.stage.FileChooser.ExtensionFilter(".jobj", "*.jobj"));

        /**
         *  Denne åpner save / open dialog avhengig av flagget
         */
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

    /**
     *  Metode som henter filformat bruker har valgt
     */
    public static FileHandler getExtensionFilter(String chosenpath) {
        FileHandler fileHandler = null;
        String out = "";
        String extension = null;
        try {
            extension = FileHandler.getExtension(chosenpath);
        }
        catch (PathNotFoundException e) {
            out = "Finner ikke filtypen!";
        }

        if(extension.equals(".csv")){
            fileHandler = new CsvFileHandler();
        }
        if(extension.equals(".jobj")){
            fileHandler = new JobjFileHandler();
        }
        if(extension.isEmpty()){
            out = "Avsluttet nedlasting.";
        }
        if(out != "") {
            AlertHelper.showError(out);
        }
        return fileHandler;
    }

    /**
     *  Denne metoden åpner dialogvinduet, leser filen som er valgt og legger den til i listen, for så å oppdatere DB.
     *  Dersom det ikke er godkjent validering vil en feilmld til bruker vises.
     *  Prøvde å implementere bruk av Thread her, men denne delen av programmet ble vi ikke helt ferdig med.
     */
    public static void upload(String toPath){
        String fromPath = openDialog();

        // Tråd:
        try {
            System.out.println(ReaderThreadStarter.startReader(fromPath).length);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(fromPath.split("\\.").length > 1) {
            FileHandler fileHandler = getExtensionFilter(fromPath);
            Object object = fileHandler.readFromFile(splitPathIntoBaseAndExtension(fromPath)[0]);

            // Henter ut filtype
            boolean isCsvFiltype = true;
            if (splitPathIntoBaseAndExtension(fromPath)[1].equals("jobj")) {
                isCsvFiltype = false;
            }

            ValidationHelper run = new ValidationHelper();
            if (run.validateFileInpt(object, toPath, isCsvFiltype)) {
                MainAppHelper runer = new MainAppHelper();
                runer.reloadJobseekersDB();
                runer.reloadTempJobsDB();
            }
            else {
                AlertHelper.showError(invalidInputs);
            }
        }
    }

    /**
     * Denne metoden viser vindu for nedlasting og skriver objektet til fil med valgt format (.csv/.jobj).
     */
    public static void download(Object object) {
        String chosenSavePath = saveDialog();
        FileHandler fileType = getExtensionFilter(chosenSavePath);

        try {
            fileType.writeToFile(object, chosenSavePath);
        }
        catch (IOException e) {
            AlertHelper.showError("Fikk ikke til nedlastningen. Feilmelding :" + e.getCause());
        }
    }

    /**
     *  Denne metoden splitter path slik at plass 0 inneholder filnavnet og 1 inneholder filtypen.
     */
    private static String[] splitPathIntoBaseAndExtension(String pathWithExtension){
        String[] editedPath = pathWithExtension.split("\\.");
        return editedPath;
    }
}