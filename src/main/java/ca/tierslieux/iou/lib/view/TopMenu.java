package ca.tierslieux.iou.lib.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;

public class TopMenu {
    public static HBox Generate() {
        Button file = new Button("Fichier");
        file.setId("file");
        HBox hBox = new HBox(file);
        return hBox;
    }
}
