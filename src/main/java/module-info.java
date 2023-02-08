module com.example.w23comp1008lhw5memorygame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w23comp1008lhw5memorygame to javafx.fxml;
    exports com.example.w23comp1008lhw5memorygame;
}