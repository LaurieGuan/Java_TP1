package ca.tierslieux.iou;

import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import ca.tierslieux.iou.lib.view.Table;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Inventory.createInstanceFromFile("src/main/resources/ca/tierslieux/iou/TestListe.json");
        Inventory inv = Inventory.getInstance();

        VBox vbox = new VBox();

        Button button = new Button("RemoveItem");
        TableView<Item> table = Table.getTableView(inv.getItems());

        // Set up the scene and stage
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.setTitle("JavaFX TableView Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}