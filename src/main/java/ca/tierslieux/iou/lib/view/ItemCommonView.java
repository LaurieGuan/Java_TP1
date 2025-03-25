package ca.tierslieux.iou.lib.view;

import ca.tierslieux.iou.lib.logic.items.Item;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ItemCommonView {

    public static VBox view(Item item) {
        Label titleL = new Label("Item d'inventaire");
        Label sectionGenL = new Label("Section générale");

        Label nameL = new Label("Nom:");
        TextField nameF = new TextField(item.getName());
        HBox nameBox = new HBox(nameL, nameF);
        nameL.prefWidthProperty().bind(nameBox.widthProperty().multiply(0.5));
        nameF.prefWidthProperty().bind(nameBox.widthProperty().multiply(0.5));
        nameF.setEditable(false);

        Label priceL = new Label("Prix:");
        TextField priceF = new TextField(item.getFormattedPrice());
        HBox priceBox = new HBox(priceL, priceF);
        priceL.prefWidthProperty().bind(priceBox.widthProperty().multiply(0.5));
        priceF.prefWidthProperty().bind(priceBox.widthProperty().multiply(0.5));
        priceF.setEditable(false);

        Label purchaseDateL = new Label("Date d'achat:");
        DatePicker purchaseDateF = new DatePicker(item.getPurchaseDate());
        HBox purchaseDateBox = new HBox(purchaseDateL, purchaseDateF);
        purchaseDateL.prefWidthProperty().bind(purchaseDateBox.widthProperty().multiply(0.5));
        purchaseDateF.prefWidthProperty().bind(purchaseDateBox.widthProperty().multiply(0.5));
        purchaseDateF.setEditable(false);

        Label receiptPathL = new Label("Image facture:");
        TextField receiptPathF = new TextField(item.getReceipt());
        HBox receiptPathBox = new HBox(receiptPathL, receiptPathF);
        receiptPathL.prefWidthProperty().bind(receiptPathBox.widthProperty().multiply(0.5));
        receiptPathF.prefWidthProperty().bind(receiptPathBox.widthProperty().multiply(0.5));
        receiptPathF.setEditable(false);

        Label stateL = new Label("État:");
        TextField stateF = new TextField(item.getStatusString());
        HBox stateBox = new HBox(stateL, stateF);
        stateL.prefWidthProperty().bind(stateBox.widthProperty().multiply(0.5));
        stateF.prefWidthProperty().bind(stateBox.widthProperty().multiply(0.5));
        stateF.setEditable(false);

        Label locationL = new Label("Emplacement:");
        TextField locationF = new TextField(item.getLocation());
        HBox locationBox = new HBox(locationL, locationF);
        locationL.prefWidthProperty().bind(locationBox.widthProperty().multiply(0.5));
        locationF.prefWidthProperty().bind(locationBox.widthProperty().multiply(0.5));
        locationF.setEditable(false);

        return new VBox(titleL, sectionGenL, nameBox, priceBox, purchaseDateBox, receiptPathBox, stateBox, locationBox);
    }
}
