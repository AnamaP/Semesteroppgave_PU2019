module hellofx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.controllers to javafx.fxml;
    exports org.openjfx;
}