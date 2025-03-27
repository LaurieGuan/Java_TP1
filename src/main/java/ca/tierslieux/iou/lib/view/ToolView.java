package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.App;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.State;
import ca.tierslieux.iou.lib.logic.items.Tool;
import ca.tierslieux.iou.lib.logic.list.Inventory;
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
        modButton.setOnAction(actionEvent -> {
            Item item = null;
            if (Table.getSelectedItem() != null) {
                item = Table.getSelectedItem();
            } else if (Table.getSelectedItemRestore() != null) {
                item = Table.getSelectedItem();
            }

            if (item != null) {
                TwoPane.modifyItem(item);
            }
        });

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
        modelField.setId("model");

        Label brandLabel = new Label("Marque:");
        TextField brandField = new TextField();
        HBox brandBox = new HBox(brandLabel, brandField);
        setHBoxPreferences(brandBox, brandLabel, brandField);
        brandField.setId("brand");

        Button addButton = new Button("Ajouter");
        Button clearButton = new Button("Effacer");
        HBox buttonBox = new HBox(addButton, clearButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                toolSectionLabel,
                modelBox, brandBox,
                buttonBox
        );

        setAddAction(addButton);
        setClearAction(clearButton);

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
        modelField.setId("model");

        Label brandLabel = new Label("Marque:");
        TextField brandField = new TextField(tool.getBrand());
        HBox brandBox = new HBox(brandLabel, brandField);
        setHBoxPreferences(brandBox, brandLabel, brandField);
        brandField.setId("brand");

        Button saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                toolSectionLabel,
                modelBox, brandBox,
                buttonBox
        );

        setSaveAction(saveButton, tool);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().clear();
        vBox.getChildren().addAll(generalSection, vbox);
    }

    private static void setSaveAction(Button saveButton, Tool tool) {
        saveButton.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            if (ItemCommonView.verifyCommonItemInput() && verifySpecialInput()) {
                TextField name = (TextField) App.getMainScene().lookup("#name");
                TextField description = (TextField) App.getMainScene().lookup("#description");
                TextField price = (TextField) App.getMainScene().lookup("#price");
                DatePicker date = (DatePicker) App.getMainScene().lookup("#datePicker");
                TextField imageReceipt = (TextField) App.getMainScene().lookup("#imageReceipt");
                TextField location = (TextField) App.getMainScene().lookup("#location");
                ComboBox state = (ComboBox) App.getMainScene().lookup("#stateBox");
                TextField model = (TextField) App.getMainScene().lookup("#model");
                TextField brand = (TextField) App.getMainScene().lookup("#brand");

                State status =  switch (state.getValue().toString()) {
                    case "Volé" -> State.STOLEN;
                    case "En ma possession" -> State.STORAGE;
                    case "Prêté" -> State.LENT;
                    case "Brisé" -> State.BROKEN;
                    default -> null;
                };

                tool.setName(name.getText());
                tool.setDescription(description.getText());
                tool.setFormattedPrice(price.getText());
                tool.setPurchaseDate(date.getValue());
                tool.setPathToReceipt(imageReceipt.getText());
                tool.setLocation(location.getText());
                tool.setStatus(status);

                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
                ToolBar.resetFilters();
                Inventory.isBeingModified = false;
                TwoPane.showItem(tool);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Champs non complétés");
                errorAlert.setContentText("Tous les champs doivent être remplis.");
                errorAlert.showAndWait();
            }
        });
    }

    private static void setClearAction(Button clearButton) {
        clearButton.setOnAction(actionEvent -> {
            Inventory.isBeingModified = false;
            TwoPane.addItem();
        });
    }

    private static void setAddAction(Button addButton) {
        addButton.setOnAction(actionEvent -> {
            Inventory inv = Inventory.getInstance();
            if (ItemCommonView.verifyCommonItemInput() && verifySpecialInput()) {
                TextField name = (TextField) App.getMainScene().lookup("#name");
                TextField description = (TextField) App.getMainScene().lookup("#description");
                TextField price = (TextField) App.getMainScene().lookup("#price");
                DatePicker date = (DatePicker) App.getMainScene().lookup("#datePicker");
                TextField imageReceipt = (TextField) App.getMainScene().lookup("#imageReceipt");
                TextField location = (TextField) App.getMainScene().lookup("#location");
                ComboBox state = (ComboBox) App.getMainScene().lookup("#stateBox");
                TextField model = (TextField) App.getMainScene().lookup("#model");
                TextField brand = (TextField) App.getMainScene().lookup("#brand");
                Spinner<Integer> numbertoAdd = (Spinner<Integer>) App.getMainScene().lookup("#numberToAdd");

                State status = switch (state.getValue().toString()) {
                    case "Volé" -> State.STOLEN;
                    case "En ma possession" -> State.STORAGE;
                    case "Prêté" -> State.LENT;
                    case "Brisé" -> State.BROKEN;
                    default -> null;
                };
                Tool tempTool = null;
                int numberOfObjects = numbertoAdd.getValue();
                for (int i = 0; i < numberOfObjects; i++) {
                     tempTool = new Tool(name.getText(), description.getText(), 0, date.getValue(),
                            imageReceipt.getText(), location.getText(), status, model.getText(), brand.getText());
                    tempTool.setFormattedPrice(price.getText());
                    inv.add(tempTool);
                }

                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
                ToolBar.resetFilters();
                Inventory.isBeingModified = false;
                TwoPane.showItem(tempTool);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Champs non complétés");
                errorAlert.setContentText("Tous les champs doivent être remplis.");
                errorAlert.showAndWait();
            }
        });
    }

    private static boolean verifySpecialInput() {
        boolean allFieldsOkay = true;
        TextField model = (TextField) App.getMainScene().lookup("#model");
        allFieldsOkay = (!model.getText().isEmpty())&& allFieldsOkay;

        TextField brand = (TextField) App.getMainScene().lookup("#brand");
        allFieldsOkay = (!brand.getText().isEmpty())&& allFieldsOkay;

        return allFieldsOkay;
    }

    private static void setHBoxPreferences(HBox hbox, Label label, Control field) {
        hbox.setSpacing(10);
        label.setPrefWidth(150);
        field.setPrefWidth(200);
    }
}
