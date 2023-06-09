package fr.equipeR.teltechmobile.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import fr.equipeR.teltechmobile.R;

/**
 * Classe <strong>singleton</strong> qui contient tous les smartphones.
 */
public class SmartphoneList {
    private final List<Smartphone> smartphones = new ArrayList<>();
    private final Map<Integer, Smartphone> smartphoneMap = new HashMap<>();
    private final RequestQueue queue;
    private final String TAG = getClass().getName();
    private final Context applicationContext;

    private static SmartphoneList instance;

    /**
     * Responsable de la création d'une liste de smartphones.
     *
     * @param context de l'application
     */
    private SmartphoneList(Context context) {
        // Récupère le contexte de l'application
        applicationContext = context.getApplicationContext();
        // Initialise la file d'attente des requêtes Volley
        queue = Volley.newRequestQueue(applicationContext);
    }


    /**
     * @return La taille de la liste des smartphones.
     */
    public int size() {
        return smartphones.size();
    }

    /**
     * @see List#get(int)
     */
    public Smartphone get(int index) {
        return smartphones.get(index);
    }

    /**
     * Renvoie un smartphone en fonction de son id.
     *
     * @param id L'id du smartphone à récupérer.
     * @return Le smartphone correspondant à l'id, ou null si aucun smartphone avec
     * @see Smartphone#getId()
     */
    public Smartphone getById(int id) {
        return smartphoneMap.get(id);
    }

    /**
     * Récupère la liste des smartphones disponibles de façon asyncrone.
     *
     * @param onSmartphonesFetchedListener Une fonction qui est appelée une fois que les smartphones
     *                                     ont été recuperé.
     * @param onErrorListener              Une fonction qui est appelée si une erreur survient.
     */
    public void fetchSmartphones(@NonNull Consumer<List<Smartphone>> onSmartphonesFetchedListener,
                                 @NonNull Consumer<VolleyError> onErrorListener) {
        if (size() != 0) {
            smartphones.clear();
            smartphoneMap.clear();
        }

        final String url = applicationContext.getString(R.string.smartphones_api_endpoint);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
                response -> {
                    Log.d(TAG, "fetchSmartphones: les smartphones ont été recuperés");
                    try {
                        JSONArray smartphonesJson = response.getJSONArray("articles");
                        for (int i = 0; i < smartphonesJson.length(); i++) {
                            JSONObject smartphoneJson = smartphonesJson.getJSONObject(i);
                            Smartphone smartphone = new SmartphoneBuilder()
                                    .setId(smartphoneJson.getInt("article_id"))
                                    .setName(smartphoneJson.getString("article_name"))
                                    .setDescription(smartphoneJson.getString("description"))
                                    .setRating(smartphoneJson.getInt("rating"))
                                    .setYear(smartphoneJson.getInt("year"))
                                    .setPriceTax(Double.parseDouble(smartphoneJson.getString("price_tax")))
                                    .setPriceNoTax(Double.parseDouble(smartphoneJson.getString("price_tax")) / 1.2d)
                                    .setImageID(smartphoneJson.getString("image"))
                                    .setSupplierName(smartphoneJson.getString("supplier_name"))
                                    .setQuantity(smartphoneJson.getInt("quantity"))
                                    .createSmartphone();
                            smartphones.add(smartphone);
                            smartphoneMap.put(smartphone.getId(), smartphone);
                        }
                    } catch (JSONException e) {
                        Log.e(TAG, "fetchSmartphones: erreur lors de la conversion du JSON", e);
                        throw new RuntimeException(e);
                    }

                    Log.i(TAG, "fetchSmartphones: nombre de smartphones récup : " + smartphones.size());
                    onSmartphonesFetchedListener.accept(smartphones);
                },
                error -> {
                    Log.e(TAG, "fetchSmartphones: erreur lors de la récuperation des smartphones", error);
                    onErrorListener.accept(error);
                }
        );

        Log.d(TAG, "fetchSmartphones: récuperation des smartphones...");
        queue.add(jsonObjectRequest);
    }

    /**
     * Initialise l'instance de la classe SmartphoneList en créant une nouvelle instance
     * si elle n'existe pas encore.
     *
     * @param context Le contexte de l'application.
     */
    public static void initialise(Context context) {
        if (instance == null) {
            instance = new SmartphoneList(context);
        }
    }

    /**
     * Récupère l'instance de la classe SmartphoneList. Si l'instance n'a pas été initialisée
     * avec la méthode initialise(Context), une IllegalStateException est levée.
     *
     * @return L'instance de la classe SmartphoneList.
     * @throws IllegalStateException Si la méthode initialise(Context) n'a pas été appelée avant.
     */
    @NonNull
    public static SmartphoneList getInstance() throws IllegalStateException {
        if (instance == null) {
            throw new IllegalStateException("Cette classe n'a pas été correctement initialisée, " +
                    "utilisez initialise(Context) avant le premier appel.");
        }

        return instance;
    }
}
