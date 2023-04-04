package fr.equipeR.teltechmobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import fr.equipeR.teltechmobile.adapters.SoldSmartphoneAdapter;
import fr.equipeR.teltechmobile.model.Smartphone;
import fr.equipeR.teltechmobile.model.SmartphoneList;

/**
 *
 * Activité principale de l'application.
 * Elle hérite de ShakeableActivity, ce qui permet de détecter un événement de secousse de l'appareil.
 * Elle implémente SmartphoneParentActivity, ce qui permet de gérer les événements de clics sur les éléments de la liste.
 */
public class MainActivity extends ShakeableActivity implements SmartphoneParentActivity {

    /**
     *
     * Méthode appelée à la création de l'activité.
     * Initialise la vue en associant le layout activity_main.xml à cette activité.
     * Initialise la liste de smartphones de la classe SmartphoneList en la récupérant depuis la source de données externe.
     *
     * @param savedInstanceState Instance précédemment enregistrée de cette activité.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartphoneList.initialise(this);

        SmartphoneList list = SmartphoneList.getInstance();
        list.fetchSmartphones(this::onSmartphonesFetched, System.err::println);
    }

    /**
     * Méthode appelée lorsque la liste de smartphones est récupérée depuis la source de données externe.
     * Initialise l'adaptateur SoldSmartphoneAdapter et l'associe à la ListView soldSmartphoneList.
     * @param s Liste des smartphones récupérés.
     */
    private void onSmartphonesFetched(List<Smartphone> s) {
        SoldSmartphoneAdapter adapter = new SoldSmartphoneAdapter(this);
        ListView soldSmartphoneList = findViewById(R.id.soldSmartphoneList);
        soldSmartphoneList.setAdapter(adapter);
    }

    /**
     * Méthode appelée lorsqu'un smartphone est cliqué dans la liste.
     * Ouvre une nouvelle activité EcranSmartphone avec l'id du smartphone comme paramètre.
     * @param smartphone Smartphone cliqué dans la liste.
     */
    @Override
    public void onSmartphoneClicked(Smartphone smartphone) {
        Intent intent = new Intent(getApplicationContext(), EcranSmartphone.class);
        intent.putExtra(getString(R.string.smartphone_id_key), smartphone.getId());
        startActivity(intent);
    }

    /**
     * Renvoie le contexte de cette activité.
     * @return Le contexte de cette activité.
     */
    @Override
    public Context getContext() {
        return getApplicationContext();
    }
}