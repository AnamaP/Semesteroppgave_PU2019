package logikk;

import filbehandling.CsvFilhandterer;
import filbehandling.Filhandterer;
import filbehandling.JobjFilhandterer;
import javafx.stage.Stage;

import java.io.*;

public class FileChooserHjelper {
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
    public static Filhandterer getExtensionFilter(String chosenpath){
        Filhandterer filhandterer;
        String extension = Filhandterer.getExtension(chosenpath);

        if(extension.equals(".csv")){
            filhandterer = new CsvFilhandterer();
        }
        else{
            filhandterer = new JobjFilhandterer();
        }
        return filhandterer;

    }

    // Skal lese innhold fra lokal fil, sjekke at det stemmer, legge elementet til i arrayet og oppdatere tabellen
    // chosenpath er filen man velger til å laste opp, mens path er dit den skal
    public static void lastOpp(String path){
        String chosenpath = openDialog();

        Filhandterer filhandterer = getExtensionFilter(chosenpath);
        Object object = filhandterer.henteFraFil(getPathBase(chosenpath));

        try {
            filhandterer.skrivTilDB(object, path);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void lastNed(Object object) {
        String chosenpath = saveDialog();
        Filhandterer filhandterer = getExtensionFilter(chosenpath);

        try {
            System.out.println(object.toString());
            filhandterer.skrivTilFil(object, chosenpath);
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