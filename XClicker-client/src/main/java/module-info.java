module XClickerClient.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;

    opens ru.projectx.clicker to javafx.fxml;
    exports ru.projectx.clicker;
}