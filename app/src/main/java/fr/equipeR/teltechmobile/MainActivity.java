package fr.equipeR.teltechmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import fr.equipeR.teltechmobile.adapters.SoldSmartphoneAdapter;
import fr.equipeR.teltechmobile.model.Smartphone;
import fr.equipeR.teltechmobile.model.SmartphoneList;

public class MainActivity extends ShakeableActivity implements SmartphoneParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartphoneList.initialise(this);

        SmartphoneList list = SmartphoneList.getInstance();
        list.fetchSmartphones(this::onSmartphonesFetched, System.err::println);

        findViewById(R.id.cartIcon).setOnClickListener(v -> {
            Toast.makeText(this, "Panier...", Toast.LENGTH_SHORT).show();
        });
    }

    private void onSmartphonesFetched(List<Smartphone> s) {
        SoldSmartphoneAdapter adapter = new SoldSmartphoneAdapter(this);
        ListView soldSmartphoneList = findViewById(R.id.soldSmartphoneList);
        soldSmartphoneList.setAdapter(adapter);
    }

    @Override
    public void onSmartphoneClicked(Smartphone smartphone) {
        Intent intent = new Intent(getApplicationContext(), EcranSmartphone.class);
        intent.putExtra(getString(R.string.smartphone_id_key), smartphone.getId());
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}