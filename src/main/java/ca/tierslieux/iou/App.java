package ca.tierslieux.iou;

import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Create an ObservableList to hold the data
        ObservableList<Item> people = FXCollections.observableArrayList(
                new Item("John", "Doe", 25),
                new Item("Jane", "Smith", 30),
                new Item("Emily", "Johnson", 35)
        );

        // Bind the data to the table
        tableView.setItems(people);

        // Create a layout and add the TableView
        VBox vbox = new VBox(tableView);

        // Set up the scene and stage
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX TableView Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}