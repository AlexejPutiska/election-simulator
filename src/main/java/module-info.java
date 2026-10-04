module org.example.electionsimprojekt {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.electionsimprojekt to javafx.fxml;
    exports view;
    exports controller;
}