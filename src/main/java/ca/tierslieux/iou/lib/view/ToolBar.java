package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.State;
import ca.tierslieux.iou.lib.logic.items.Type;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import ca.tierslieux.iou.lib.logic.list.ItemList;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class ToolBar {
    private static Button addItem;
    private static Button removeItem;
    private static Button restoreItem;
   private static ToggleButton gameFilter;
   private static ToggleButton bookFilter;
   private static ToggleButton toolFilter;
   private static ObjectProperty<EventHandler<ActionEvent>> buttonActionCallback;


    public static HBox Generate() {
        addItem = new Button("Ajouter objet");
        addItem.setId("addButton");
        removeItem = new Button("Retirer objet");
        removeItem.setId("removeButton");
        restoreItem = new Button("Restaurer objet");
        restoreItem.setId("restoreButton");
        gameFilter = new ToggleButton("Afficher jeux");
        gameFilter.setId("gameFilter");
        bookFilter = new ToggleButton("Afficher livres");
        bookFilter.setId("bookFilter");
        toolFilter = new ToggleButton("Afficher outils");
        toolFilter.setId("toolFilter");
        Separator addFilterSeperator = new Separator();
        Label filter = new Label("Filtre:");
        ChoiceBox chooseFilter = new ChoiceBox();
        chooseFilter.getItems().addAll("", State.getStatusString(State.STORAGE),
                State.getStatusString(State.STOLEN),
                State.getStatusString(State.LENT),
                State.getStatusString(State.BROKEN));
        chooseFilter.setId("chooseFilter");
        TextField description = new TextField();
        description.setId("description");

        description.setPromptText("Recherche");

        HBox toolBar = new HBox(addItem, removeItem,  restoreItem,
                addFilterSeperator, gameFilter, bookFilter, toolFilter,
                filter, chooseFilter, description);

        toolBar.setSpacing(10);
        toolBar.setPadding(new Insets(10));

        setupButtonActionsListControll();
        setupButtonActionsFilters();


        return toolBar;
    }

    private static void setupButtonActionsListControll() {
        addItem.setOnAction(event -> {
            TwoPane.addItem();
        });
        removeItem.setOnAction(actionEvent -> {
            if (Table.getSelectedItem() != null) {
                Inventory inv = Inventory.getInstance();
                inv.remove(Table.getSelectedItem());
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
                Table.resetSelectedItems();
            }
        });
        restoreItem.setOnAction(actionEvent -> {
            if (Table.getSelectedItemRestore() != null) {
                Inventory inv = Inventory.getInstance();
                inv.restore(Table.getSelectedItemRestore());
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
                Table.resetSelectedItems();
            }
        });
    }
    private static void setupButtonActionsFilters() {
        gameFilter.setOnAction(actionEvent -> {
            bookFilter.setSelected(false);
            toolFilter.setSelected(false);
            Inventory inv = Inventory.getInstance();
            if (gameFilter.isSelected()) {
                filter(Type.GAME, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }

        });
        bookFilter.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            gameFilter.setSelected(false);
            toolFilter.setSelected(false);
            if (bookFilter.isSelected()) {
                filter(Type.BOOK, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }
        });
        toolFilter.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            gameFilter.setSelected(false);
            bookFilter.setSelected(false);
            if (toolFilter.isSelected()) {
                filter(Type.TOOL, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }
        });
    }

    private static void filter(Type type, Inventory inv) {
        Item[] items =  inv.getItems();
        ItemList filteredList = new ItemList();
        for (Item item: items) {
            if (item.getType() == type) {
                filteredList.add(item);
            }
        }

        Item[] itemsRes =  inv.getRestoreItems();
        ItemList filteredListRestore = new ItemList();
        for (Item item: itemsRes) {
            if (item.getType() == type) {
                filteredListRestore.add(item);
            }
        }
        Table.resetItemsList(filteredList.getItemList(), filteredListRestore.getItemList());
    }


    public static void setButtonAction(Runnable action) {
        gameFilter.setOnAction(actionEvent ->
                action.run());
    }

    public static ToggleButton getGameFilter() {
        return gameFilter;
    }

    public static ToggleButton getToolFilter() {
        return toolFilter;
    }

    public static ToggleButton getBookFilter() {
        return bookFilter;
    }

}
