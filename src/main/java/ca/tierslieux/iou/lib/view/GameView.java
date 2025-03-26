package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Book;
import ca.tierslieux.iou.lib.logic.items.Game;
import ca.tierslieux.iou.lib.logic.items.Item;
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

        Label minimumAgeLabel = new Label("Âge minimum:");
        Spinner<Integer> minimumAgeField = new Spinner<Integer>();
        HBox minimumAgeBox = new HBox(minimumAgeLabel, minimumAgeField);
        setHBoxPreferences(minimumAgeBox, minimumAgeLabel, minimumAgeField);
        SpinnerValueFactory<Integer> valueAgeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        minimumAgeField.setValueFactory(valueAgeFactory);

        Label minNumberPlayersLabel = new Label("Nombre minimum de joueurs:");
        Spinner<Integer> minNumberPlayersField = new Spinner<Integer>();
        HBox minNumberPlayersBox = new HBox(minNumberPlayersLabel, minNumberPlayersField);
        setHBoxPreferences(minNumberPlayersBox, minNumberPlayersLabel, minNumberPlayersField);
        SpinnerValueFactory<Integer> valueMinPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        minNumberPlayersField.setValueFactory(valueMinPlayerFactory);

        Label maxNumberPlayersLabel = new Label("Nombre maximum de joueurs:");
        Spinner<Integer> maxNumberPlayersField = new Spinner<Integer>();
        HBox maxNumberPlayersBox = new HBox(maxNumberPlayersLabel, maxNumberPlayersField);
        setHBoxPreferences(maxNumberPlayersBox, maxNumberPlayersLabel, maxNumberPlayersField);
        SpinnerValueFactory<Integer> valueMaxPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        maxNumberPlayersField.setValueFactory(valueMaxPlayerFactory);

        Button addButton = new Button("Ajouter");
        Button clearButton = new Button("Effacer");
        HBox buttonBox = new HBox(addButton, clearButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                gameSectionLabel,
                publisherBox, minimumAgeBox, minNumberPlayersBox,maxNumberPlayersBox,
                buttonBox
        );

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

        Label minimumAgeLabel = new Label("Âge minimum:");
        Spinner<Integer> minimumAgeField = new Spinner<>();
        HBox minimumAgeBox = new HBox(minimumAgeLabel, minimumAgeField);
        setHBoxPreferences(minimumAgeBox, minimumAgeLabel, minimumAgeField);
        SpinnerValueFactory<Integer> valueAgeFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, game.getMinimumAge());
        minimumAgeField.setValueFactory(valueAgeFactory);
        minimumAgeField.getValueFactory().setValue(game.getMinimumAge());

        Label minNumberPlayersLabel = new Label("Nombre minimum de joueurs:");
        Spinner<Integer> minNumberPlayersField = new Spinner<>();
        HBox minNumberPlayersBox = new HBox(minNumberPlayersLabel, minNumberPlayersField);
        setHBoxPreferences(minNumberPlayersBox, minNumberPlayersLabel, minNumberPlayersField);
        SpinnerValueFactory<Integer> valueMinPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, game.getMinNumberPlayers());
        minNumberPlayersField.setValueFactory(valueMinPlayerFactory);
        minNumberPlayersField.getValueFactory().setValue(game.getMinNumberPlayers());

        Label maxNumberPlayersLabel = new Label("Nombre maximum de joueurs:");
        Spinner<Integer> maxNumberPlayersField = new Spinner<>();
        HBox maxNumberPlayersBox = new HBox(maxNumberPlayersLabel, maxNumberPlayersField);
        setHBoxPreferences(maxNumberPlayersBox, maxNumberPlayersLabel, maxNumberPlayersField);
        SpinnerValueFactory<Integer> valueMaxPlayerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, game.getMaxNumberPlayers());
        maxNumberPlayersField.setValueFactory(valueMaxPlayerFactory);
        maxNumberPlayersField.getValueFactory().setValue(game.getMaxNumberPlayers());


        Button saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                gameSectionLabel,
                publisherBox, minimumAgeBox, minNumberPlayersBox, maxNumberPlayersBox,
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
