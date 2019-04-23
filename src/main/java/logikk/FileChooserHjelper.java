package logikk;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import filbehandling.JobjFilhandterer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class FileChooserHjelper {
    final static int SAVE_FLAG = 1;
    final static int OPEN_FLAG = 2;

    //public static String fileChooser() {
    public static String saveDialog(){
        return formatFileChooser("Lagre som", SAVE_FLAG);
    }

    public static String openDialog(){
        // Denne er ikke ferdig - viser kun last opp mulighet, men utfører ingenting
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
        } else {
            selectedFile = fileChooser.showOpenDialog(chooserStage);
        }

        String chosenpath = selectedFile.toString();
        return chosenpath;
    }


    public static void lastOpp(String csvPath){
        Filhandterer filHandterer = null;
        String chosenpath = openDialog();

        String extension = Filhandterer.getExtention(chosenpath);

        if(extension.equals(".csv")){
            filHandterer = new CsvFilhandterer();
        }
        else{
            filHandterer = new JobjFilhandterer();
        }
        try {
            filHandterer.skrivTilFil(chosenpath, csvPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lastNed(Object object) {
        Filhandterer filHandterer;
        String chosenpath = saveDialog();

        // Metode som sjekker hvilket filformat bruker har valgt, henter ut riktig fil med innhold
        // TODO : Hva med jobj?
        String extension = Filhandterer.getExtention(chosenpath);

        if(extension.equals(".csv")) {
            filHandterer = new CsvFilhandterer();
            try {
                filHandterer.skrivTilFil(object, chosenpath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
