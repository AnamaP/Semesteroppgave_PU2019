package org.openjfx.model.logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class NavigationHelper {

    /**
     * ChangePage får inn en path som sier hvilken FXML-side den skal ta deg til. Den oppretter så en
     * ny "scene" eller "vindu" bassert på denne FXML-siden.
     */
    public static void changePage(String path, ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            URL url = NavigationHelper.class.getResource(path);
            loader.setLocation(url);

            Parent parent = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.centerOnScreen();
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
