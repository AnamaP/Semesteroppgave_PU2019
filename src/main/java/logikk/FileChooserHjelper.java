package logikk;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.openjfx.OversiktVikariaterController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.openjfx.OversiktVikariaterController.stringToList;

public class FileChooserHjelper {

    //public static String fileChooser() {
    public static String saveDialog(){
        // FileChooser
        Stage chooserStage = new Stage();
        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Lagre som");
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter(".csv", "*.csv"),
                new javafx.stage.FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File selectedFile = fileChooser.showSaveDialog(chooserStage);
        String chosenpath = selectedFile.toString();
        return chosenpath;
    }

    public static String openDialog(){
        // Denne er ikke ferdig - viser kun last opp mulighet, men utfører ingenting
        Stage chooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Last opp fil");
        fileChooser.getExtensionFilters().addAll(
                new javafx.stage.FileChooser.ExtensionFilter(".csv", "*.csv"),
                new javafx.stage.FileChooser.ExtensionFilter(".jobj", "*.jobj"));
        File openFile = fileChooser.showOpenDialog(chooserStage);

        String chosenpath = openFile.toString();
        return chosenpath;
    }


    public static void lastOpp(String csvPath){
        Filhandterer filHandterer = null;
        String chosenpath = openDialog();

        String extension = Filhandterer.getExtention(chosenpath);

        if(extension.equals(".csv")){
            filHandterer = new CsvFilhandterer();
        }
        try {
            filHandterer.skrivTilFil(chosenpath, csvPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lastNed(String csvPath) {
        Filhandterer filHandterer;
        String chosenpath = saveDialog();

        // Metode som sjekker hvilket filformat bruker har valgt, henter ut riktig fil med innhold
        // TODO : Hva med jobj?
        String extension = Filhandterer.getExtention(chosenpath);

        if(extension.equals(".csv")) {
            filHandterer = new CsvFilhandterer();
            try {
                filHandterer.lagreFilLokalt(chosenpath, csvPath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
