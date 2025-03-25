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
        return State.getStatusString(status);
    }
    public abstract Type getType();


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPathToReceipt(String pathToReceipt) {
        this.receipt = receipt;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(State status) {
        this.status = status;
    }
}