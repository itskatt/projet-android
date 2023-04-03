package fr.equipeR.teltechmobile;

import android.content.Intent;
import android.os.Bundle;

public class PaymentInfoActivity extends ShakeableActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_info);

        findViewById(R.id.payButton).setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), EcranConfirmation.class);
            startActivity(intent);
        });
    }
}