package fr.equipeR.teltechmobile.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

import fr.equipeR.teltechmobile.R;
import fr.equipeR.teltechmobile.SmartphoneParentActivity;
import fr.equipeR.teltechmobile.model.Smartphone;
import fr.equipeR.teltechmobile.model.SmartphoneList;

public class SoldSmartphoneAdapter extends BaseAdapter {
    private final SmartphoneList smartphoneList;
    private final LayoutInflater inflater;
    private final SmartphoneParentActivity parentActivity;
    private final static int[] placeholders = {
            R.drawable.placeholder,
            R.drawable.placeholder1,
            R.drawable.placeholder2,
            R.drawable.placeholder3,
    };
    private final static Random random = new Random();

    /**
     * Constructeur de la classe SoldSmartphoneAdapter.
     *
     * @param parentActivity l'activité parente
     */
    public SoldSmartphoneAdapter(SmartphoneParentActivity parentActivity) {
        smartphoneList = SmartphoneList.getInstance();
        inflater = LayoutInflater.from(parentActivity.getContext());
        this.parentActivity = parentActivity;
    }

    /**
     * Retourne le nombre d'éléments dans la liste des smartphones vendus.
     *
     * @return le nombre d'éléments dans la liste
     */
    @Override
    public int getCount() {
        return smartphoneList.size();
    }

    /**
     * Retourne l'élément à la position donnée dans la liste des smartphones vendus.
     *
     * @param i la position de l'élément
     * @return l'élément à la position donnée
     */
    @Override
    public Object getItem(int i) {
        return smartphoneList.get(i);
    }

    /**
     * Retourne l'ID de l'élément à la position donnée dans la liste des smartphones vendus.
     *
     * @param i la position de l'élément
     * @return l'ID de l'élément à la position donnée
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Cette méthode renvoie un entier aléatoire qui correspond à l'ID d'une image de placeholder.
     * Les ID des placeholders sont stockés dans un tableau statique de la classe, placeholders.
     *
     * @return un entier aléatoire correspondant à l'ID d'une image de placeholder
     */
    private static int randomPlaceholder() {
        return placeholders[random.nextInt(placeholders.length)];
    }

    /**
     * Retourne la vue à la position donnée dans la liste des smartphones vendus.
     *
     * @param position la position de la vue
     * @param convertView la vue convertie
     * @param parent le parent de la vue
     * @return la vue à la position donnée
     */
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

        String imageUrl = parentActivity.getContext()
                .getString(R.string.smartphone_image_api_endpoint) + smartphone.getImageID();
        Picasso.get()
                .load(imageUrl)
                .placeholder(randomPlaceholder())
                .error(R.drawable.placeholder)
                .into(smartphoneImage);

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
