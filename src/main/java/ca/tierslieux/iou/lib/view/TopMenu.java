package ca.tierslieux.iou.lib.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;

public class TopMenu {
    public static HBox Generate() {
        MenuButton file = new MenuButton("Fichier");
        file.setId("file");

        MenuItem newList = new MenuItem("Nouvelle liste");
        MenuItem open = new MenuItem("Ouvrir");
        MenuItem save = new MenuItem("Enregistrer");
        MenuItem saveAs = new MenuItem("Enregistrer sous");
        MenuItem export = new MenuItem("Exporter");

        file.getItems().addAll(newList, open, save, saveAs, export);

        HBox hBox = new HBox(file);

        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));

        return hBox;
    }
}
