module ca.tierslieux.iou {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.tierslieux.iou to javafx.fxml;
    exports ca.tierslieux.iou;
}