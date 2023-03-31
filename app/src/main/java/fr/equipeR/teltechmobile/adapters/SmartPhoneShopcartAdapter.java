package fr.equipeR.teltechmobile.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import fr.equipeR.teltechmobile.R;
import fr.equipeR.teltechmobile.ShopCartActivity;
import fr.equipeR.teltechmobile.model.ShopCartPhones;

/**
 * Created by frallo on 03/02/2020.
 */
public class SmartPhoneShopcartAdapter extends BaseAdapter {
    private ShopCartPhones phoneIDs;
    private LayoutInflater mInflater;  //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private ShopCartActivity activity;

    public SmartPhoneShopcartAdapter(ShopCartActivity activity, ShopCartPhones items) {
        this.activity = activity;
        this.phoneIDs = items;
        mInflater = LayoutInflater.from(activity.getContext());
    }

    public int getCount() {
        return phoneIDs.size();
    }

    public Object getItem(int position) {
        return phoneIDs.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View layoutItem;

        //(1) : Réutilisation des layouts
        layoutItem = convertView == null ? mInflater.inflate(R.layout.shopcart_phone_item, parent, false) : convertView;

        //(2) : Récupération des TextView de notre layout
//        TextView name = layoutItem.findViewById(R.id.pizzaName);
//        TextView price = layoutItem.findViewById(R.id.pizzaPrice);
//        ImageView image = layoutItem.findViewById(R.id.pizzaImage);

        //(3) : Renseignement des valeurs
//        name.setText(phoneIDs.get(position).getName());
//        price.setText(String.valueOf(phoneIDs.get(position).getPrice()) + " €");
//        image.setBackground(phoneIDs.get(position).getDrawable());
//
//        name.setTag(position);
//        layoutItem.setOnClickListener(v -> activity.onClickNom(phoneIDs.get(position)) );

        return layoutItem; //On retourne l'item créé.
    }


}


