package fr.equipeR.teltechmobile.model;

/**
 * Cette classe est responsable de créer un nouveau smartphone en utilisant le pattern Builder.
 * <p>
 * Elle permet de créer un objet Smartphone étape par étape, en définissant chaque
 * attribut individuellement.
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

    /**
     * Cette méthode permet de définir l'id du smartphone.
     *
     * @param id L'id à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setId(int id) {
        this.id = id;
        return this;
    }

    /**
     * Cette méthode permet de définir le nom du smartphone.
     *
     * @param name Le nom à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Cette méthode permet de définir la description du smartphone.
     *
     * @param description La description à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Cette méthode permet de définir la note du smartphone.
     *
     * @param rating La note à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setRating(int rating) {
        this.rating = rating;
        return this;
    }

    /**
     * Cette méthode permet de définir l'année de sortie du smartphone.
     *
     * @param year L'année à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * Cette méthode permet de définir le prix avec taxe du smartphone.
     *
     * @param priceTax Le prix avec taxe à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setPriceTax(double priceTax) {
        this.priceTax = priceTax;
        return this;
    }

    /**
     * Cette méthode permet de définir le prix sans taxe du smartphone.
     *
     * @param priceNoTax Le prix sans taxe à définir pour le smartphone.
     * @return L'instance courante de SmartphoneBuilder pour permettre l'appel en cascade de
     * méthodes.
     */
    public SmartphoneBuilder setPriceNoTax(double priceNoTax) {
        this.priceNoTax = priceNoTax;
        return this;
    }

    /**
     * Définit l'ID de l'image pour le smartphone en cours de création.
     *
     * @param imageID l'ID de l'image pour le smartphone en cours de création
     * @return le SmartphoneBuilder en cours avec l'ID de l'image défini
     */
    public SmartphoneBuilder setImageID(String imageID) {
        this.imageID = imageID;
        return this;
    }

    /**
     * Définit le nom du fournisseur pour le smartphone en cours de création.
     *
     * @param supplierName le nom du fournisseur pour le smartphone en cours de création
     * @return le SmartphoneBuilder en cours avec le nom du fournisseur défini
     */
    public SmartphoneBuilder setSupplierName(String supplierName) {
        this.supplierName = supplierName;
        return this;
    }

    /**
     * Définit la quantité de stock pour le smartphone en cours de création.
     *
     * @param quantity la quantité de stock pour le smartphone en cours de création
     * @return le SmartphoneBuilder en cours avec la quantité de stock définie
     */
    public SmartphoneBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Crée et retourne un nouvel objet Smartphone à partir des informations
     * fournies au SmartphoneBuilder en cours.
     *
     * @return le nouvel objet Smartphone créé avec les informations du SmartphoneBuilder en cours
     */
    public Smartphone createSmartphone() {
        return new Smartphone(
                id,
                name,
                description,
                rating,
                year,
                priceTax,
                priceNoTax,
                imageID,
                supplierName,
                quantity
        );
    }
}