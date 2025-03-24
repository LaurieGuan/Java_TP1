package ca.tierslieux.iou;

import ca.tierslieux.iou.lib.logic.exception.FileAlreadyExists;
import ca.tierslieux.iou.lib.logic.file.CustomFile;
import ca.tierslieux.iou.lib.logic.file.FileType;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        VBox vBox = new VBox(new Label("A JavaFX Label"));
        Scene scene = new Scene(vBox, 300, 300);

        stage.setScene(scene);
        stage.setTitle("temporaire");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}