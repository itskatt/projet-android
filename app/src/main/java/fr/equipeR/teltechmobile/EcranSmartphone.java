package fr.equipeR.teltechmobile;

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

public class EcranSmartphone extends ShakeableActivity {

    private int smartphoneId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_smartphone);

        Intent intent = getIntent();
        if (intent == null) return;

        Smartphone smartphone = SmartphoneList.getInstance()
                .getById(intent.getExtras().getInt(getString(R.string.smartphone_id_key)));

        TextView phoneName = findViewById(R.id.phoneName);
        TextView brandYear = findViewById(R.id.brandYear);
        ImageView phoneImage = findViewById(R.id.phoneImage);
        TextView phoneDescription = findViewById(R.id.phoneDescription);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView phonePriceHT = findViewById(R.id.phonePriceHT);
        TextView phonePriceTTC = findViewById(R.id.phonePriceTTC);
        TextView phoneStock = findViewById(R.id.phoneStock);

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

        smartphoneId = smartphone.getId();

        Button buttonAddToCart = findViewById(R.id.buttonAddToCart);

        buttonAddToCart.setOnClickListener(this::addSmartphoneToCart);
    }

    private void addSmartphoneToCart(View event) {
//        Intent intent = new Intent(getApplicationContext(), EcranPanier.class);
//        intent.putExtra(getString(R.string.VAR_ID), smartphoneId);
        Toast.makeText(getApplicationContext(), "L'article a été ajouté au panier.", Toast.LENGTH_SHORT).show();
    }
}