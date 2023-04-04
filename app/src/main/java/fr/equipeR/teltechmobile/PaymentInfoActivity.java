package fr.equipeR.teltechmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
/**
 *
 * Activité permettant de saisir les informations de paiement et de les vérifier avant de passer à
 * l'écran de confirmation.
 */
public class PaymentInfoActivity extends ShakeableActivity {

    /**
     *
     * Méthode appelée lors de la création de l'activité.
     * Initialise les champs de saisie et ajoute un écouteur de clic sur le bouton de paiement.
     *
     * @param savedInstanceState état enregistré de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        TextView surname = findViewById(R.id.formNom);
        TextView firstname = findViewById(R.id.formPrenom);
        TextView shippingAdress = findViewById(R.id.formAdresse);

        findViewById(R.id.payButton).setOnClickListener(view -> {
            if (checkIfInvalid(surname, "Le nom")) return;
            if (checkIfInvalid(firstname, "Le prénom")) return;
            if (checkIfInvalid(shippingAdress, "L'adresse de livraison")) return;

            Intent intent = new Intent(getApplicationContext(), EcranConfirmation.class);
            startActivity(intent);
        });
    }

    /**
     *
     * Vérifie si un champ de saisie est valide.
     * Affiche un message d'erreur si le champ est vide.
     *
     * @param view champ de saisie à vérifier
     * @param name nom du champ de saisie
     *
     * @return true si le champ est invalide, false sinon
     */
    private boolean checkIfInvalid(TextView view, String name) {
        if (view.getText().length() == 0) {
            Toast.makeText(this, name + " ne peut pas être vide", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }
}