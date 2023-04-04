package fr.equipeR.teltechmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/**
 * Classe représentant un fragment de barre de navigation contenant un icône de panier.
 */
public class NavBarFragment extends Fragment {

    /**
     *
     * Constructeur par défaut de la classe NavBarFragment.
     */
    public NavBarFragment() {
    }

    /**
     * Méthode appelée lors de la création de l'activité contenant le fragment.
     *
     * @param savedInstanceState état enregistré de l'activité
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Méthode appelée lorsque la vue associée au fragment a été créée.
     *
     * @param view vue associée au fragment
     * @param savedInstanceState état enregistré de la vue
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.cartIcon).setOnClickListener(v -> startActivity(new Intent(requireActivity().getApplicationContext(), ShopCartActivity.class)));
    }

    /**
     * Méthode appelée pour créer et retourner la vue associée au fragment.
     *
     * @param inflater objet permettant de créer la vue à partir d'un layout XML
     * @param container conteneur parent de la vue
     * @param savedInstanceState état enregistré de la vue
     *
     * @return la vue créée pour le fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_bar, container, false);
    }
}