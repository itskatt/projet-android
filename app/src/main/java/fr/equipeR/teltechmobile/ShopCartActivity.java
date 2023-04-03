package fr.equipeR.teltechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import fr.equipeR.teltechmobile.adapters.SmartPhoneShopcartAdapter;
import fr.equipeR.teltechmobile.model.ShopCartPhones;
import fr.equipeR.teltechmobile.model.SmartphoneList;

public class ShopCartActivity extends AppCompatActivity {

    ListView articles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcart_screen);

        articles = findViewById(R.id.telList);
//        SmartphoneList.initialise(this);
        ShopCartPhones instance = ShopCartPhones.getInstance();

        SmartPhoneShopcartAdapter smartPhoneShopcartAdapter = new SmartPhoneShopcartAdapter(this, instance);
        SmartPhoneShopcartAdapter adapter = smartPhoneShopcartAdapter;

        articles.setAdapter(adapter);
    }

    public Context getContext() {
        return this.getApplicationContext();
    }
}