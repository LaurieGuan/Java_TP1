package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SplitView {

    public static void GeneratePanel(Item[] items) {

        TableView<Item> tableView = new TableView<>();

        tableView.setPlaceholder(new Label("Aucun contenu dans la table."));

        TableColumn<Item, String> description = new TableColumn<>("Description");
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Item, String> state = new TableColumn<>("État");
        state.setCellValueFactory(new PropertyValueFactory<>("state"));

        TableColumn<Item, String> purchaseDate = new TableColumn<>("Date d'achat");
        purchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchaseDate"));

        TableColumn<Item, String> price = new TableColumn<>("Prix");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableView.getColumns().addAll(description, state, purchaseDate, price);

        ObservableList<Item> itemList = FXCollections.observableArrayList();

        for (Item item: items) {
            itemList.add()
        }

        SplitPane split = new SplitPane();
    }
}
