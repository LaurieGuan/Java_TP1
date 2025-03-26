package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Book;
import ca.tierslieux.iou.lib.logic.items.Game;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.Tool;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TwoPane {

    public static HBox GeneratePanel(Item[] items, Item[] restoreItems) {
        TabPane listView = TabListView.getTabListView(items, restoreItems);
        Separator separator = new Separator();
        VBox itemView = new VBox();

        Inventory inv = Inventory.getInstance();

        Item item = inv.getItem(0);

        if (item instanceof Game) {
            Game game = (Game)item;
            GameView.modify(game, itemView);
        } else if (item instanceof Book) {
            Book book = (Book)item;
            BookView.modify(book, itemView);
        } else if (item instanceof Tool) {
        Tool tool = (Tool)item;
        ToolView.modify(tool, itemView);
    }

        HBox twoPane = new HBox(listView, separator, itemView);
        listView.prefWidthProperty().bind(twoPane.widthProperty().multiply(0.65));
        HBox.setHgrow(itemView, Priority.ALWAYS);

        return twoPane;
    }
}
