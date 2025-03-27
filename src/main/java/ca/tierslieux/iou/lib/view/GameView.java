package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.App;
import ca.tierslieux.iou.lib.logic.Regex;
import ca.tierslieux.iou.lib.logic.items.Book;
import ca.tierslieux.iou.lib.logic.items.Game;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.State;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView {

    public static void view(Game game, VBox vBox) {
        VBox generalSection = ItemCommonView.view(game);
        Label gameSectionLabel = new Label("Section jeu");

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField(game.getPublisher());
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);
        publisherField.setEditable(false);

        Label minimumAgeLabel = new Label("Âge minimum:");
        Spinner<Integer> minimumAgeField = new Spinner<Integer>();
        HBox minimumAgeBox = new HBox(minimumAgeLabel, minimumAgeField);
        setHBoxPreferences(minimumAgeBox, minimumAgeLabel, minimumAgeField);
        int minAge = game.getMinimumAge();
        SpinnerValueFactory<Integer> valueAgeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(minAge, minAge, minAge);
        minimumAgeField.setValueFactory(valueAgeFactory);
        minimumAgeField.setEditable(false);

        Label minNumberPlayersLabel = new Label("Nombre minimum de joueurs:");
        Spinner<Integer> minNumberPlayersField = new Spinner<Integer>();
        HBox minNumberPlayersBox = new HBox(minNumberPlayersLabel, minNumberPlayersField);
        setHBoxPreferences(minNumberPlayersBox, minNumberPlayersLabel, minNumberPlayersField);
        int minNbPlayers = game.getMinNumberPlayers();
        SpinnerValueFactory<Integer> valueMinPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(minNbPlayers, minNbPlayers, minNbPlayers);
        minNumberPlayersField.setValueFactory(valueMinPlayerFactory);
        minNumberPlayersField.setEditable(false);

        Label maxNumberPlayersLabel = new Label("Nombre maximum de joueurs:");
        Spinner<Integer> maxNumberPlayersField = new Spinner<Integer>();
        HBox maxNumberPlayersBox = new HBox(maxNumberPlayersLabel, maxNumberPlayersField);
        setHBoxPreferences(maxNumberPlayersBox, maxNumberPlayersLabel, maxNumberPlayersField);
        int maxNbPlayers = game.getMaxNumberPlayers();
        SpinnerValueFactory<Integer> valueMaxPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(maxNbPlayers, maxNbPlayers, maxNbPlayers);
        maxNumberPlayersField.setValueFactory(valueMaxPlayerFactory);
        maxNumberPlayersField.setEditable(false);

        Button modButton = new Button("Modifier");
        HBox buttonBox = new HBox(modButton);
        buttonBox.setSpacing(10);

        // Main VBox
        VBox vbox = new VBox(
                gameSectionLabel,
                publisherBox, minimumAgeBox, minNumberPlayersBox, maxNumberPlayersBox,
                buttonBox
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

        Label gameSectionLabel = new Label("Section jeu");

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField();
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);
        publisherField.setId("publisher");

        Label minimumAgeLabel = new Label("Âge minimum:");
        Spinner<Integer> minimumAgeField = new Spinner<Integer>();
        HBox minimumAgeBox = new HBox(minimumAgeLabel, minimumAgeField);
        setHBoxPreferences(minimumAgeBox, minimumAgeLabel, minimumAgeField);
        SpinnerValueFactory<Integer> valueAgeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        minimumAgeField.setValueFactory(valueAgeFactory);
        minimumAgeField.setId("minAge");

        Label minNumberPlayersLabel = new Label("Nombre minimum de joueurs:");
        Spinner<Integer> minNumberPlayersField = new Spinner<Integer>();
        HBox minNumberPlayersBox = new HBox(minNumberPlayersLabel, minNumberPlayersField);
        setHBoxPreferences(minNumberPlayersBox, minNumberPlayersLabel, minNumberPlayersField);
        SpinnerValueFactory<Integer> valueMinPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        minNumberPlayersField.setValueFactory(valueMinPlayerFactory);
        minNumberPlayersField.setId("minNbPlayers");

        Label maxNumberPlayersLabel = new Label("Nombre maximum de joueurs:");
        Spinner<Integer> maxNumberPlayersField = new Spinner<Integer>();
        HBox maxNumberPlayersBox = new HBox(maxNumberPlayersLabel, maxNumberPlayersField);
        setHBoxPreferences(maxNumberPlayersBox, maxNumberPlayersLabel, maxNumberPlayersField);
        SpinnerValueFactory<Integer> valueMaxPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 4);
        maxNumberPlayersField.setValueFactory(valueMaxPlayerFactory);
        maxNumberPlayersField.setId("maxNbPlayers");

        Button addButton = new Button("Ajouter");
        Button clearButton = new Button("Effacer");
        HBox buttonBox = new HBox(addButton, clearButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                gameSectionLabel,
                publisherBox, minimumAgeBox, minNumberPlayersBox,maxNumberPlayersBox,
                buttonBox
        );

        setAddAction(addButton);
        setClearAction(clearButton);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().removeAll();
        vBox.getChildren().addAll(generalSection, vbox);
    }

    public static void modify(Game game, VBox vBox) {
        VBox generalSection = ItemCommonView.modify(game);

        Label gameSectionLabel = new Label("Modifier jeu");

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField(game.getPublisher());
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);
        publisherField.setId("publisher");

        Label minimumAgeLabel = new Label("Âge minimum:");
        Spinner<Integer> minimumAgeField = new Spinner<>();
        HBox minimumAgeBox = new HBox(minimumAgeLabel, minimumAgeField);
        setHBoxPreferences(minimumAgeBox, minimumAgeLabel, minimumAgeField);
        SpinnerValueFactory<Integer> valueAgeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, game.getMinimumAge());
        minimumAgeField.setValueFactory(valueAgeFactory);
        minimumAgeField.getValueFactory().setValue(game.getMinimumAge());
        minimumAgeField.setId("minAge");

        Label minNumberPlayersLabel = new Label("Nombre minimum de joueurs:");
        Spinner<Integer> minNumberPlayersField = new Spinner<>();
        HBox minNumberPlayersBox = new HBox(minNumberPlayersLabel, minNumberPlayersField);
        setHBoxPreferences(minNumberPlayersBox, minNumberPlayersLabel, minNumberPlayersField);
        SpinnerValueFactory<Integer> valueMinPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, game.getMinNumberPlayers());
        minNumberPlayersField.setValueFactory(valueMinPlayerFactory);
        minNumberPlayersField.getValueFactory().setValue(game.getMinNumberPlayers());
        minNumberPlayersField.setId("minNbPlayers");

        Label maxNumberPlayersLabel = new Label("Nombre maximum de joueurs:");
        Spinner<Integer> maxNumberPlayersField = new Spinner<>();
        HBox maxNumberPlayersBox = new HBox(maxNumberPlayersLabel, maxNumberPlayersField);
        setHBoxPreferences(maxNumberPlayersBox, maxNumberPlayersLabel, maxNumberPlayersField);
        SpinnerValueFactory<Integer> valueMaxPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, game.getMaxNumberPlayers());
        maxNumberPlayersField.setValueFactory(valueMaxPlayerFactory);
        maxNumberPlayersField.getValueFactory().setValue(game.getMaxNumberPlayers());
        maxNumberPlayersField.setId("maxNbPlayers");


        Button saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                gameSectionLabel,
                publisherBox, minimumAgeBox, minNumberPlayersBox, maxNumberPlayersBox,
                buttonBox
        );

        setSaveAction(saveButton, game);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().clear();
        vBox.getChildren().addAll(generalSection, vbox);
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
                TextField publisher = (TextField) App.getMainScene().lookup("#publisher");
                Spinner<Integer> minAge = (Spinner<Integer>) App.getMainScene().lookup("#minAge");
                Spinner<Integer> minNbPlayers = (Spinner<Integer>) App.getMainScene().lookup("#minNbPlayers");
                Spinner<Integer> maxNbPlayers = (Spinner<Integer>) App.getMainScene().lookup("#maxNbPlayers");
                Spinner<Integer> numbertoAdd = (Spinner<Integer>) App.getMainScene().lookup("#numberToAdd");

                State status = switch (state.getValue().toString()) {
                    case "Volé" -> State.STOLEN;
                    case "En ma possession" -> State.STORAGE;
                    case "Prêté" -> State.LENT;
                    case "Brisé" -> State.BROKEN;
                    default -> null;
                };
                Game tempGame = null;
                int numberOfObjects = numbertoAdd.getValue();
                for (int i = 0; i < numberOfObjects; i++) {
                    tempGame = new Game(name.getText(), description.getText(), 0, date.getValue(),
                            imageReceipt.getText(), location.getText(), status, publisher.getText(), minAge.getValue(),
                            minNbPlayers.getValue(), maxNbPlayers.getValue(), "");
                    tempGame.setFormattedPrice(price.getText());
                    inv.add(tempGame);
                }

                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
                ToolBar.resetFilters();
                Inventory.isBeingModified = false;
                TwoPane.showItem(tempGame);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Champs non complétés");
                errorAlert.setContentText("Tous les champs doivent être remplis.");
                errorAlert.showAndWait();
            }
        });
    }

    private static void setSaveAction(Button saveButton, Game game) {
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
                TextField publisher = (TextField) App.getMainScene().lookup("#publisher");
                Spinner<Integer> minAge = (Spinner<Integer>) App.getMainScene().lookup("#minAge");
                Spinner<Integer> minNbPlayers = (Spinner<Integer>) App.getMainScene().lookup("#minNbPlayers");
                Spinner<Integer> maxNbPlayers = (Spinner<Integer>) App.getMainScene().lookup("#maxNbPlayers");

                State status =  switch (state.getValue().toString()) {
                    case "Volé" -> State.STOLEN;
                    case "En ma possession" -> State.STORAGE;
                    case "Prêté" -> State.LENT;
                    case "Brisé" -> State.BROKEN;
                    default -> null;
                };

                game.setName(name.getText());
                game.setDescription(description.getText());
                game.setFormattedPrice(price.getText());
                game.setPurchaseDate(date.getValue());
                game.setPathToReceipt(imageReceipt.getText());
                game.setLocation(location.getText());
                game.setStatus(status);
                game.setPublisher(publisher.getText());
                game.setMinimumAge(minAge.getValue());
                game.setMinNumberPlayers(minNbPlayers.getValue());
                game.setMaxNumberPlayers(maxNbPlayers.getValue());
                game.setPathToImage("");

                Table.resetItemsList(inv.getItems(), inv.getRestoreItems());
                ToolBar.resetFilters();
                Inventory.isBeingModified = false;
                TwoPane.showItem(game);
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
        TextField publisher = (TextField) App.getMainScene().lookup("#publisher");
        allFieldsOkay = (!publisher.getText().isEmpty())&& allFieldsOkay;

        Spinner<Integer> minAge = (Spinner<Integer>) App.getMainScene().lookup("#minAge");
        allFieldsOkay = (minAge.getValue() != null) && allFieldsOkay;

        Spinner<Integer> minPlayers = (Spinner<Integer>) App.getMainScene().lookup("#minNbPlayers");
        allFieldsOkay = (minPlayers.getValue() != null) && allFieldsOkay;

        Spinner<Integer> maxPlayers = (Spinner<Integer>) App.getMainScene().lookup("#maxNbPlayers");
        allFieldsOkay = (maxPlayers.getValue() != null) && allFieldsOkay;

        return allFieldsOkay;
    }


    private static void setHBoxPreferences(HBox hbox, Label label, Control field) {
        hbox.setSpacing(10);
        label.setPrefWidth(150);
        field.setPrefWidth(200);
    }

}
