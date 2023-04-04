package fr.equipeR.teltechmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import fr.equipeR.teltechmobile.model.Smartphone;
import fr.equipeR.teltechmobile.model.SmartphoneList;

/**

 * Activité qui affiche les détails d'un smartphone sélectionné et permet de l'ajouter au panier
 */
public class EcranSmartphone extends ShakeableActivity {

    /**

     * Cette méthode est appelée lorsque l'activité est créée. Elle récupère l'intent passé pour
     * lancer l'activité et récupère les données du smartphone correspondant à l'identifiant passé en
     * extra. Elle remplit ensuite les éléments graphiques de l'interface avec les données du smartphone.
     * Elle configure le bouton pour ajouter le smartphone au panier.
     *
     * @param savedInstanceState L'état de l'instance précédente de cette activité, s'il y en a un.
     */
    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_smartphone);

        // Récupération de l'intent et du smartphone correspondant à l'identifiant passé en extra
        Intent intent = getIntent();
        if (intent == null) return;

        Smartphone smartphone = SmartphoneList.getInstance()
                .getById(intent.getExtras().getInt(getString(R.string.smartphone_id_key)));

        // Récupération des éléments graphiques de l'interface
        TextView phoneName = findViewById(R.id.phoneName);
        TextView brandYear = findViewById(R.id.brandYear);
        ImageView phoneImage = findViewById(R.id.phoneImage);
        TextView phoneDescription = findViewById(R.id.phoneDescription);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView phonePriceHT = findViewById(R.id.phonePriceHT);
        TextView phonePriceTTC = findViewById(R.id.phonePriceTTC);
        TextView phoneStock = findViewById(R.id.phoneStock);

        // Remplissage des éléments graphiques avec les données du smartphone
        phoneName.setText(smartphone.getName());
        brandYear.setText(smartphone.getYear() + " - " + smartphone.getSupplierName());

        String imageUrl = getString(R.string.smartphone_image_api_endpoint) + smartphone.getImageID();
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(phoneImage);

        phoneDescription.setText(smartphone.getDescription());
        ratingBar.setRating(smartphone.getRating());
        phonePriceHT.setText("Prix HT : " + Math.round(smartphone.getPriceNoTax() * 100d) / 100d + " €");
        phonePriceTTC.setText("Prix TTC : " + smartphone.getPriceTax() + " €");
        phoneStock.setText("En stock : " + smartphone.getQuantity());

        // Configuration du bouton pour ajouter le smartphone au panier
        Button buttonAddToCart = findViewById(R.id.buttonAddToCart);
        buttonAddToCart.setOnClickListener(this::addSmartphoneToCart);
    }

    /**
     * Méthode appelée lorsqu'on appuie sur le bouton "Ajouter au panier"
     *
     * @param event L'événement de clic sur le bouton
     */
    private void addSmartphoneToCart(View event) {
//        Intent intent = new Intent(getApplicationContext(), EcranPanier.class);
//        intent.putExtra(getString(R.string.VAR_ID), smartphoneId);
        Toast.makeText(getApplicationContext(), "L'article a été ajouté au panier.", Toast.LENGTH_SHORT).show();
    }
}