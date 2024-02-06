module com.example.peselverifier {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.peselverifier to javafx.fxml;
    exports com.example.peselverifier;
}