package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Tool;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ToolView {

    public static void view(Tool tool, VBox vBox) {
        VBox generalSection = ItemCommonView.view(tool);
        Label toolSectionLabel = new Label("Section outil");

        Label modelLabel = new Label("Modèle:");
        TextField modelField = new TextField(tool.getModel());
        HBox modelBox = new HBox(modelLabel, modelField);
        setHBoxPreferences(modelBox, modelLabel, modelField);
        modelField.setEditable(false);

        Label brandLabel = new Label("Marque:");
        TextField brandField = new TextField(tool.getBrand());
        HBox brandBox = new HBox(brandLabel, brandField);
        setHBoxPreferences(brandBox, brandLabel, brandField);
        brandField.setEditable(false);

        Button modButton = new Button("Modifier");
        HBox buttonBox = new HBox(modButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                toolSectionLabel,
                modelBox, brandBox, modButton
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().addAll(generalSection, vbox);
    }

    public static void add(VBox vBox) {
        VBox generalSection = ItemCommonView.add();

        Label toolSectionLabel = new Label("Section outil");

        Label modelLabel = new Label("Modèle:");
        TextField modelField = new TextField();
        HBox modelBox = new HBox(modelLabel, modelField);
        setHBoxPreferences(modelBox, modelLabel, modelField);

        Label brandLabel = new Label("Marque:");
        TextField brandField = new TextField();
        HBox brandBox = new HBox(brandLabel, brandField);
        setHBoxPreferences(brandBox, brandLabel, brandField);

        Button addButton = new Button("Ajouter");
        Button clearButton = new Button("Effacer");
        HBox buttonBox = new HBox(addButton, clearButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                toolSectionLabel,
                modelBox, brandBox,
                buttonBox
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().removeAll();
        vBox.getChildren().addAll(generalSection, vbox);
    }

    public static void modify(Tool tool, VBox vBox) {
        VBox generalSection = ItemCommonView.modify(tool);
        Label toolSectionLabel = new Label("Modifier outil");

        Label modelLabel = new Label("Modèle:");
        TextField modelField = new TextField(tool.getModel());
        HBox modelBox = new HBox(modelLabel, modelField);
        setHBoxPreferences(modelBox, modelLabel, modelField);

        Label brandLabel = new Label("Marque:");
        TextField brandField = new TextField(tool.getBrand());
        HBox brandBox = new HBox(brandLabel, brandField);
        setHBoxPreferences(brandBox, brandLabel, brandField);

        Button saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                toolSectionLabel,
                modelBox, brandBox,
                buttonBox
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().clear();
        vBox.getChildren().addAll(generalSection, vbox);
    }

    private static void setHBoxPreferences(HBox hbox, Label label, Control field) {
        hbox.setSpacing(10);
        label.setPrefWidth(150);
        field.setPrefWidth(200);
    }
}
