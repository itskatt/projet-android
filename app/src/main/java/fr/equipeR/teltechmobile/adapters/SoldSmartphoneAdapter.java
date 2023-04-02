package fr.equipeR.teltechmobile.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import fr.equipeR.teltechmobile.R;
import fr.equipeR.teltechmobile.SmartphoneParentActivity;
import fr.equipeR.teltechmobile.model.Smartphone;
import fr.equipeR.teltechmobile.model.SmartphoneList;

public class SoldSmartphoneAdapter extends BaseAdapter {
    private final SmartphoneList smartphoneList;
    private final LayoutInflater inflater;
    private final SmartphoneParentActivity parentActivity;

    public SoldSmartphoneAdapter(SmartphoneParentActivity parentActivity) {
        smartphoneList = SmartphoneList.getInstance();
        inflater = LayoutInflater.from(parentActivity.getContext());
        this.parentActivity = parentActivity;
    }

    @Override
    public int getCount() {
        return smartphoneList.size();
    }

    @Override
    public Object getItem(int i) {
        return smartphoneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View layoutItem;

        if (convertView == null)
            layoutItem = inflater.inflate(R.layout.smartphone_list_element_layout, parent, false);
        else layoutItem = convertView;

        ImageView smartphoneImage = layoutItem.findViewById(R.id.smartphoneImage);
        TextView smartphoneName = layoutItem.findViewById(R.id.smartphoneName);
        TextView smartphoneBrandAndYear = layoutItem.findViewById(R.id.smartphoneBrandAndYear);
        TextView smartphoneDescription = layoutItem.findViewById(R.id.smartphoneDescription);
        TextView smartphoneQuantity = layoutItem.findViewById(R.id.smartphoneQuantity);
        TextView smartphonePrice = layoutItem.findViewById(R.id.smartphonePrice);
        RatingBar smartphoneRatingBar = layoutItem.findViewById(R.id.smartphoneRatingBar);

        Smartphone smartphone = smartphoneList.get(position);

        smartphoneName.setText(smartphone.getName());
        smartphoneBrandAndYear.setText(smartphone.getSupplierName() + " - " + smartphone.getYear());
        smartphoneDescription.setText(smartphone.getDescription().substring(0, 42) + "…");
        smartphoneQuantity.setText("En stock : " + smartphone.getQuantity());
        smartphonePrice.setText(smartphone.getPriceTax() + " €");
        smartphoneRatingBar.setRating(smartphone.getRating());

        layoutItem.setOnClickListener(view -> parentActivity.onSmartphoneClicked(smartphone));

        return layoutItem;
    }
}
