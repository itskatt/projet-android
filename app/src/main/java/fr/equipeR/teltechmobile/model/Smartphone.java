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
    private final int quantity;

    /**
     * Constructeur de la classe Smartphone.
     * Ce constructeur est protégé pour empêcher les instances de Smartphone
     * d'être créées directement en dehors de cette classe et de ses sous-classes.
     *
     * @param id           L'ID du smartphone.
     * @param name         Le nom du smartphone.
     * @param description  La description du smartphone.
     * @param rating       La note du smartphone.
     * @param year         L'année de sortie du smartphone.
     * @param priceTax     Le prix du smartphone avec taxe.
     * @param priceNoTax   Le prix du smartphone sans taxe.
     * @param imageID      L'ID de l'image du smartphone.
     * @param supplierName Le nom du fournisseur du smartphone.
     * @param quantity     La quantité de smartphones en stock.
     */
    protected Smartphone(int id, String name, String description, int rating, int year, double priceTax,
                         double priceNoTax, String imageID, String supplierName, int quantity) {
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

    /**
     * Retourne l'ID du smartphone.
     *
     * @return L'ID du smartphone.
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le nom du smartphone.
     *
     * @return Le nom du smartphone.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne la description du smartphone.
     *
     * @return La description du smartphone.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne la note du smartphone.
     *
     * @return La note du smartphone.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Retourne l'année de sortie du smartphone.
     *
     * @return L'année de sortie du smartphone.
     */
    public int getYear() {
        return year;
    }

    /**
     * Renvoie le prix avec taxe.
     *
     * @return le prix avec taxe.
     */
    public double getPriceTax() {
        return priceTax;
    }

    /**
     * Renvoie le prix sans taxe.
     *
     * @return le prix sans taxe.
     */
    public double getPriceNoTax() {
        return priceNoTax;
    }

    /**
     * Renvoie l'identifiant de l'image.
     *
     * @return l'identifiant de l'image.
     */
    public String getImageID() {
        return imageID;
    }

    /**
     * Renvoie le nom du fournisseur.
     *
     * @return le nom du fournisseur.
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Renvoie la quantité.
     *
     * @return la quantité.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Compare cet objet Smartphone à un autre objet pour vérifier s'ils sont égaux.
     *
     * @param o l'objet à comparer.
     * @return true si les objets sont égaux, false sinon.
     */
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

    /**
     * Calcule le code de hachage pour cet objet Smartphone.
     *
     * @return le code de hachage pour cet objet Smartphone.
     */
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

}
