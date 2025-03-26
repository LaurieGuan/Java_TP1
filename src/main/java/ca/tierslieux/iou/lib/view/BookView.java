package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.App;
import ca.tierslieux.iou.lib.logic.Regex;
import ca.tierslieux.iou.lib.logic.items.Book;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.State;
import ca.tierslieux.iou.lib.logic.list.Inventory;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.regex.Pattern;

public class BookView {

    public static void view(Book book, VBox vBox) {
        VBox generalSection = ItemCommonView.view(book);
        Label bookSectionLabel = new Label("Section livre");

        Label authorLabel = new Label("Auteur:");
        TextField authorField = new TextField(book.getAuthor());
        HBox authorBox = new HBox(authorLabel, authorField);
        setHBoxPreferences(authorBox, authorLabel, authorField);
        authorField.setEditable(false);

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField(book.getPublisher());
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);
        publisherField.setEditable(false);

        Label publishedYearLabel = new Label("Année de publication:");
        TextField publishedYearField = new TextField(String.valueOf(book.getPublishedYear()));
        HBox publishedYearBox = new HBox(publishedYearLabel, publishedYearField);
        setHBoxPreferences(publishedYearBox, publishedYearLabel, publishedYearField);
        publishedYearField.setEditable(false);

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField(book.getIsbn());
        HBox isbnBox = new HBox(isbnLabel, isbnField);
        setHBoxPreferences(isbnBox, isbnLabel, isbnField);
        isbnField.setEditable(true);

        Button modButton = new Button("Modifier");
        HBox buttonBox = new HBox(modButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                bookSectionLabel,
                authorBox, publisherBox, publishedYearBox, isbnBox,
                modButton
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

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

        vBox.getChildren().addAll(generalSection, vbox);
    }

    public static void add(VBox vBox) {
        VBox generalSection = ItemCommonView.add();

        Label bookSectionLabel = new Label("Section livre");

        Label authorLabel = new Label("Auteur:");
        TextField authorField = new TextField();
        HBox authorBox = new HBox(authorLabel, authorField);
        setHBoxPreferences(authorBox, authorLabel, authorField);

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField();
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);

        Label publishedYearLabel = new Label("Année de publication:");
        Spinner<Integer> publishedYearField = new Spinner<>();
        HBox publishedYearBox = new HBox(publishedYearLabel, publishedYearField);
        setHBoxPreferences(publishedYearBox, publishedYearLabel, publishedYearField);
        SpinnerValueFactory<Integer> valueYearFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1800, 2100, 2023);
        publishedYearField.setValueFactory(valueYearFactory);

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField();
        HBox isbnBox = new HBox(isbnLabel, isbnField);
        setHBoxPreferences(isbnBox, isbnLabel, isbnField);

        Button addButton = new Button("Ajouter");
        Button clearButton = new Button("Effacer");
        HBox buttonBox = new HBox(addButton, clearButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                bookSectionLabel,
                authorBox, publisherBox, publishedYearBox, isbnBox,
                buttonBox
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().addAll(generalSection, vbox);
    }

    public static void modify(Book book, VBox vBox) {
        VBox generalSection = ItemCommonView.modify(book);
        Label bookSectionLabel = new Label("Modifier livre");

        Label authorLabel = new Label("Auteur:");
        TextField authorField = new TextField(book.getAuthor());
        HBox authorBox = new HBox(authorLabel, authorField);
        setHBoxPreferences(authorBox, authorLabel, authorField);
        authorField.setId("author");

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField(book.getPublisher());
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);
        publisherField.setId("publisher");

        Label publishedYearLabel = new Label("Année de publication:");
        Spinner<Integer> publishedYearField = new Spinner<>();
        HBox publishedYearBox = new HBox(publishedYearLabel, publishedYearField);
        setHBoxPreferences(publishedYearBox, publishedYearLabel, publishedYearField);
        SpinnerValueFactory<Integer> valueYearFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1800, 2100, book.getPublishedYear());
        publishedYearField.setValueFactory(valueYearFactory);
        publishedYearBox.setId("year");

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField(book.getIsbn());
        HBox isbnBox = new HBox(isbnLabel, isbnField);
        setHBoxPreferences(isbnBox, isbnLabel, isbnField);
        isbnField.setId("isbn");

        Button saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                bookSectionLabel,
                authorBox, publisherBox, publishedYearBox, isbnBox,
                buttonBox
        );

        saveButton.setOnAction(actionEvent -> {
            if (verifyCommonItemInput() && verifySpecialInput()) {
                TextField name = (TextField) App.getMainScene().lookup("#name");
                TextField description = (TextField) App.getMainScene().lookup("#description");
                TextField price = (TextField) App.getMainScene().lookup("#price");
                DatePicker date = (DatePicker) App.getMainScene().lookup("#datePicker");
                TextField imageReceipt = (TextField) App.getMainScene().lookup("#imageReceipt");
                TextField location = (TextField) App.getMainScene().lookup("#location");
                TextField state = (TextField) App.getMainScene().lookup("#stateBox");


                State status =  switch (state.getText()) {
                    case "Volé" -> State.STOLEN;
                    case "En ma possession" -> State.STORAGE;
                    case "Prêté" -> State.LENT;
                    case "Brisé" -> State.BROKEN;
                };

                book.setName(name.getText());
                book.setDescription(description.getText());
                book.setFormattedPrice(price.getText());
                book.setPurchaseDate(date.getValue());
                book.setPathToReceipt(imageReceipt.getText());
                book.setLocation(location.getText());
                book.setStatus(status);

            }
        });

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().clear();
        vBox.getChildren().addAll(generalSection, vbox);
    }

    private static boolean verifySpecialInput() {
        return true;
    }

    private static boolean verifyCommonItemInput() {
        boolean allFieldsOkay = true;

        TextField name = (TextField) App.getMainScene().lookup("#name");
        allFieldsOkay = (name.getText() != null && name.getText().isBlank()) && allFieldsOkay;

        TextField description = (TextField) App.getMainScene().lookup("#description");
        allFieldsOkay = (description.getText() != null && name.getText().isBlank()) && allFieldsOkay;

        TextField price = (TextField) App.getMainScene().lookup("#price");
        allFieldsOkay = (price.getText() != null && name.getText().isBlank()) && allFieldsOkay;

        DatePicker date = (DatePicker) App.getMainScene().lookup("#datePicker");
        allFieldsOkay = (date.getValue() != null) && allFieldsOkay;

        TextField imageReceipt = (TextField) App.getMainScene().lookup("#imageReceipt");
        allFieldsOkay = (imageReceipt.getText() != null && name.getText().isBlank()) && allFieldsOkay;

        TextField location = (TextField) App.getMainScene().lookup("#location");
        allFieldsOkay = (location.getText() != null && name.getText().isBlank()) && allFieldsOkay;

        TextField state = (TextField) App.getMainScene().lookup("#stateBox");
        allFieldsOkay = (state.getText() != null && name.getText().isBlank()) && allFieldsOkay;

        return allFieldsOkay;
    }


    private static void setHBoxPreferences(HBox hbox, Label label, Control field) {
        hbox.setSpacing(10);
        label.setPrefWidth(150);
        field.setPrefWidth(200);
    }
}
