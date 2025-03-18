package ca.tierslieux.iou;

import ca.tierslieux.iou.lib.logic.items.Book;
import ca.tierslieux.iou.lib.logic.test;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Book test = Book.fromJson("{\n" +
                "  \"Book\": {\n" +
                "    \"name\": \"Bilbo, The Hobbit\",\n" +
                "    \"description\": \"Bilbo set off on an adventure.\",\n" +
                "    \"price\": 2000,\n" +
                "    \"purchaseDate\": \"2024-12-12\",\n" +
                "    \"receipt\": \"C:\\Users\\6287054\\facture.txt\",\n" +
                "    \"location\": \"À la maison\",\n" +
                "    \"status\": \"STORAGE\",\n" +
                "    \"author\": \"Tolkien\",\n" +
                "    \"publisher\": \"whatever\",\n" +
                "    \"publishedYear\": 1937,\n" +
                "    \"isbn\": \"12-1341-134-123\",\n" +
                "    \"pathToImage\": \"C:\\test\"\n" +
                "  }\n" +
                "}");
        System.out.println(test.getDescription());
        System.out.println(test.getPrice());
        System.out.println(test.getStatus());
        System.out.println(test.getLocation());

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