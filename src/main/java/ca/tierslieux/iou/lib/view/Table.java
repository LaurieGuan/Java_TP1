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
    private static ObservableList<Item> observableList;
    public static TableView<Item> getTableView(Item[] items) {
        table = new TableView<>();
        table.setPlaceholder(new Label("Aucun contenu dans la table."));

        TableColumn<Item, String> description = new TableColumn<>("Description");
        TableColumn<Item, String> state = new TableColumn<>("État");
        TableColumn<Item, LocalDate> purchaseDate = new TableColumn<>("Date d'achat");
        TableColumn<Item, Float> price = new TableColumn<>("Prix");

        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        state.setCellValueFactory(new PropertyValueFactory<>("StatusString"));
        purchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(description, state, purchaseDate, price);

         observableList = FXCollections.observableArrayList();

        for (Item item: items) {
            observableList.add(item);
        }

        table.setItems(observableList);

        return table;
    }

    public void resetItemsList(Item[] items) {
        observableList = FXCollections.observableArrayList();
        for (Item item: items) {
            observableList.add(item);
        }
    }
}
