package logikk;

import javafx.scene.control.Alert;

public class AlertHelper {
    private String message;

    public AlertHelper(String message){
        this.message = message;
    }

    // Alert metode for feilmeldinger
    public static void showError(String message) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("Feilmelding");
        error.setHeaderText("Obs!");
        error.setContentText(message);
        error.showAndWait();
    }

    // Alert metode for vellykket registrering
    public static void showConfirmation(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Bekreftelse");
        confirm.setContentText("Registereringen var vellykket!");
        confirm.showAndWait();
    }

    // Alert metode for visning av mer info i "OversiktVikariater"
    public static void showMoreInfo(String title, String message){
        Alert moreInfo = new Alert(Alert.AlertType.INFORMATION);
        moreInfo.setTitle("Informasjon om vikariatet: ");
        moreInfo.setHeaderText(title);
        moreInfo.setContentText(message);
        moreInfo.showAndWait();
    }

}
