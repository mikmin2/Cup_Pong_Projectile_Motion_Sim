module org.mikmin.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.mikmin.Cup_Pong to javafx.fxml;
    exports org.mikmin.Cup_Pong;
}