package fr.equipeR.teltechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
        Log.d("TAG", "onSmartphoneClicked: " + smartphone.getName());
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

}