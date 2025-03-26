package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.Regex;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.State;
import ca.tierslieux.iou.lib.logic.items.Type;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import ca.tierslieux.iou.lib.logic.list.ItemList;
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
   private static ChoiceBox chooseFilter;
   private static TextField description;


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
        chooseFilter = new ChoiceBox();
        chooseFilter.getItems().addAll("", State.getStatusString(State.STORAGE),
                State.getStatusString(State.STOLEN),
                State.getStatusString(State.LENT),
                State.getStatusString(State.BROKEN));
        chooseFilter.setId("chooseFilter");
        description = new TextField();
        description.setPromptText("Recherche");

        HBox toolBar = new HBox(addItem, removeItem,  restoreItem,
                addFilterSeperator, gameFilter, bookFilter, toolFilter,
                filter, chooseFilter, description);

        toolBar.setSpacing(10);
        toolBar.setPadding(new Insets(10));

        setupButtonActionsListControll();
        setupButtonActionTypeFilter();
        setupButtonActionFilter();
        setupSearchAction();

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
    private static void setupButtonActionTypeFilter() {
        gameFilter.setOnAction(actionEvent -> {
            bookFilter.setSelected(false);
            toolFilter.setSelected(false);
            Inventory inv = Inventory.getInstance();
            if (gameFilter.isSelected()) {
                typeFilter(Type.GAME, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }

        });
        bookFilter.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            gameFilter.setSelected(false);
            toolFilter.setSelected(false);
            if (bookFilter.isSelected()) {
                typeFilter(Type.BOOK, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }
        });
        toolFilter.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            gameFilter.setSelected(false);
            bookFilter.setSelected(false);
            if (toolFilter.isSelected()) {
                typeFilter(Type.TOOL, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }
        });
    }
    private static void setupButtonActionFilter() {
        Inventory inv = Inventory.getInstance();
        chooseFilter.setOnAction(actionEvent -> {
            State state = switch (chooseFilter.getValue().toString()) {
                case "En ma possession" -> State.STORAGE;
                case "Brisé" -> State.BROKEN;
                case "Volé" -> State.STOLEN;
                case "Prêté" -> State.LENT;
                default -> null;
            };

            if (state != null) {
                stateFilter(state, inv);
            } else {
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }
        });

    }

    private static void setupSearchAction() {
        description.setOnKeyPressed(keyEvent -> {
            Inventory inv = Inventory.getInstance();
            String input = description.getText();
            if (input != "") {
                patternSearch(input, inv);
            }
        });
    }

    private static void typeFilter(Type type, Inventory inv) {
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

    private static void stateFilter(State state, Inventory inv) {
        Item[] items =  inv.getItems();
        ItemList filteredList = new ItemList();
        for (Item item: items) {
            if (item.getStatus() == state) {
                filteredList.add(item);
            }
        }

        Item[] itemsRes =  inv.getRestoreItems();
        ItemList filteredListRestore = new ItemList();
        for (Item item: itemsRes) {
            if (item.getStatus() == state) {
                filteredListRestore.add(item);
            }
        }
        Table.resetItemsList(filteredList.getItemList(), filteredListRestore.getItemList());
    }

    private static void patternSearch(String input, Inventory inv) {
        Item[] items =  inv.getItems();
        ItemList filteredList = new ItemList();
        for (Item item: items) {
            if (Regex.matchesPattern(item.getDescription(), input)) {
                filteredList.add(item);
            }
        }

        Item[] itemsRes =  inv.getRestoreItems();
        ItemList filteredListRestore = new ItemList();
        for (Item item: itemsRes) {
            if (Regex.matchesPattern(item.getDescription(), input)) {
                filteredListRestore.add(item);
            }
        }
        Table.resetItemsList(filteredList.getItemList(), filteredListRestore.getItemList());
    }
}
