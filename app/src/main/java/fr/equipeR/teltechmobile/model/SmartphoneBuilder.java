package fr.equipeR.teltechmobile.model;

/**
 * Cette classe est responsable de créer un nouveau smartphone.
 *
 * @see Smartphone
 */
public class SmartphoneBuilder {
    private int id;
    private String name;
    private String description;
    private int rating;
    private int year;
    private double priceTax;
    private double priceNoTax;
    private String imageID;
    private String supplierName;
    private int quantity;

    public SmartphoneBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public SmartphoneBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SmartphoneBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public SmartphoneBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public SmartphoneBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    public SmartphoneBuilder setPriceTax(double priceTax) {
        this.priceTax = priceTax;
        return this;
    }

    public SmartphoneBuilder setPriceNoTax(double priceNoTax) {
        this.priceNoTax = priceNoTax;
        return this;
    }

    public SmartphoneBuilder setImageID(String imageID) {
        this.imageID = imageID;
        return this;
    }

    public SmartphoneBuilder setSupplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    public SmartphoneBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public Smartphone createSmartphone() {
        return new Smartphone(id, name, description, rating, year, priceTax, priceNoTax, imageID, supplierName, quantity);
    }
}