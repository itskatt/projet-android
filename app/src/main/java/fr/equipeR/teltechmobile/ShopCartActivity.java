package fr.equipeR.teltechmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import fr.equipeR.teltechmobile.adapters.SmartPhoneShopcartAdapter;
import fr.equipeR.teltechmobile.model.ShopCartPhones;

public class ShopCartActivity extends ShakeableActivity implements ChangingPriceActivity {

    ListView articles;
    TextView HTPrice;
    TextView TTCPrice;
    Button commander;
    Button vider;
    TextView emptyText;

    private static int articleVisibility = View.INVISIBLE;
    private static int HTPriceVisibility = View.INVISIBLE;
    private static int TTCPriceVisibility = View.INVISIBLE;
    private static int commanderVisibility = View.INVISIBLE;
    private static int viderVisibility = View.INVISIBLE;

    private static int emptyTextVisibilyty = View.VISIBLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcart_screen);

        articles = findViewById(R.id.telList);
        HTPrice = findViewById(R.id.htPrice);
        TTCPrice = findViewById(R.id.ttcPrice);
        commander = findViewById(R.id.command);
        vider = findViewById(R.id.clear);
        emptyText = findViewById(R.id.emptyText);


//        SmartphoneList.initialise(this);
        ShopCartPhones phones = ShopCartPhones.getInstance();
        phones.callWhenPriceChanging(this);

        if (phones.size() == 0) {
            updateVisibilyties(View.INVISIBLE);
        } else {
            updateVisibilyties(View.VISIBLE);
        }


        SmartPhoneShopcartAdapter smartPhoneShopcartAdapter = new SmartPhoneShopcartAdapter(this, phones);
        articles.setAdapter(smartPhoneShopcartAdapter);

        commander.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), PaymentInfoActivity.class)));
    }

    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void onHTPriceUpdated(double newPrice) {
        newPrice = Math.round(newPrice * 1000.0) / 1000.0;
        HTPrice.setText("Tarif hors taxes : " + newPrice + " €");

    }

    @Override
    public void onTTCPriceUpdated(double newPrice) {
        newPrice = Math.round(newPrice * 1000.0) / 1000.0;
        TTCPrice.setText("Tarif TTC : " + newPrice + " €");
    }

    @Override
    public void onItemNumberUpdated(double newNumber) {
        if (newNumber == 0) {
            updateVisibilyties(View.INVISIBLE);
            return;
        }
        updateVisibilyties(View.VISIBLE);
    }

    public void updateVisibilyties(int visibility) {

        articleVisibility = visibility;
        HTPriceVisibility = visibility;
        TTCPriceVisibility = visibility;
        commanderVisibility = visibility;
        viderVisibility = visibility;

        articles.setVisibility(articleVisibility);
        HTPrice.setVisibility(HTPriceVisibility);
        TTCPrice.setVisibility(TTCPriceVisibility);

        emptyText.setVisibility(visibility == View.VISIBLE ? View.INVISIBLE : View.VISIBLE);


        boolean enabled = visibility == View.VISIBLE;
        commander.setEnabled(enabled);
        vider.setEnabled(enabled);


    }
}