module hellofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.scripting.nashorn;

    opens org.openjfx.controllers to javafx.fxml;
    exports org.openjfx;
}