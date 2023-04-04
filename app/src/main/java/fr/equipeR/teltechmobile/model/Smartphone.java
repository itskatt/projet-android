package fr.equipeR.teltechmobile.model;

/**
 * Classe qui représente un smartphone.
 * Utiliser la classe {@link SmartphoneBuilder} pour créer des smartphones.
 *
 * @see SmartphoneBuilder
 */
public class Smartphone {
    private final int id;
    private final String name;
    private final String description;
    private final int rating;
    private final int year;
    private final double priceTax;
    private final double priceNoTax;
    private final String imageID;
    private final String supplierName;
    private int quantity;

    protected Smartphone(int id, String name, String description, int rating, int year, double priceTax, double priceNoTax, String imageID, String supplierName, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.year = year;
        this.priceTax = priceTax;
        this.priceNoTax = priceNoTax;
        this.imageID = imageID;
        this.supplierName = supplierName;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public double getPriceTax() {
        return priceTax;
    }

    public double getPriceNoTax() {
        return priceNoTax;
    }

    public String getImageID() {
        return imageID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Smartphone that = (Smartphone) o;

        if (id != that.id) return false;
        if (rating != that.rating) return false;
        if (year != that.year) return false;
        if (Double.compare(that.priceTax, priceTax) != 0) return false;
        if (Double.compare(that.priceNoTax, priceNoTax) != 0) return false;
        if (quantity != that.quantity) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        if (!imageID.equals(that.imageID)) return false;
        return supplierName.equals(that.supplierName);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + rating;
        result = 31 * result + year;
        temp = Double.doubleToLongBits(priceTax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(priceNoTax);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + imageID.hashCode();
        result = 31 * result + supplierName.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
