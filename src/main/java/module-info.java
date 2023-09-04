module ec.edu.espol.ventattv {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.ventattv to javafx.fxml;
    exports ec.edu.espol.ventattv;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
}
