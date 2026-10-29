module org.example.projectilemotion {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projectilemotion to javafx.fxml;
    exports org.example.projectilemotion;
}