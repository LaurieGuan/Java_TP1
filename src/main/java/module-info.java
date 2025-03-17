module ca.tierslieux.iou {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens ca.tierslieux.iou to javafx.fxml;
    exports ca.tierslieux.iou;
    exports ca.tierslieux.iou.lib.logic.exception;
    opens ca.tierslieux.iou.lib.logic.exception to javafx.fxml;
    exports ca.tierslieux.iou.lib;
    opens ca.tierslieux.iou.lib to javafx.fxml;
    exports ca.tierslieux.iou.lib.logic;
    opens ca.tierslieux.iou.lib.logic to javafx.fxml;
    exports ca.tierslieux.iou.lib.logic.items;
    opens ca.tierslieux.iou.lib.logic.items to javafx.fxml;
}