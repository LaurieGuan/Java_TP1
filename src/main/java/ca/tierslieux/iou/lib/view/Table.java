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
    public static TableView<Item> getTableView(Item[] items) {
        TableView<Item> tableView = new TableView<>();
        tableView.setPlaceholder(new Label("Aucun contenu dans la table."));

        TableColumn<Item, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Item, String> state = new TableColumn<>("État");
        state.setCellValueFactory(new PropertyValueFactory<>("StatusString"));

        TableColumn<Item, LocalDate> purchaseDate = new TableColumn<>("Date d'achat");
        purchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        TableColumn<Item, Integer> price = new TableColumn<>("Prix");
        price.setCellValueFactory(new PropertyValueFactory<>("formattedPrice"));

        tableView.getColumns().addAll(description, state, purchaseDate, price);

        ObservableList<Item> observableList = FXCollections.observableArrayList();

        for (Item item: items) {
            observableList.add(item);
        }

        tableView.setItems(observableList);

        return tableView;
    }
}
