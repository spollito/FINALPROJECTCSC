module com.example.elevator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.elevator to javafx.fxml;
    exports com.example.elevator;
}