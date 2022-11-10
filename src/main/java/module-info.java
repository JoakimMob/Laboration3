module com.example.laboration3joakimmoberg {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.laboration3joakimmoberg to javafx.fxml;
    exports com.example.laboration3joakimmoberg;
    exports com.example.laboration3joakimmoberg.controller;
    opens com.example.laboration3joakimmoberg.controller to javafx.fxml;
}