package ca.tierslieux.iou;

import ca.tierslieux.iou.lib.logic.list.Inventory;
import ca.tierslieux.iou.lib.view.Table;
import ca.tierslieux.iou.lib.view.ToolBar;
import ca.tierslieux.iou.lib.view.TopMenu;
import ca.tierslieux.iou.lib.view.TwoPane;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {
    private static Scene mainScene;
    @Override
    public void start(Stage stage) throws IOException {

        Inventory.createInstance("liste");
        Inventory inv = Inventory.getInstance();

        HBox split = new HBox();

        Node topMenu = TopMenu.Generate(stage);
        Node toolBar = ToolBar.Generate();
        TwoPane.GeneratePanel(split, inv.getItems(), inv.getRestoreItems());

        VBox vbox = new VBox(topMenu, toolBar, split);
        VBox.setVgrow(split, Priority.ALWAYS);

        mainScene = new Scene(vbox, 1000, 1000);
        stage.setScene(mainScene);
        stage.setTitle("IOU");
        stage.show();
    }

    public void handleButtonAction() {
//        switch (buttonId) {
//            case ""
//        }
        Inventory inv = Inventory.getInstance();
        inv.pop(0);
        Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
        System.out.println(inv.getItem(0));
    }

    public static void main(String[] args) {
        launch();
    }

    public static Scene getMainScene() {
        return mainScene;
    }
}