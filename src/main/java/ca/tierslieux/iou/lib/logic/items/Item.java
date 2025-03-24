package ca.tierslieux.iou.lib.logic.items;

import ca.tierslieux.iou.lib.logic.file.Json;

import java.time.LocalDate;

public abstract class Item implements Json {
    protected String name;
    protected String description;
    protected int price;
    protected LocalDate purchaseDate;
    protected String receipt;
    protected String location;
    protected State status;

    public Item(String name, String description, int price, LocalDate purchaseDate, String pathToReceipt, String location, State status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.receipt = pathToReceipt;
        this.location = location;
        this.status = status;
    }

    public abstract String toJson();
    public abstract String toCsv();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return (float) price / 100f;
    }

    public String getFormattedPrice() {
        String value = Integer.toString(price);
        String prefix = value.substring(0, value.length() - 2);
        String suffix = value.substring(value.length() - 2);
        return String.format("%s,%s$", prefix, suffix);
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public String getPurchaseDateString() {
        return purchaseDate.toString();
    }

    public String getReceipt() {
        return receipt;
    }

    public String getLocation() {
        return location;
    }

    public State getStatus() {
        return status;
    }

    public String getStatusString() {
        return switch (status) {
            case STOLEN -> "Volé";
            case STORAGE -> "En ma possession";
            case LENT -> "Prêté";
            case BROKEN -> "Brisé";
        };
    }

    public abstract Type getType();
}