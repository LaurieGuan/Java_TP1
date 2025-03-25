package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Game;
import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView {

    public static VBox view(Item item) {
        VBox generalSection = ItemCommonView.view(item);

        return new VBox(generalSection);
    }

}
