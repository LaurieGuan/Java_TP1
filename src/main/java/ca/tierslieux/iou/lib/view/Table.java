package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Table {

    TableView<Item> tableV = new TableView<>();

    TableColumn<Item, String> nom = new TableColumn<>("Name");
    nom.setCellValueFactory(new PropertyValueFactory<>("name"));

}
