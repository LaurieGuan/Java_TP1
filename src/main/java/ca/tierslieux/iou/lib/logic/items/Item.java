package ca.tierslieux.iou.lib.logic.items;

import ca.tierslieux.iou.lib.logic.file.Json;

import java.time.LocalDate;

public abstract class Item /*implements Json */{
    private String name;
    private String description;
    private int price;
    private LocalDate purchaseDate;
    private String receipt;
    private String location;
    private State status;

    Item(String name, String description, int price, LocalDate purchaseDate, String pathToReceipt, String location, State status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.purchaseDate = purchaseDate;
        this.receipt = pathToReceipt;
        this.location = location;
        this.status = status;
    }

    public abstract String toJson();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return (float) price / 100f;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
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
}