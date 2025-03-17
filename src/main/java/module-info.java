module ca.tierslieux.iou {
    requires javafx.controls;
    requires javafx.fxml;


    opens ca.tierslieux.iou to javafx.fxml;
    exports ca.tierslieux.iou;
    exports ca.tierslieux.iou.lib.exception;
    opens ca.tierslieux.iou.lib.exception to javafx.fxml;
    exports ca.tierslieux.iou.lib;
    opens ca.tierslieux.iou.lib to javafx.fxml;
}