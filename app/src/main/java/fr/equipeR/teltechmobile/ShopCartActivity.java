package fr.equipeR.teltechmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import fr.equipeR.teltechmobile.adapters.SmartPhoneShopcartAdapter;
import fr.equipeR.teltechmobile.model.ShopCartPhones;

public class ShopCartActivity extends AppCompatActivity implements ChangingPriceActivity{

    ListView articles;
    TextView HTPrice;
    TextView TTCPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcart_screen);

        articles = findViewById(R.id.telList);
        HTPrice = findViewById(R.id.htPrice);
        TTCPrice = findViewById(R.id.ttcPrice);

//        SmartphoneList.initialise(this);
        ShopCartPhones instance = ShopCartPhones.getInstance();
        instance.callWhenPriceChanging(this);
        SmartPhoneShopcartAdapter smartPhoneShopcartAdapter = new SmartPhoneShopcartAdapter(this, instance);
        SmartPhoneShopcartAdapter adapter = smartPhoneShopcartAdapter;
        articles.setAdapter(adapter);
    }

    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void onHTPriceChanged(double newPrice) {
        HTPrice.setText("Prix Hors Taxes \n" + newPrice);
    }

    @Override
    public void onTTCPriceChanged(double newPrice) {
        TTCPrice.setText("Prix Toutes Taxes Comprises \n" + newPrice);
    }
}