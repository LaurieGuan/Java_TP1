package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.exception.ListNotSaved;
import ca.tierslieux.iou.lib.logic.exception.PathNotSpecified;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class TopMenu {
    public static HBox Generate(Stage stage) {
        MenuButton file = new MenuButton("Fichier");
        file.setId("file");

        MenuItem newList = new MenuItem("Nouvelle liste");
        MenuItem open = new MenuItem("Ouvrir");
        MenuItem save = new MenuItem("Enregistrer");
        MenuItem saveAs = new MenuItem("Enregistrer sous");

        file.getItems().addAll(newList, open, save, saveAs);

        HBox hBox = new HBox(file);

        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10));

        setActions(stage, newList, open, save, saveAs);

        return hBox;
    }

    private static void setActions(Stage stage, MenuItem newList, MenuItem open, MenuItem save, MenuItem saveAs) {
        saveAs.setOnAction(actionEvent -> {
            saveAs(stage);
        });

        save.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            inv.save();
        });
        newList.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            try {
                inv.close();
                Inventory.createInstance("list");

            } catch (ListNotSaved e) {
                Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                errorAlert.setHeaderText("Liste non enregistrée");
                errorAlert.setContentText("Vous risquez de perdre des informations si vous\nn'enregistrez pas la liste.");
                Optional<ButtonType> result = errorAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    inv.close(true);
                    Inventory.createInstance("list");
                }
            } finally {
                inv = Inventory.getInstance();
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
            }
            Inventory.createInstance("standholder");
        });

        open.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le fichier sous:");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                inv.close(true);
                Inventory.createInstanceFromFile(selectedFile.getAbsolutePath());
                inv = Inventory.getInstance();
                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());

            } else {
                System.err.println("Pas de fichiers sélectionnés");
            }

        });
    }

    private static void saveAs(Stage stage) {
        Inventory inv = Inventory.getInstance();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Inventory");

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JSON Files", "*.json"),
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            String filePath = file.getAbsolutePath();

            String selectedExtension = fileChooser.getSelectedExtensionFilter().getExtensions().get(0);
            if (!filePath.endsWith(".json") && !filePath.endsWith(".csv")) {
                filePath += selectedExtension.substring(1);  // Add extension (e.g., ".json")
            }

            try {
                if (filePath.endsWith(".json")) {
                    inv.saveAs(inv.getListName(), filePath);  // Save as JSON
                } else if (filePath.endsWith(".csv")) {
                    inv.export(filePath);  // Export to CSV
                }
                System.out.println("Inventory saved successfully!");
            } catch (Exception e) {
                System.err.println("Error saving inventory: " + e.getMessage());
            }
        } else {
            System.out.println("Save operation cancelled.");
        }
    }
}
