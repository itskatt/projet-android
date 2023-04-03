package fr.equipeR.teltechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class EcranSmartphone extends AppCompatActivity {

    private int idSmartphone = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecran_smartphone);

        Button buttonAddToCart = findViewById(R.id.buttonAddToCart);

        buttonAddToCart.setOnClickListener(event -> {
            Intent intent = new Intent(getApplicationContext(), EcranPanier.class);
            intent.putExtra(getString(R.string.VAR_ID), idSmartphone);
            Toast.makeText(getApplicationContext(), "L'article a été ajouté au panier.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
    }
}