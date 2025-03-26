package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;

import java.time.LocalDate;

public class Table {
    private static TableView<Item> table;
    private static TableView<Item> tableRestore;
    private static ObservableList<Item> observableList;
    private static ObservableList<Item> observableListRestore;
    private static Item selectedItem = null;
    private static Item selectedItemRestore = null;

    public enum ListType {
        MAIN_LIST,
        RESTORE
    }

    public static TableView<Item> getTableView(Item[] items, ListType listType) {

        TableView<Item> tempTable;
        ObservableList<Item> tempList;
        tempTable = new TableView<>();
        tempTable.setPlaceholder(new Label("Aucun contenu dans la table."));

        TableColumn<Item, String> name = new TableColumn<>("Name");
        name.prefWidthProperty().bind(tempTable.widthProperty().multiply(0.15));
        name.setResizable(false);
        name.setReorderable(false);

        TableColumn<Item, String> description = new TableColumn<>("Description");
        description.prefWidthProperty().bind(tempTable.widthProperty().multiply(0.40));
        description.setReorderable(false);

        TableColumn<Item, String> state = new TableColumn<>("État");
        state.prefWidthProperty().bind(tempTable.widthProperty().multiply(0.15));
        state.setResizable(false);
        state.setReorderable(false);

        TableColumn<Item, LocalDate> purchaseDate = new TableColumn<>("Date d'achat");
        purchaseDate.prefWidthProperty().bind(tempTable.widthProperty().multiply(0.15));
        purchaseDate.setResizable(false);
        purchaseDate.setReorderable(false);

        TableColumn<Item, Float> price = new TableColumn<>("Prix");
        price.prefWidthProperty().bind(tempTable.widthProperty().multiply(0.15));
        price.setResizable(false);
        price.setReorderable(false);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        state.setCellValueFactory(new PropertyValueFactory<>("StatusString"));
        purchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tempTable.getColumns().addAll(name, description, state, purchaseDate, price);

         tempList = FXCollections.observableArrayList();

        for (Item item: items) {
            tempList.add(item);
        }

        tempTable.setItems(tempList);
        tempTable.setId("mainList");

        switch (listType) {
            case MAIN_LIST:
                table = tempTable;
                observableList = tempList;
                break;
            case RESTORE:
                tableRestore = tempTable;
                observableListRestore = tempList ;
                break;
        }
        tempTable.getSelectionModel().selectedItemProperty().addListener((
                observable, _, newValue) -> {
            if (newValue != null && listType == listType.MAIN_LIST) {
                selectedItem = newValue;
            } else if (newValue != null && listType == listType.RESTORE) {
                selectedItemRestore = newValue;
            }

            TwoPane.showItem(selectedItem);
        });

        return tempTable;
    }

    public static void resetItemsList(Item[] items, Item[] restoreItems) {
        observableList = FXCollections.observableArrayList();
        observableListRestore = FXCollections.observableArrayList();
        for (Item item: items) {
            observableList.add(item);
        }
        for (Item item: restoreItems) {
            observableListRestore.add(item);
        }

        table.setItems(observableList);
        tableRestore.setItems(observableListRestore);
    }

    public static Item getSelectedItem() {
        return selectedItem;
    }

    public static Item getSelectedItemRestore() {
        return selectedItemRestore;
    }

    public static void resetSelectedItems() {
        selectedItemRestore = null;
        selectedItem = null;
    }
}
