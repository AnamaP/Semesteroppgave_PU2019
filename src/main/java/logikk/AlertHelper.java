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
        error.setHeaderText("Følgende feil er registrert, vennligst se over:");
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

}
