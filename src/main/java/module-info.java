module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens home to javafx.fxml;
    exports home;

    opens Controllers to javafx.fxml;
    exports Controllers;

    opens Classes to javafx.fxml;
    exports Classes;
}
