module ca.tierslieux.iou {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ca.tierslieux.iou to javafx.fxml;
    exports ca.tierslieux.iou;

    opens ca.tierslieux.iou.lib.logic.items to javafx.fxml;
    exports ca.tierslieux.iou.lib.logic.items;
}