package fr.equipeR.teltechmobile.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.equipeR.teltechmobile.R;
import fr.equipeR.teltechmobile.ShopCartActivity;
import fr.equipeR.teltechmobile.model.ShopCartPhones;
import fr.equipeR.teltechmobile.model.Smartphone;

/**
 * Created by frallo on 03/02/2020.
 */
public class SmartPhoneShopcartAdapter extends BaseAdapter {
    private ShopCartPhones phones;
    private LayoutInflater mInflater;  //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private ShopCartActivity activity;

    public SmartPhoneShopcartAdapter(ShopCartActivity activity, ShopCartPhones items) {
        this.activity = activity;
        this.phones = items;
        mInflater = LayoutInflater.from(activity.getContext());
    }

    public int getCount() {
        return phones.size();
    }

    public Object getItem(int position) {
        return phones.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = convertView == null ? mInflater.inflate(R.layout.shopcart_phone_item, parent, false) : convertView;

        //(2) : Récupération des TextView de notre layout
        ImageView image = layoutItem.findViewById(R.id.shopcartPhoneImage);
        TextView name = layoutItem.findViewById(R.id.phoneName);
        TextView year = layoutItem.findViewById(R.id.brandYear);
        TextView quantity = layoutItem.findViewById(R.id.shopcartPhoneQuantity);

        Button plus = layoutItem.findViewById(R.id.plusButton);
        Button minus = layoutItem.findViewById(R.id.minusButton);

        Smartphone phone = phones.get(position);
        //(3) : Renseignement des valeurs
        name.setText(phone.getName());
        year.setText(phone.getYear()+"");
        quantity.setText("x"+phones.getQuantity(phone));


        String imageUrl = activity.getContext()
                .getString(R.string.smartphone_image_api_endpoint) + phone.getImageID();
        Picasso.get()
                .load(imageUrl)
                .into(image);

        plus.setOnClickListener(view -> {
            quantity.setText(quantButton(quantity.getText().toString(), minus, Action.PLUS, position));
        });

        minus.setEnabled(false);
        minus.setOnClickListener(view -> {
            quantity.setText(quantButton(quantity.getText().toString(), minus, Action.MINUS, position));
        });


//        price.setText(String.valueOf(phoneIDs.get(position).getPrice()) + " €");
//        image.setBackground(phoneIDs.get(position).getDrawable());
//
//        name.setTag(position);
//        layoutItem.setOnClickListener(v -> activity.onClickNom(phoneIDs.get(position)) );

        return layoutItem; //On retourne l'item créé.
    }

    /**
     * gere l'evènement des boutons plus et moins
     * @param content
     * @param minus
     * @param action
     * @param position
     * @return
     */
    private String quantButton(String content, Button minus, Action action, int position){
        int result = 0;
        switch (action){
            case PLUS:
                int plusModifs = Integer.parseInt(content.split("x")[1]) + 1;
                minus.setEnabled(true);
                result = plusModifs;
                break;
            case MINUS:
                int minusModifs = Integer.parseInt(content.split("x")[1]) - 1;
                if (minusModifs <= 1){
                    minus.setEnabled(false);
                    result = 1;
                }
                else {
                    result = minusModifs;
                }
                break;
        }
        phones.setQuantity(phones.get(position), result);
        return "x" + result;
    }

    enum Action{PLUS, MINUS}
}


