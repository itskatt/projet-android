package fr.equipeR.teltechmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentInfoActivity extends ShakeableActivity {

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

    private boolean checkIfInvalid(TextView view, String name) {
        if (view.getText().length() == 0) {
            Toast.makeText(this, name + " ne peut pas être vide", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }
}