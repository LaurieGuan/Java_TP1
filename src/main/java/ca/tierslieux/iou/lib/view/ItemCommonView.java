package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.App;
import ca.tierslieux.iou.lib.logic.Regex;
import ca.tierslieux.iou.lib.logic.items.Item;
import ca.tierslieux.iou.lib.logic.items.State;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemCommonView {

    public static VBox view(Item item) {
        Label titleL = new Label("Item d'inventaire");
        Label sectionGenL = new Label("Section générale");

        Label nameL = new Label("Nom:");
        TextField nameF = new TextField(item.getName());
        HBox nameBox = new HBox(nameL, nameF);
        nameF.setEditable(false);
        setHBoxPreferences(nameBox, nameL, nameF);

        Label descLabel = new Label("Courte description:");
        TextField descF = new TextField(item.getDescription());
        HBox descBox = new HBox(descLabel, descF);
        descF.setEditable(false);
        setHBoxPreferences(descBox, descLabel, descF);

        Label priceL = new Label("Prix:");
        TextField priceF = new TextField(item.getFormattedPrice());
        HBox priceBox = new HBox(priceL, priceF);
        priceF.setEditable(false);
        setHBoxPreferences(priceBox, priceL, priceF);

        Label purchaseDateL = new Label("Date d'achat:");
        DatePicker purchaseDateF = new DatePicker(item.getPurchaseDate());
        HBox purchaseDateBox = new HBox(purchaseDateL, purchaseDateF);
        purchaseDateF.setEditable(false);
        setHBoxPreferences(purchaseDateBox, purchaseDateL, purchaseDateF);

        Label receiptPathL = new Label("Image facture:");
        TextField receiptPathF = new TextField(item.getReceipt());
        HBox receiptPathBox = new HBox(receiptPathL, receiptPathF);
        receiptPathF.setEditable(false);
        setHBoxPreferences(receiptPathBox, receiptPathL, receiptPathF);

        Label stateLabel = new Label("État:");
        ComboBox<String> stateComboBox = new ComboBox<>();
        stateComboBox.getItems().add(item.getStatusString());
        stateComboBox.setValue(item.getStatusString());
        stateComboBox.setEditable(false);
        HBox stateBox = new HBox(stateLabel, stateComboBox);
        setHBoxPreferences(stateBox, stateLabel, stateComboBox);

        Label locationL = new Label("Emplacement:");
        TextField locationF = new TextField(item.getLocation());
        HBox locationBox = new HBox(locationL, locationF);
        locationF.setEditable(false);
        setHBoxPreferences(locationBox, locationL, locationF);

        VBox vbox = new VBox(titleL, sectionGenL, nameBox, descBox, priceBox, purchaseDateBox, receiptPathBox, stateBox, locationBox);

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        return vbox;
    }

    public static VBox add() {
        Label titleLabel = new Label("Nouvel item d'inventaire");

        Label generalSectionLabel = new Label("Section générale");

        Label nameLabel = new Label("Nom:");
        TextField nameF = new TextField();
        HBox nameBox = new HBox(nameLabel, nameF);
        setHBoxPreferences(nameBox, nameLabel, nameF);
        nameF.setId("name");

        Label descLabel = new Label("Courte description:");
        TextField descF = new TextField();
        HBox descBox = new HBox(descLabel, descF);
        setHBoxPreferences(descBox, descLabel, descF);
        descF.setId("description");

        Label priceLabel = new Label("Prix:");
        TextField priceF = new TextField();
        HBox priceBox = new HBox(priceLabel, priceF);
        setHBoxPreferences(priceBox, priceLabel, priceF);
        priceF.setId("price");

        Label quantityLabel = new Label("Quantité:");
        Spinner<Integer> quantityF = new Spinner<Integer>();
        HBox quantityBox = new HBox(quantityLabel, quantityF);
        setHBoxPreferences(quantityBox, quantityLabel, quantityF);
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1);
        quantityF.setValueFactory(valueFactory);
        quantityF.setId("numberToAdd");

        Label purchaseDateLabel = new Label("Date d'achat:");
        DatePicker purchaseDatePicker = new DatePicker();
        HBox purchaseDateBox = new HBox(purchaseDateLabel, purchaseDatePicker);
        setHBoxPreferences(purchaseDateBox, purchaseDateLabel, purchaseDatePicker);
        purchaseDatePicker.setId("datePicker");

        Label receiptImageLabel = new Label("Image facture:");
        TextField receiptImageF = new TextField();
        HBox receiptImageBox = new HBox(receiptImageLabel, receiptImageF);
        setHBoxPreferences(receiptImageBox, receiptImageLabel, receiptImageF);
        receiptImageF.setId("imageReceipt");

        Label stateLabel = new Label("État:");
        ComboBox<String> stateComboBox = new ComboBox<>();
        stateComboBox.getItems().addAll(State.getStatusString(State.STORAGE),
                State.getStatusString(State.STOLEN),
                State.getStatusString(State.LENT),
                State.getStatusString(State.BROKEN));
        HBox stateBox = new HBox(stateLabel, stateComboBox);
        setHBoxPreferences(stateBox, stateLabel, stateComboBox);
        stateComboBox.setId("stateBox");

        Label locationLabel = new Label("Emplacement:");
        TextField locationF = new TextField();
        HBox locationBox = new HBox(locationLabel, locationF);
        setHBoxPreferences(locationBox, locationLabel, locationF);
        locationF.setId("location");

        VBox vbox = new VBox(
                titleLabel,
                generalSectionLabel,
                nameBox, descBox, priceBox, quantityBox, purchaseDateBox, receiptImageBox, stateBox, locationBox
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        setVerifyPrice(priceF);

        return vbox;
    }

    public static VBox modify(Item item) {
        Label titleLabel = new Label("Modifier item d'inventaire");

        Label generalSectionLabel = new Label("Section générale");

        Label nameLabel = new Label("Nom:");
        TextField nameField = new TextField(item.getName());
        HBox nameBox = new HBox(nameLabel, nameField);
        setHBoxPreferences(nameBox, nameLabel, nameField);
        nameField.setId("name");

        Label descLabel = new Label("Courte description:");
        TextField descField = new TextField(item.getDescription());
        HBox descBox = new HBox(descLabel, descField);
        setHBoxPreferences(descBox, descLabel, descField);
        descField.setId("description");

        Label priceLabel = new Label("Prix:");
        TextField priceField = new TextField(item.getFormattedPrice());
        HBox priceBox = new HBox(priceLabel, priceField);
        setHBoxPreferences(priceBox, priceLabel, priceField);
        priceField.setId("price");

        Label purchaseDateLabel = new Label("Date d'achat:");
        DatePicker purchaseDatePicker = new DatePicker(item.getPurchaseDate());
        HBox purchaseDateBox = new HBox(purchaseDateLabel, purchaseDatePicker);
        setHBoxPreferences(purchaseDateBox, purchaseDateLabel, purchaseDatePicker);
        purchaseDatePicker.setId("datePicker");

        Label receiptImageLabel = new Label("Image facture:");
        TextField receiptImageField = new TextField(item.getReceipt());
        HBox receiptImageBox = new HBox(receiptImageLabel, receiptImageField);
        setHBoxPreferences(receiptImageBox, receiptImageLabel, receiptImageField);
        receiptImageField.setId("imageReceipt");


        Label stateLabel = new Label("État:");
        ComboBox<String> stateComboBox = new ComboBox<>();
        stateComboBox.getItems().addAll(
                State.getStatusString(State.STORAGE),
                State.getStatusString(State.STOLEN),
                State.getStatusString(State.LENT),
                State.getStatusString(State.BROKEN)
        );
        stateComboBox.setValue(item.getStatusString());
        HBox stateBox = new HBox(stateLabel, stateComboBox);
        setHBoxPreferences(stateBox, stateLabel, stateComboBox);
        stateComboBox.setId("stateBox");

        Label locationLabel = new Label("Emplacement:");
        TextField locationField = new TextField(item.getLocation());
        HBox locationBox = new HBox(locationLabel, locationField);
        setHBoxPreferences(locationBox, locationLabel, locationField);
        locationField.setId("location");

        setVerifyPrice(priceField);

        VBox vbox = new VBox(
                titleLabel,
                generalSectionLabel,
                nameBox, descBox, priceBox, purchaseDateBox, receiptImageBox, stateBox, locationBox
        );

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        return vbox;
    }

    private static void setVerifyPrice(TextField priceField) {
        priceField.setOnAction(event -> {

            if (!priceField.getText().isEmpty()) {
                String input = priceField.getText();
                if (!Regex.matchesPattern(input,"^\\b(\\d+)(,|\\.)?(\\d{0,2})\\$?$")) {

                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("Champ incorrect");
                    errorAlert.setContentText("Le format du prix est incorrect (##,##$)");
                    errorAlert.showAndWait();
                }
            }
        });
    }

    public static boolean verifyCommonItemInput() {
        boolean allFieldsOkay = true;

        TextField name = (TextField) App.getMainScene().lookup("#name");
        allFieldsOkay = (!name.getText().isEmpty()) && allFieldsOkay;

        TextField description = (TextField) App.getMainScene().lookup("#description");
        allFieldsOkay = (!description.getText().isEmpty()) && allFieldsOkay;

        TextField price = (TextField) App.getMainScene().lookup("#price");
        allFieldsOkay = (!price.getText().isEmpty()) && allFieldsOkay;

        DatePicker date = (DatePicker) App.getMainScene().lookup("#datePicker");
        allFieldsOkay = (date.getValue() != null)&& allFieldsOkay;

        TextField imageReceipt = (TextField) App.getMainScene().lookup("#imageReceipt");
        allFieldsOkay = (!imageReceipt.getText().isEmpty())&& allFieldsOkay;

        TextField location = (TextField) App.getMainScene().lookup("#location");
        allFieldsOkay = (!location.getText().isEmpty())&& allFieldsOkay;

        ComboBox state = (ComboBox) App.getMainScene().lookup("#stateBox");
        allFieldsOkay = (state.getValue() != null)&& allFieldsOkay;

        return allFieldsOkay;
    }

    private static void setHBoxPreferences(HBox hbox, Label label, Control field) {
        hbox.setSpacing(10);
        label.setPrefWidth(150);
        field.setPrefWidth(200);
    }
}