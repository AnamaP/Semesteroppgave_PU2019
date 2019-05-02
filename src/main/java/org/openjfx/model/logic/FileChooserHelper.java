package org.openjfx.model.logic;

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
     * vi hgar brukt flag for å vise forskjell på om det er åpne eller lagre
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
     * Denne metoden kalles på av upload metoden i denne klassen som igjen kaller på formatFileChooser
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
     *  Metode som henter filformat bruker har valgt:
     *  Kaller på "getExtensionFilter" metoden som ligger i FileHandler klassen. Dersom extension(filnavnet)
     *  inneholder ".csv" vil vil denne kjøre filhåntering for Csv, ellers vil den kjøre jobj filhåndtering.
     */
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

    //TODO: Ny kommentar her...!!!
    /**
     * Denne metoden tar inn en path og leser innhold fra lokal fil, sjekker at det stemmer ift validering for fil,
     * legger elementet til i arrayet og oppdatere tabellen gjennom en metode i controlleren når den kalles på.
     * Chosenpath er filen man velger når man laste opp, mens path er dit den sendes til.
     */
    /**
     *  Denne metoden åpner dialogvinduet, leser filen som er valgt og skriver til fil dersom den
     *  den er kommer igjennom valideringen.
     *  Dersom ikke godkjent validering vil en feilmld til bruker vises.
     */
    public static void upload(String toPath){
        String chosenpath = openDialog();

        try {
            System.out.println(ReaderThreadStarter.startReader(chosenpath).length);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(chosenpath.split("\\.").length > 1) {
            FileHandler fileHandler = getExtensionFilter(chosenpath);
            Object object = fileHandler.readFromFile(splitPathIntoBaseAndExtension(chosenpath)[0]);
            boolean csvFiltype = true;
            if (splitPathIntoBaseAndExtension(chosenpath)[1].equals("jobj")) {
                csvFiltype = false;
            }

            ValidationHelper run = new ValidationHelper();
            if (run.validateFileInpt(object, toPath, csvFiltype)) {
                try {
                    fileHandler.writeToDB(object, toPath);
                } catch (FileNotFoundException e) {
                    System.err.println("Fant ikke filen du lette etter.");
                } catch (IOException e) {
                    System.err.println("Kunne ikke lese filen du lette etter. Årsak : " + e.getCause());
                }
            } else {
                AlertHelper.showError(invalidInputs);
            }
        }
    }

    /**
     * Denne metoden laster ned valgt objekt.
     * chosenpath er satt til SaveDialog, dermed vil FileChooseren vise et vindu for nedlasting og
     * skrive objektet til fil med valgt format (.csv/.jobj).
     */
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

    /**
     *  Denne metoden splitter path slik at plass 0 inneholder filnavnet og 1 inneholder filtypen.
     */
    private static String[] splitPathIntoBaseAndExtension(String pathWithExtension){
        String[] editedPath = pathWithExtension.split("\\.");
        return editedPath;
    }
}