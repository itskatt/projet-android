package fr.equipeR.teltechmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import fr.equipeR.teltechmobile.adapters.SoldSmartphoneAdapter;
import fr.equipeR.teltechmobile.model.Smartphone;
import fr.equipeR.teltechmobile.model.SmartphoneList;

public class MainActivity extends AppCompatActivity implements SmartphoneParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartphoneList.initialise(this);

        SmartphoneList list = SmartphoneList.getInstance();
        list.fetchSmartphones(this::onSmartphonesFetched, System.err::println);
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