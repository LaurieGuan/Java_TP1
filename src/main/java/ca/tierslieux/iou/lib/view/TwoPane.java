package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.*;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TwoPane {
    private static VBox itemView;

    public static void GeneratePanel(HBox panel, Item[] items, Item[] restoreItems) {
        TabPane listView = TabListView.getTabListView(items, restoreItems);
        Separator separator = new Separator();
        itemView = new VBox();

        panel.getChildren().addAll(listView, separator, itemView);
        listView.prefWidthProperty().bind(panel.widthProperty().multiply(0.65));
        HBox.setHgrow(itemView, Priority.ALWAYS);
    }

    public static void modifyItem(Item item) {
        itemView.getChildren().removeAll(itemView.getChildren());
        switch (item.getType()) {
            case BOOK:
                BookView.modify((Book) item, itemView);
                break;
            case GAME:
                GameView.modify((Game) item, itemView);
                break;
            case TOOL:
                ToolView.modify((Tool) item, itemView);
                break;
        }

        Inventory.getInstance().isBeingModified = true;
    }

    public static void showItem(Item item) {

        itemView.getChildren().removeAll(itemView.getChildren());

        if (item instanceof Game) {
            Game game = (Game)item;
            GameView.view(game, itemView);
        } else if (item instanceof Book) {
            Book book = (Book)item;
            BookView.view(book, itemView);
        } else if (item instanceof Tool) {
            Tool tool = (Tool) item;
            ToolView.view(tool, itemView);
        }
    }

    public static void addItem() {
        if (! Inventory.getInstance().isBeingModified) {
            Label typeLabel = new Label("Type d'objet:");
            ComboBox<String> typeComboBox = new ComboBox<>();
            typeComboBox.getItems().addAll(
                    Type.getTypeString(Type.GAME),
                    Type.getTypeString(Type.BOOK),
                    Type.getTypeString(Type.TOOL)
            );
            typeComboBox.setValue("Jeu");
            HBox stateBox = new HBox(typeLabel, typeComboBox);
            stateBox.setPrefWidth(200);

            typeComboBox.setOnAction(actionEvent -> {
                if (typeComboBox.getValue() == "Jeu") {
                    GameView.add(itemView);
                } else if (typeComboBox.getValue() == "Livre") {
                    BookView.add(itemView);
                } else if (typeComboBox.getValue() == "Outil") {
                    ToolView.add(itemView);
                }
                Inventory.getInstance().isBeingModified = true;
                typeComboBox.setDisable(true);
            });

            itemView.getChildren().removeAll(itemView.getChildren());
            itemView.getChildren().addAll(typeLabel, typeComboBox);
        }
    }
}
