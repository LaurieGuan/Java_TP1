package ca.tierslieux.iou.lib.logic.items;

import ca.tierslieux.iou.lib.logic.Regex;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Book extends Item {
    private String author;
    private String publisher;
    private int publishedYear;
    private String isbn;
    private String pathToImage;
    private Image bookCover = null;
    private boolean imageFound;

    public Book(String name, String description, int price, LocalDate purchaseDate,
                String pathToReceipt, String location, State status,
                String author, String publisher, int publishedYear, String isbn, String pathToImage) {

        super(name, description, price, purchaseDate, pathToReceipt, location, status);
        this.author = author;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
        this.pathToImage = pathToImage;

        FileInputStream rawImage = null;
        try {
            rawImage = new FileInputStream(pathToImage);
            this.bookCover = new Image(rawImage);
            this.imageFound = true;
        } catch (FileNotFoundException e) {
            this.imageFound = false;
        }
    }

    @Override
    public String toJson() {
        String space = "        ";
        return String.format("      \"Book\": {\n" +
                        space + "\"name\": \"%s\",\n" +
                        space + "\"description\": \"%s\",\n" +
                        space + "\"price\": %s,\n" +
                        space + "\"purchaseDate\": \"%s\",\n" +
                        space + "\"receipt\": \"%s\",\n" +
                        space + "\"location\": \"%s\",\n" +
                        space + "\"status\": \"%s\",\n" +
                        space + "\"author\": \"%s\",\n" +
                        space + "\"publisher\": \"%s\",\n" +
                        space + "\"publishedYear\": %d,\n" +
                        space + "\"isbn\": \"%s\",\n" +
                        space + "\"pathToImage\": \"%s\"\n" +
                        "      }\n",
                name, description, price, purchaseDate.toString(), receipt, location, status, author, publisher, publishedYear, isbn, pathToImage);
    }

    public String toCsv() {
        return String.format("Book;%s;%s;%s;%s;%s;%s;%s;%s;%s;%d;%s;%s",
                name, description, getFormattedPrice(), purchaseDate.toString(), receipt, location, status.toString(),
                author, publisher, publishedYear, isbn, pathToImage);
    }

    public static Book fromJson(String json) {
        String tempName = Regex.attributeMatch(json, "name", Regex.ATTRIBUTE_MODE.STRING);
        String tempDescription = Regex.attributeMatch(json, "description", Regex.ATTRIBUTE_MODE.STRING);

        String tempPriceString = Regex.attributeMatch(json, "price", Regex.ATTRIBUTE_MODE.NUMBER);
        int tempPrice = Integer.parseInt(tempPriceString);

        String tempPurchaseDateString = Regex.attributeMatch(json, "purchaseDate", Regex.ATTRIBUTE_MODE.STRING);
        LocalDate tempPurchaseDate = LocalDate.parse(tempPurchaseDateString, DateTimeFormatter.ISO_LOCAL_DATE);
        String tempReceipt = Regex.attributeMatch(json, "receipt", Regex.ATTRIBUTE_MODE.STRING);
        String tempLocation = Regex.attributeMatch(json, "location", Regex.ATTRIBUTE_MODE.STRING);

        String tempStatusString = Regex.attributeMatch(json, "status", Regex.ATTRIBUTE_MODE.STRING);
        State tempStatus = null;
        switch (tempStatusString) {
            case "STORAGE":
                tempStatus = State.STORAGE;
                break;
            case "STOLEN":
                tempStatus = State.STOLEN;
                break;
            case "LENT":
                tempStatus = State.LENT;
                break;
            case "BROKEN":
                tempStatus = State.BROKEN;
                break;
        }

        String tempAuthor = Regex.attributeMatch(json, "author", Regex.ATTRIBUTE_MODE.STRING);
        String tempPublisher = Regex.attributeMatch(json, "publisher", Regex.ATTRIBUTE_MODE.STRING);
        String tempPublishedYearString = Regex.attributeMatch(json, "publishedYear", Regex.ATTRIBUTE_MODE.NUMBER);
        int tempPublishedYear = Integer.parseInt(tempPublishedYearString);
        String tempIsbn = Regex.attributeMatch(json, "isbn", Regex.ATTRIBUTE_MODE.STRING);
        String tempPathToImage = Regex.attributeMatch(json, "pathToImage", Regex.ATTRIBUTE_MODE.STRING);

        return new Book(tempName, tempDescription, tempPrice, tempPurchaseDate, tempReceipt, tempLocation,
                tempStatus, tempAuthor, tempPublisher, tempPublishedYear, tempIsbn, tempPathToImage);
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public Type getType() {
        return Type.BOOK;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}