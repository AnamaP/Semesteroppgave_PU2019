package logic;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

import java.util.Optional;

public class AlertHelper {
    private String message;

    public AlertHelper(String message){
        this.message = message;
    }

    /**
     * Alert metode for feilmeldinger
     */
    public static void showError(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        error.setTitle("Feilmelding");
        error.setHeaderText("Obs!");
        error.setContentText(message);
        error.showAndWait();
    }

    /**
     Alert metode for vellykket registrering
      */
    public static void showConfirmation(){
        Alert confirm = new Alert(Alert.AlertType.INFORMATION);
        confirm.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        confirm.setTitle("Bekreftelse");
        confirm.setContentText("Registereringen var vellykket!");
        confirm.showAndWait();
    }

    /**
     * Alert metode for visning av mer info i "OversiktVikariater"
     */
    public static void showMoreInfo(String title, String message){
        Alert moreInfo = new Alert(Alert.AlertType.INFORMATION);
        moreInfo.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        moreInfo.setTitle("Informasjon om vikariatet: ");
        moreInfo.setHeaderText(title);
        moreInfo.setContentText(message);
        moreInfo.showAndWait();
    }

    /**
     * Alert metode som spør om bruker virkelig vil utføre slettingen
     */
    public static Optional<ButtonType> showDeleteAlert(String message){
        Alert question = new Alert(Alert.AlertType.CONFIRMATION);
        question.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        question.setHeaderText("Er du sikker på at du vil slette : ");
        question.setContentText(message + "?");
        Optional<ButtonType> result = question.showAndWait();
        return result;
    }

}
