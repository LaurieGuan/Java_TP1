package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Book;
import javafx.geometry.Insets;
import javafx.scene.control.*;
        import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookView {

    public static VBox view(Book book, VBox vBox) {
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
        isbnField.setDisable(true);

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

        vBox.getChildren().addAll(generalSection, vbox);
        return vBox;
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

    public static VBox modify(Book book, VBox vBox) {
        VBox generalSection = ItemCommonView.modify(book);
        Label bookSectionLabel = new Label("Modifier livre");

        Label authorLabel = new Label("Auteur:");
        TextField authorField = new TextField(book.getAuthor());
        HBox authorBox = new HBox(authorLabel, authorField);
        setHBoxPreferences(authorBox, authorLabel, authorField);

        Label publisherLabel = new Label("Éditeur:");
        TextField publisherField = new TextField(book.getPublisher());
        HBox publisherBox = new HBox(publisherLabel, publisherField);
        setHBoxPreferences(publisherBox, publisherLabel, publisherField);

        Label publishedYearLabel = new Label("Année de publication:");
        Spinner<Integer> publishedYearField = new Spinner<>();
        HBox publishedYearBox = new HBox(publishedYearLabel, publishedYearField);
        setHBoxPreferences(publishedYearBox, publishedYearLabel, publishedYearField);
        SpinnerValueFactory<Integer> valueYearFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1800, 2100, book.getPublishedYear());
        publishedYearField.setValueFactory(valueYearFactory);

        Label isbnLabel = new Label("ISBN:");
        TextField isbnField = new TextField(book.getIsbn());
        HBox isbnBox = new HBox(isbnLabel, isbnField);
        setHBoxPreferences(isbnBox, isbnLabel, isbnField);

        Button saveButton = new Button("Enregistrer");
        HBox buttonBox = new HBox(saveButton);
        buttonBox.setSpacing(10);

        VBox vbox = new VBox(
                bookSectionLabel,
                authorBox, publisherBox, publishedYearBox, isbnBox,
                buttonBox
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        vBox.getChildren().clear();
        vBox.getChildren().addAll(generalSection, vbox);

        return vBox;
    }

    private static void setHBoxPreferences(HBox hbox, Label label, Control field) {
        hbox.setSpacing(10);
        label.setPrefWidth(150);
        field.setPrefWidth(200);
    }
}
